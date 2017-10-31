package com.ffm.account.service;

import com.ffm.account.entity.AssetDynamic;
import com.ffm.account.entity.BalanceDetail;
import com.ffm.account.entity.UserBalance;
import com.ffm.common.exception.ServiceException;

public interface StockAssetService {
	/**
	 * 保存资产明细业务方式
	 * 
	 * @param userBalance
	 * @param balanceDetail
	 */
	void saveBalanceDetail(UserBalance userBalance, BalanceDetail balanceDetail);

	/**
	 * 处理当前资产总值
	 * 
	 * @param balanceDetail
	 */
	void calWorth(BalanceDetail balanceDetail) throws Exception;

	/**
	 * 计算并记录最终投资收益
	 * 
	 * @param balanceDetail
	 * @param asset
	 */
	void calBenifit(BalanceDetail balanceDetail, AssetDynamic asset);

	/**
	 * 修改用户资产
	 * 
	 * @param userBalance
	 */
	void modifyUserBalance(UserBalance userBalance);

	/**
	 * 修改用户资产明细
	 * 
	 * @param balanceDetail
	 */
	void modifyBalanceDetail(BalanceDetail balanceDetail);

	/**
	 * 删除用户资产明细
	 * 
	 * @param balanceDetail
	 */
	void removeBalanceDetail(BalanceDetail balanceDetail) throws ServiceException;
}