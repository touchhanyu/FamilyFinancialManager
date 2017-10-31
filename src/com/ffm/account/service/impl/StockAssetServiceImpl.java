package com.ffm.account.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ffm.account.dao.AssetDynamicDAO;
import com.ffm.account.dao.BalanceBenifitDAO;
import com.ffm.account.dao.BalanceDetailDAO;
import com.ffm.account.dao.UserBalanceDAO;
import com.ffm.account.entity.AssetDynamic;
import com.ffm.account.entity.BalanceBenifit;
import com.ffm.account.entity.BalanceDetail;
import com.ffm.account.entity.UserBalance;
import com.ffm.account.service.StockAssetService;
import com.ffm.common.db.SQLUtil;
import com.ffm.common.dict.TRADEDIRECTION;
import com.ffm.common.exception.ServiceException;
import com.ffm.common.util.CalUtil;
import com.ffm.common.util.PKUtil;

public class StockAssetServiceImpl implements StockAssetService {
	private UserBalanceDAO ubDAO;
	private BalanceDetailDAO bdDAO;
	private AssetDynamicDAO adDAO;
	private BalanceBenifitDAO bbDAO;

	public void setUbDAO(UserBalanceDAO ubDAO) {
		this.ubDAO = ubDAO;
	}

	public void setBdDAO(BalanceDetailDAO bdDAO) {
		this.bdDAO = bdDAO;
	}

	public void setAdDAO(AssetDynamicDAO adDAO) {
		this.adDAO = adDAO;
	}

	public void setBbDAO(BalanceBenifitDAO bbDAO) {
		this.bbDAO = bbDAO;
	}

	@Override
	public void saveBalanceDetail(UserBalance userBalance, BalanceDetail balanceDetail) {
		// TODO Auto-generated method stub
		/** 查询操作人股票资产 **/
		String sql = "select ID from FFM_ACCOUNT_USERBALANCE where type='33' and USERID=" + userBalance.getOperId();
		List<Object[]> list = SQLUtil.query(sql);
		if (list.size() == 0) {// 新增
			int id = PKUtil.generatePK("ACCOUNT_USERBALANCE");
			userBalance.setId(id);
			userBalance.setUserId(userBalance.getOperId());
			this.ubDAO.insert(userBalance);
		}
		balanceDetail.setBalanceId(userBalance.getId());
		int id = PKUtil.generatePK("ACCOUNT_BALANCEDETAIL");
		balanceDetail.setId(id);
		this.bdDAO.insert(balanceDetail);
	}

	@Override
	public void calWorth(BalanceDetail balanceDetail) throws Exception {
		// TODO Auto-generated method stub
		double fee = 5;// 交易手续费默认为5
		String sql = "select TOTAL,NUM,AVERAGEPRICE from FFM_Account_AssetDynamic where BALANCEID="
				+ balanceDetail.getBalanceId() + " and DETAILID='" + balanceDetail.getDetailId() + "'";
		List<AssetDynamic> list = SQLUtil.query(sql, AssetDynamic.class);
		AssetDynamic newAsset = new AssetDynamic();
		newAsset.setBalanceId(balanceDetail.getBalanceId());
		newAsset.setDetailId(balanceDetail.getDetailId());
		/** 计算发生额 **/
		double sum = 0;
		BigDecimal amount = CalUtil.mul(balanceDetail.getPrice(), balanceDetail.getNum());// 本次发生额
		if (TRADEDIRECTION.BUY.getValue().equals(balanceDetail.getType())) {// 买入
			amount = CalUtil.add(amount.doubleValue(), fee);// 单价*数量+费用
		} else if (TRADEDIRECTION.SELL.getValue().equals(balanceDetail.getType())) {// 卖出
			/** 卖出后金额=本次发生额*(1-0.001)-手续费 **/
			sum = CalUtil.sub(CalUtil.mul(amount.doubleValue(), 0.999).doubleValue(), fee);
		}
		double diff = sum;// 资产价值变动发生额
		if (list.size() == 1) {
			AssetDynamic asset = list.get(0);
			Double total = asset.getTotal();
			Integer num = asset.getNum();
			if ("1".equals(balanceDetail.getType())) {// 买入
				total = CalUtil.add(CalUtil.add(total, amount.doubleValue()).doubleValue(), fee).doubleValue();// 余额+本次发生金额+手续费
				num = balanceDetail.getNum() + num;
			} else {// 卖出
				/** 卖出后金额=本次发生额*(1-0.001)-手续费 **/
				sum = CalUtil.sub(CalUtil.mul(sum, 0.999).doubleValue(), fee);
				diff = -sum;
				total = CalUtil.sub(total, sum);
				if (total < 0) {// 收益超过投资本金，余下部分成本为0，按本次投资收益计算
					total = 0d;
					diff = asset.getTotal();
				}
				num = num - balanceDetail.getNum();
				if (num < 0) {
					throw new Exception("卖出数量超过持仓数量");
				} else if (num == 0) {// 清仓
					total = 0d;
					diff = asset.getTotal();
					sql = "update FFM_ACCOUNT_BALANCEDETAIL set STATUS='2' where BALANCEID=" + balanceDetail.getBalanceId()
							+ " and DETAILID='" + balanceDetail.getDetailId() + "'";
					SQLUtil.update(sql, null, this.adDAO);
					this.calBenifit(balanceDetail, asset);// 清仓时计算最终收益
				}
			}
			newAsset.setTotal(total);
			newAsset.setNum(num);
			if (num == 0) {
				newAsset.setAveragePrice(0d);
			} else {
				newAsset.setAveragePrice(CalUtil.div(total, num));
			}
			this.adDAO.update(newAsset);
		} else {// 新增买入
			newAsset.setTotal(CalUtil.add(amount.doubleValue(), fee).doubleValue());
			newAsset.setNum(balanceDetail.getNum());
			newAsset.setAveragePrice(balanceDetail.getPrice());
			this.adDAO.insert(newAsset);
		}
		sql = "update FFM_ACCOUNT_USERBALANCE set WORTH=WORTH+" + diff + " where ID=" + balanceDetail.getBalanceId();// 更新用户总资产
		SQLUtil.update(sql, null, this.ubDAO);
		/** 更新明细表 **/
		sql = "update FFM_ACCOUNT_BALANCEDETAIL set AMOUNT=" + diff + ",OUTSTANDING=,REAMAINNUM= where ID=";
		SQLUtil.update(sql, null, this.bdDAO);
	}

