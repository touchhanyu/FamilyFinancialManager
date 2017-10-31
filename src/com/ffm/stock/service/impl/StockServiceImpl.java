package com.ffm.stock.service.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ffm.common.annotation.Comment;
import com.ffm.common.db.DAO;
import com.ffm.common.db.SQLUtil;
import com.ffm.common.entity.DataGrid;
import com.ffm.common.entity.PropertyGrid;
import com.ffm.common.entity.PropertyGridRow;
import com.ffm.stock.dao.CompanyDAO;
import com.ffm.stock.entity.Company;
import com.ffm.stock.entity.Stock;
import com.ffm.stock.entity.StockQuota;
import com.ffm.stock.input.StockInfo;
import com.ffm.stock.service.StockService;
import com.ffm.sys.entity.SysUser;
import com.google.gson.Gson;

public class StockServiceImpl implements StockService {
	private DAO companyDao = new CompanyDAO();

	public DAO getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(DAO companyDao) {
		this.companyDao = companyDao;
	}

	@Override
	public void requestData() {
		// TODO Auto-generated method stub
		StockInfo stockInfo = new StockInfo();
//		List<Company> list = this.queryStockByName("");
		stockInfo.requestStockById("");
	}

	@Override
	public int queryStockInfoMax() {
		String sql = "select count(*) from FFM_STOCK_STOCK";
		List<Object[]> list = SQLUtil.query(sql);
		Object[] objects = list.get(0);
		BigDecimal max = (BigDecimal) objects[0];
		return max.intValue();
	}

	@Override
	public List<Stock> queryStockInfoByPage(int page, int size) {
		// TODO Auto-generated method stub
		int start = (page - 1) * size;
		int end = page * size;
		StringBuffer sb = new StringBuffer();
		sb.append("select * from (select GID,NAME,TODAYSTARTPRI,YESTODENDPRI,NOWPRI,TODAYMAX,TODAYMIN,COMPETITIVEPRI,");
		sb.append("RESERVEPRI,TRANUMBER,TRAAMOUNT,rownum RN from FFM_STOCK_STOCK order by GID) where RN>");
		sb.append(start).append(" and RN<=").append(end);
		return SQLUtil.query(sb.toString(), Stock.class);
	}

	@Override
	public List<Company> queryStockByName(String name) {
		// TODO Auto-generated method stub
		String sql = "select ID,GNAME,PINYIN,GID from FFM_STOCK_COMPANY where PINYIN like '%" + name + "%' order by GID";
		return SQLUtil.query(sql, Company.class);
	}

	@Override
	public List<Company> queryStockByPinyin(String pinyin) {
		// TODO Auto-generated method stub
		String sql = "select ID,GTYPE||GID NAME,GNAME,PINYIN,GID from FFM_STOCK_COMPANY where PINYIN like '%" + pinyin + "%' order by GID";
		return SQLUtil.query(sql, Company.class);
	}

	@Override
	public DataGrid queryTodayStock(int row, int page, String param) {
		// TODO Auto-generated method stub
		String sql = "select GTYPE||GID GID,NAME,NOWPRI,TODAYMAX,TODAYMIN,TRANUMBER,TRAAMOUNT,PUBLICDATE,PUBLICTIME,";
		sql += "(case when TODAYSTARTPRI<NOWPRI then 'red' when TODAYSTARTPRI>NOWPRI then 'green' else '0' end)GTYPE from";
		sql += "(select * from FFM_STOCK_STOCK order by GID)";
		if (param != null && !"".equals(param)) {
			sql += " where GTYPE||GID='" + param + "'";
		}
		List<Stock> list = SQLUtil.queryByPage(sql, Stock.class, row, page);
		int count = SQLUtil.queryCount(sql);
		DataGrid grid = new DataGrid();
		grid.setRows(list);
		grid.setTotal(count);
		return grid;
	}

	@Override
	public PropertyGrid queryTodayStockDetail(String param) {
		// TODO Auto-generated method stub
		PropertyGrid grid = new PropertyGrid();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from FFM_STOCK_STOCK where GTYPE||GID='").append(param).append("'");
		List<Stock> list = SQLUtil.query(sb.toString(), Stock.class);
		if (list.size() == 1) {
			Stock stock = list.get(0);
			Field[] fields = stock.getClass().getDeclaredFields();
			ArrayList<PropertyGridRow> rows = new ArrayList<PropertyGridRow>();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				field.setAccessible(true);
				Comment common = field.getAnnotation(Comment.class);
				if (common == null)
					continue;
				PropertyGridRow row = new PropertyGridRow();
				row.setDictCode(field.getName());
				row.setName(common.value());
				try {
					Object value = field.get(stock);
					row.setValue(value.toString());
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				row.setGroup("指标明细");
				rows.add(row);
			}
			grid.setTotal(2);
			grid.setRows(rows);
		}
		return grid;
	}

	@Override
	public String queryStockHistory(String gid, String startDate, String endDate, int row, int page) {
		// TODO Auto-generated method stub
		DataGrid grid = new DataGrid();
		String sql = "select GID,NAME,PUBLICDATE,TODAYMAX,TODAYMIN,NOWPRI,TRANUMBER,TRAAMOUNT,";
		sql += "case when TRANUMBER<>0 then TRAAMOUNT/TRANUMBER else NOWPRI end AVGPRI,TODAYMAX-TODAYMIN SUB1";
		sql += " from FFM_STOCK_STOCKHISTORY where GTYPE||GID='" + gid + "'";
		if (startDate != null && !"".equals(startDate)) {
			sql += " and PUBLICDATE>=to_date('" + startDate + "','yyyy-MM-dd')";
		}
		if (endDate != null && !"".equals(endDate)) {
			sql += " and PUBLICDATE<=to_date('" + endDate + "','yyyy-MM-dd')";
		}
		sql += " order by PUBLICDATE desc";
		int count = SQLUtil.queryCount(sql);
		List<StockQuota> list = SQLUtil.queryByPage(sql, StockQuota.class, row, page);
		grid.setRows(list);
		grid.setTotal(count);
		Gson gson = new Gson();
		String json = gson.toJson(grid);
		return json;
	}

	@Override
	public void calculateStockCost(String gid, SysUser user) {
		// TODO Auto-generated method stub
		String sql = "select sum(decode(type,'1',cost,'2',-cost,0))cost from ";
		sql += "(select type,sum(price*num+decode(type,'1',1,'2',-1,0)*fee)cost from ffm_account_balancedetail";
		sql += " where detailid=#gid# and operid=#userid# and status='1' group by type)";
		HashMap<String, Object> para = new HashMap<String, Object>();
		para.put("gid", gid);
		para.put("userid", user.getId());
		List<Map<String,Object>> list = SQLUtil.query(sql, para);
		BigDecimal cost = BigDecimal.ZERO;
		if (list.size() == 1) {
			Map<String, Object> map = list.get(0);
			cost = (BigDecimal) map.get("COST");
		}
	}
}