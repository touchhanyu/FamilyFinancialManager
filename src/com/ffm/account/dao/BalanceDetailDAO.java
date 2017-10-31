package com.ffm.account.dao;

import java.sql.SQLException;
import java.util.List;

import com.ffm.common.db.DAO;
import com.ffm.common.db.DAOUtil;

public class BalanceDetailDAO extends DAO {
	public BalanceDetailDAO() {
		super.insSqlTemp = "insert into FFM_ACCOUNT_BALANCEDETAIL(ID,BALANCEID,DETAILID,TYPE,PRICE,NUM,FEE,AMOUNT,OUTSTANDING,REAMAINNUM,STATUS,TRANDATE,MAKEDATE,MAKETIME,OPERID) values(id?,balanceId?,detailId?,type?,price?,num?,fee?,amount?,outstanding?,remainNum?,status?,tranDate?,makeDate?,makeTime?,operId?)";
		super.updSqlTemp = "update FFM_ACCOUNT_BALANCEDETAIL set DETAILID=detailId?,TYPE=type?,PRICE=price?,NUM=num?,FEE=fee?,AMOUNT=amount?,OUTSTANDING=outstanding?,REAMAINNUM=remainNum?,STATUS=status?,TRANDATE=tranDate?,MAKEDATE=makeDate?,MAKETIME=makeTime?,OPERID=operId? where ID=id?";
		super.delSqlTemp = "delete FFM_ACCOUNT_BALANCEDETAIL WHERE ID=id?";
		super.insSql = "insert into FFM_ACCOUNT_BALANCEDETAIL(ID,BALANCEID,DETAILID,TYPE,PRICE,NUM,FEE,AMOUNT,OUTSTANDING,REAMAINNUM,STATUS,TRANDATE,MAKEDATE,MAKETIME,OPERID) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		super.updSql = "update FFM_ACCOUNT_BALANCEDETAIL set DETAILID=?,TYPE=?,PRICE=?,NUM=?,FEE=fee?,AMOUNT=?,OUTSTANDING=?,REAMAINNUM=?,STATUS=?,TRANDATE=?,MAKETIME=?,OPERID=? where ID=?";
		super.delSql = "delete FFM_ACCOUNT_BALANCEDETAIL WHERE ID=?";
	}

	@Override
	public <BalanceDetail> void insert(BalanceDetail detail) {
		try {
			DAOUtil.insert(this, detail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <BalanceDetail> void update(BalanceDetail detail) {
		try {
			DAOUtil.update(this, detail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <BalanceDetail> void delete(BalanceDetail detail) {
		try {
			DAOUtil.delete(this, detail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <BalanceDetail> void insert(List<BalanceDetail> list) {
		// TODO Auto-generated method stub
	}

	@Override
	public <BalanceDetail> void update(List<BalanceDetail> list) {
		// TODO Auto-generated method stub
	}

	@Override
	public <BalanceDetail> void delete(List<BalanceDetail> list) {
		// TODO Auto-generated method stub
	}
}