	@Override
	public void calBenifit(BalanceDetail balanceDetail, AssetDynamic asset) {
		// TODO Auto-generated method stub
		BalanceBenifit benifit = new BalanceBenifit();
		benifit.setId(PKUtil.generatePK("ACCOUNT_BALANCEBENIFIT"));
		benifit.setBalanceDetailId(balanceDetail.getId());
		benifit.setDetailId(balanceDetail.getDetailId());
		benifit.setTranDate(balanceDetail.getTranDate());
		/** 收益=(卖出价-成本价)*数量-手续费合计 **/
		Double price = balanceDetail.getPrice();
		Integer num = balanceDetail.getNum();
		Double averagePrice = asset.getAveragePrice();
		double sub = CalUtil.sub(price, averagePrice);
		double mul = CalUtil.mul(sub, num).doubleValue();
		benifit.setBenifit(mul);
		this.bbDAO.insert(benifit);
	}

	@Override
	public void modifyUserBalance(UserBalance userBalance) {
		// TODO Auto-generated method stub
		this.ubDAO.update(userBalance);
	}

	@Override
	public void modifyBalanceDetail(BalanceDetail balanceDetail) {
		// TODO Auto-generated method stub
		this.bdDAO.update(balanceDetail);
	}

	@Override
	public void removeBalanceDetail(BalanceDetail balanceDetail) throws ServiceException {
		// TODO Auto-generated method stub
		String sql = "select ID,BALANCEID,DETAILID,TYPE,PRICE,NUM,STATUS from FFM_ACCOUNT_BALANCEDETAIL where ID=" + balanceDetail.getId();
		List<BalanceDetail> list = SQLUtil.query(sql, BalanceDetail.class);
		if (list.size() == 1) {
			BalanceDetail detail = list.get(0);
			if ("1".equals(detail.getType())) {// 买入
				double worth = CalUtil.mul(detail.getPrice(), detail.getNum()).doubleValue();
				/** 删除资产动态表 **/
				sql = "select * from FFM_ACCOUNT_ASSETDYNAMIC where BALANCEID=" + detail.getBalanceId()
						+ " and DETAILID='" + detail.getDetailId() + "'";
				List<AssetDynamic> ads = SQLUtil.query(sql, AssetDynamic.class);
				AssetDynamic assetDynamic = ads.get(0);
				Double total = assetDynamic.getTotal();
				Integer num = assetDynamic.getNum();
				double num2 = CalUtil.sub(num, detail.getNum());
				if (num2 == 0) {// 删除后当前持股数为0
					this.adDAO.delete(assetDynamic);
					sql = "select * from FFM_ACCOUNT_ASSETDYNAMIC where BALANCEID=" + assetDynamic.getBalanceId();
					List<AssetDynamic> query = SQLUtil.query(sql, AssetDynamic.class);
					/** 不删除用户资产表，更新余额为0 **/
					if (query.size() == 0) {
						sql = "update FFM_ACCOUNT_USERBALANCE set worth=0 where ID=" + detail.getBalanceId();
						SQLUtil.update(sql, null, this.bdDAO);
					}
				} else if (num2 > 0) {
					total = CalUtil.sub(total, worth);
					double average = CalUtil.div(total, num2);
					sql = "update FFM_ACCOUNT_ASSETDYNAMIC set TOTAL=" + total + ",NUM=" + num2 + ",AVERAGEPRICE=" + average
							+ " where BALANCEID=" + detail.getBalanceId() + " and DETAILID='" + detail.getDetailId() + "'";
					SQLUtil.update(sql, null, this.bdDAO);
					/** 更新用户资产表 **/
					sql = "update FFM_ACCOUNT_USERBALANCE set worth=worth-" + worth + " where ID=" + detail.getBalanceId();
					SQLUtil.update(sql, null, this.bdDAO);
				} else {// 删除后可用数量小于0
					throw new ServiceException("操作失败，可用持股数不足！");
				}
				/** 删除资产明细 **/
				this.bdDAO.delete(balanceDetail);
			} else {// 卖出
				/** 还原资产明细 **/
				/** 还原资产动态表 **/
			}
		}
	}
}