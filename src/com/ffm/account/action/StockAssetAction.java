package com.ffm.account.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ffm.account.dto.StockAssetDTO;
import com.ffm.account.entity.BalanceDetail;
import com.ffm.account.entity.UserBalance;
import com.ffm.account.service.StockAssetService;
import com.ffm.common.action.BaseAction;
import com.ffm.common.db.SQLUtil;
import com.ffm.common.dict.STATUS;
import com.ffm.common.entity.DataGrid;
import com.ffm.common.exception.ServiceException;
import com.ffm.common.util.COMUtil;
import com.ffm.sys.entity.SysUser;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class StockAssetAction extends BaseAction {
	private int rows;
	private int page;
	private UserBalance balance;
	private BalanceDetail detail;
	private String type;
	private String date1;
	private String date2;

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public UserBalance getBalance() {
		return balance;
	}

	public void setBalance(UserBalance balance) {
		this.balance = balance;
	}

	public BalanceDetail getDetail() {
		return detail;
	}

	public void setDetail(BalanceDetail detail) {
		this.detail = detail;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	private StockAssetService service;

	public void setService(StockAssetService service) {
		this.service = service;
	}

	public String userStockAsset() {
		SysUser sysUser = super.getSysUser();
		StringBuffer sb = new StringBuffer();
		sb.append("select ID,nvl(sum(TOTAL),0),nvl(sum(WORTH),0) from(select ID,TOTAL,NOWPRI*NUM WORTH from ");
		sb.append("(select BALANCEID ID,DETAILID,TOTAL,NUM from FFM_Account_AssetDynamic where BALANCEID in");
		sb.append("(select ID from FFM_ACCOUNT_USERBALANCE b where TYPE='33' and b.USERID=").append(sysUser.getId());
		sb.append("))detail left join FFM_STOCK_STOCK stock on detail.DETAILID=stock.GTYPE||stock.GID) group by ID");
		List<Object[]> list = SQLUtil.query(sb.toString());
		if (list.size() == 1) {
			Object[] objs = list.get(0);
			BigDecimal id = (BigDecimal) objs[0];
			BigDecimal worth = (BigDecimal) objs[1];
			BigDecimal total = (BigDecimal) objs[2];
			super.setValueOGNL("balanceId", id.intValue());
			super.setValueOGNL("worth", worth.doubleValue());
			super.setValueOGNL("total", total.doubleValue());
		}
		return SUCCESS;
	}

	/**
	 * 查询用户股票资产明细
	 * 
	 * @throws IOException
	 */
	public void stockAssetList() throws IOException {
		SysUser user = (SysUser) super.getSessionAttributes("user");
		if (user != null) {
			StringBuffer sb = new StringBuffer();
			sb.append("select bd.ID,TYPE,DETAILID,PRICE,NUM,FEE,TRANDATE,(select GNAME from FFM_STOCK_COMPANY where GTYPE||GID=DETAILID)DETAILVALUE from ");
			sb.append("(select ID from FFM_ACCOUNT_USERBALANCE where TYPE='33' and USERID=").append(user.getId()).append(")ub left join ");
			sb.append("(select ID,TYPE,BALANCEID,DETAILID,PRICE,NUM,FEE,TRANDATE from FFM_ACCOUNT_BALANCEDETAIL where STATUS='1')bd on ub.ID=bd.BALANCEID ");
			sb.append("where bd.ID is not null");
			if (this.type != null) {
				sb.append(" and TYPE='").append(this.type).append("'");
			}
			if (this.date1 != null && !"".equals(this.date1)) {
				sb.append(" and TRANDATE>=to_date('").append(this.date1).append("','yyyy-MM-dd')");
			}
			if (this.date2 != null && !"".equals(this.date2)) {
				sb.append(" and TRANDATE<=to_date('").append(this.date2).append("','yyyy-MM-dd')");
			}
			sb.append(" order by DETAILID,TRANDATE");
			int total = SQLUtil.queryCount(sb.toString());
			List<StockAssetDTO> list = SQLUtil.queryByPage(sb.toString(), StockAssetDTO.class, this.rows, this.page);
			DataGrid grid = new DataGrid();
			grid.setRows(list);
			grid.setTotal(total);
			Gson gson = new Gson();
			String json = gson.toJson(grid);
			super.outputJSON(json);
		}
	}

	/**
	 * 保存用户股票资产明细
	 * 
	 * @return
	 */
	public String saveBalanceDetail() {
		Date date = new Date();
		String time = COMUtil.currentTime();
		SysUser sysUser = super.getSysUser();
		this.balance.setType("33");
		this.balance.setWorth(0d);
		this.balance.setMakeDate(date);
		this.balance.setMakeTime(time);
		this.balance.setOperId(sysUser.getId());
		this.detail.setMakeDate(date);
		this.detail.setMakeTime(time);
		this.detail.setOperId(sysUser.getId());
		this.detail.setStatus(STATUS.Y.getValue());
		this.service.saveBalanceDetail(this.balance, this.detail);
		try {
			this.detail.setBalanceId(this.balance.getId());
			this.service.calWorth(this.detail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 删除资产明细
	 */
	public String removeBalanceDetail() {
		if (this.detail.getId() == null)
			return ERROR;
		try {
			this.service.removeBalanceDetail(this.detail);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			super.setValueOGNL("error", e);
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
}