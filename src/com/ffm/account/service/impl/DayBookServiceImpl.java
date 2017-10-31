package com.ffm.account.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ffm.account.dao.DayBookDAO;
import com.ffm.account.dto.DaybookDTO;
import com.ffm.account.entity.DayBook;
import com.ffm.account.service.DayBookService;
import com.ffm.common.db.SQLUtil;
import com.ffm.common.entity.DataGrid;
import com.ffm.common.util.CalUtil;
import com.ffm.common.util.PKUtil;

public class DayBookServiceImpl implements DayBookService {
	private DayBookDAO dao;

	public void setDao(DayBookDAO dao) {
		this.dao = dao;
	}

	@Override
	public DataGrid queryByPage(String sql, int page, int size) {
		// TODO Auto-generated method stub
		int max = SQLUtil.queryCount(sql);
		List<DaybookDTO> list = SQLUtil.queryByPage(sql, DaybookDTO.class, size, page);
		DataGrid grid = new DataGrid();
		grid.setTotal(max);
		grid.setRows(list);
		return grid;
	}

	@Override
	public void save(DayBook dayBook) {
		// TODO Auto-generated method stub
		String id = PKUtil.generateD_PK("ACCOUNT_DAYBOOK", 8);
		dayBook.setId(id);
		this.dao.insert(dayBook);
		this.calWorth(dayBook, "save");
	}

	@Override
	public void modify(DayBook dayBook) {
		// TODO Auto-generated method stub
		String sql = "select DIRECTION,TYPE,AMOUNT,TRANOPER from FFM_ACCOUNT_DAYBOOK where id='" + dayBook.getId() + "'";
		List<DayBook> list = SQLUtil.query(sql, DayBook.class);
		if (list.size() == 1) {
			DayBook db = list.get(0);
			String direction = db.getDirection();
			String type = db.getType();
			Double amount = db.getAmount();
			Integer oper = db.getTranOper();
			if ("in".equals(direction)) {
				amount = -amount;
			}
			sql = "update FFM_ACCOUNT_USERBALANCE set WORTH=WORTH+" + amount+ " where TYPE='" + type + "' and USERID=" + oper;
			SQLUtil.update(sql, null, this.dao);
			Double newAmount = dayBook.getAmount();
			if ("out".equals(dayBook.getDirection())) {
				newAmount = -newAmount;
			}
			sql = "update FFM_ACCOUNT_USERBALANCE set WORTH=WORTH+" + newAmount + " where TYPE='" + dayBook.getType() + "' and USERID=" + oper;
			SQLUtil.update(sql, null, this.dao);
			this.dao.update(dayBook);
		}
	}

	@Override
	public void remove(DayBook dayBook) {
		// TODO Auto-generated method stub
		this.calWorth(dayBook, "remove");
		this.dao.delete(dayBook);
	}

	@Override
	public void calWorth(DayBook dayBook, String oper) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
//		sb.append("select ID,WORTH,DIRECTION,AMOUNT from ");
//		sb.append("(select DIRECTION,TYPE,AMOUNT,TRANOPER from FFM_ACCOUNT_DAYBOOK where ID=").append(dayBook.getId());
//		sb.append(")db left join FFM_ACCOUNT_USERBALANCE ub on db.TYPE=ub.TYPE and db.TRANOPER=ub.USERID");
		sb.append("select ID,WORTH from FFM_ACCOUNT_USERBALANCE where TYPE='").append(dayBook.getType());
		sb.append("' and USERID=").append(dayBook.getTranOper());
		List<Object[]> list = SQLUtil.query(sb.toString());
		if (list.size() == 1) {
			Object[] objs = list.get(0);
			BigDecimal id = (BigDecimal) objs[0];
			BigDecimal worth = (BigDecimal) objs[1];
			String direction = dayBook.getDirection();
			double amount = dayBook.getAmount();
			double sum = worth.doubleValue();
			if ("save".equals(oper)) {// 保存
				if ("in".equals(direction)) {// 支入
					sum = CalUtil.add(sum, amount).doubleValue();
				} else if ("out".equals(direction)) {// 支出
					sum = CalUtil.sub(sum, amount);
				}
			} else if ("remove".equals(oper)) {// 删除，与保存相反
				if ("in".equals(direction)) {// 减去支入部分
					sum = CalUtil.sub(sum, amount);
				} else if ("out".equals(direction)) {// 增加支出部分
					sum = CalUtil.add(sum, amount).doubleValue();
				}
			}
			String sql = "update FFM_ACCOUNT_USERBALANCE set WORTH=" + sum + " where ID=" + id.intValue();
			SQLUtil.update(sql, null);
			String purpose = dayBook.getPurpose();
			String accountType = "", quote = "+";
			if ("withdraw".equals(purpose)) {// 取款银行账户联动处理
				accountType = "01";
				quote = "+";
			} else if ("repay".equals(purpose)) {// 还款信贷账户联动处理
				accountType = "10";
				quote = "+";
			}
			sql = "update FFM_ACCOUNT_USERBALANCE set WORTH=WORTH" + quote + amount
					+ " where TYPE='" + accountType + "' and USERID=" + dayBook.getTranOper();
			SQLUtil.update(sql, null);
		}
	}

	@Override
	public DayBook queryById(String id) {
		// TODO Auto-generated method stub
		String sql = "select * from FFM_ACCOUNT_DAYBOOK where ID='" + id + "'";
		List<DayBook> list = SQLUtil.query(sql, DayBook.class);
		return list.get(0);
	}
}