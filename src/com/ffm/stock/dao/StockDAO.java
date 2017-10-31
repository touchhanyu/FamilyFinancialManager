package com.ffm.stock.dao;

import java.sql.SQLException;
import java.util.List;

import com.ffm.common.db.DAO;
import com.ffm.common.db.DAOUtil;

public class StockDAO extends DAO {
	public StockDAO() {
		super.insSqlTemp = "insert into FFM_STOCK_STOCK(GTYPE,GID,NAME,TODAYSTARTPRI,YESTODENDPRI,NOWPRI,TODAYMAX,TODAYMIN,COMPETITIVEPRI,RESERVEPRI,TRANUMBER,TRAAMOUNT,BUY1,BUY1PRI,BUY2,BUY2PRI,BUY3,BUY3PRI,BUY4,BUY4PRI,BUY5,BUY5PRI,SELL1,SELL1PRI,SELL2,SELL2PRI,SELL3,SELL3PRI,SELL4,SELL4PRI,SELL5,SELL5PRI,PUBLICDATE,PUBLICTIME,MAKEDATE,MAKETIME) values(gType?,gid?,name?,todayStartPri?,yestodEndPri?,nowPri?,todayMax?,todayMin?,competitivePri?,reservePri?,traNumber?,traAmount?,buyOne?,buyOnePri?,buyTwo?,buyTwoPri?,buyThree?,buyThreePri?,buyFour?,buyFourPri?,buyFive?,buyFivePri?,sellOne?,sellOnePri?,sellTwo?,sellTwoPri?,sellThree?,sellThreePri?,sellFour?,sellFourPri?,sellFive?,sellFivePri?,date?,time?,makeDate?,makeTime?)";
		super.updSqlTemp = "update FFM_STOCK_STOCK set GTYPE=gType? where GID=gid?";
		super.delSqlTemp = "delete FFM_STOCK_STOCK where GTYPE=gType? and GID=gid?";
		super.insSql = "insert into FFM_STOCK_STOCK(GTYPE,GID,NAME,TODAYSTARTPRI,YESTODENDPRI,NOWPRI,TODAYMAX,TODAYMIN,COMPETITIVEPRI,RESERVEPRI,TRANUMBER,TRAAMOUNT,BUY1,BUY1PRI,BUY2,BUY2PRI,BUY3,BUY3PRI,BUY4,BUY4PRI,BUY5,BUY5PRI,SELL1,SELL1PRI,SELL2,SELL2PRI,SELL3,SELL3PRI,SELL4,SELL4PRI,SELL5,SELL5PRI,PUBLICDATE,PUBLICTIME,MAKEDATE,MAKETIME) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		super.updSql = "update FFM_STOCK_STOCK set GTYPE=? where GID=?";
		super.delSql = "delete FFM_STOCK_STOCK where GTYPE=? and GID=?";
	}

	@Override
	public <Stock> void insert(List<Stock> list) {
		try {
			DAOUtil.insert(this, list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <Stock> void update(List<Stock> list) {
		try {
			DAOUtil.update(this, list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <Stock> void delete(List<Stock> list) {
		try {
			DAOUtil.delete(this, list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <Stock> void insert(Stock obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public <Stock> void update(Stock obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public <Stock> void delete(Stock obj) {
		// TODO Auto-generated method stub
	}
}