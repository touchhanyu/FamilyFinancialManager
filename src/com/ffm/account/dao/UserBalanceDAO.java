package com.ffm.account.dao;

import java.sql.SQLException;
import java.util.List;

import com.ffm.common.db.DAO;
import com.ffm.common.db.DAOUtil;

public class UserBalanceDAO extends DAO {
	public UserBalanceDAO() {
		super.insSqlTemp = "insert into FFM_ACCOUNT_USERBALANCE(ID,TYPE,WORTH,USERID,MAKEDATE,MAKETIME,OPERID) values(id?,type?,worth?,userId?,makeDate?,makeTime?,operId?)";
		super.updSqlTemp = "update FFM_ACCOUNT_USERBALANCE set TYPE=type?,WORTH=worth?,MAKEDATE=makeDate?,MAKETIME=makeTime?,OPERID=operId? where ID=id?";
		super.insSql = "insert into FFM_ACCOUNT_USERBALANCE(ID,TYPE,WORTH,USERID,MAKEDATE,MAKETIME,OPERID) values(?,?,?,?,?,?,?)";
		super.updSql = "update FFM_ACCOUNT_USERBALANCE set TYPE=?,WORTH=?,MAKEDATE=?,MAKETIME=?,OPERID=? where ID=id?";
		super.delSql = "delete FFM_ACCOUNT_USERBALANCE WHERE ID=?";
	}

	@Override
	public <UserBalance> void insert(UserBalance balance) {
		try {
			DAOUtil.insert(this, balance);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <UserBalance> void update(UserBalance balance) {
		try {
			DAOUtil.update(this, balance);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <UserBalance> void delete(UserBalance ub) {
		// TODO Auto-generated method stub
	}

	@Override
	public <UserBalance> void insert(List<UserBalance> list) {
		// TODO Auto-generated method stub
	}

	@Override
	public <UserBalance> void update(List<UserBalance> list) {
		// TODO Auto-generated method stub
	}

	@Override
	public <UserBalance> void delete(List<UserBalance> list) {
		// TODO Auto-generated method stub
	}
}