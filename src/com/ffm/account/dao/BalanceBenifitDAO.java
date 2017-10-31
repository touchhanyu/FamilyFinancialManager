package com.ffm.account.dao;

import java.sql.SQLException;
import java.util.List;

import com.ffm.common.db.DAO;
import com.ffm.common.db.DAOUtil;

public class BalanceBenifitDAO extends DAO {
	public BalanceBenifitDAO() {
		super.insSqlTemp = "insert into FFM_ACCOUNT_BALANCEBENIFIT(ID,BALANCEDETAILID,DETAILID,BENIFIT,TRANDATE) values(id?,balanceDetailId?,detailId?,benifit?,tranDate?)";
		super.updSqlTemp = "update FFM_ACCOUNT_BALANCEBENIFIT set BALANCEDETAILID=balanceDetailId?,DETAILID=detailId?,BENIFIT=benifit?,TRANDATE=tranDate? where ID=id?";
		super.delSqlTemp = "delete FFM_ACCOUNT_BALANCEBENIFIT WHERE ID=id?";
		super.insSql = "insert into FFM_ACCOUNT_BALANCEBENIFIT(ID,BALANCEDETAILID,DETAILID,BENIFIT,TRANDATE) values(?,?,?,?,?)";
		super.updSql = "update FFM_ACCOUNT_BALANCEBENIFIT set BALANCEDETAILID=?,DETAILID=?,BENIFIT=?,TRANDATE=? where ID=?";
		super.delSql = "delete FFM_ACCOUNT_BALANCEBENIFIT WHERE ID=?";
	}

	@Override
	public <BalanceBenifit> void insert(BalanceBenifit benifit) {
		// TODO Auto-generated method stub
		try {
			DAOUtil.insert(this, benifit);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <BalanceBenifit> void update(BalanceBenifit benifit) {
		// TODO Auto-generated method stub
		try {
			DAOUtil.update(this, benifit);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <BalanceBenifit> void delete(BalanceBenifit benifit) {
		// TODO Auto-generated method stub
		try {
			DAOUtil.delete(this, benifit);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <BalanceBenifit> void insert(List<BalanceBenifit> list) {
		// TODO Auto-generated method stub
	}

	@Override
	public <BalanceBenifit> void update(List<BalanceBenifit> list) {
		// TODO Auto-generated method stub
	}

	@Override
	public <BalanceBenifit> void delete(List<BalanceBenifit> list) {
		// TODO Auto-generated method stub
	}
}