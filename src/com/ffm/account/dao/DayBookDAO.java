package com.ffm.account.dao;

import java.sql.SQLException;
import java.util.List;

import com.ffm.common.db.DAO;
import com.ffm.common.db.DAOUtil;

public class DayBookDAO extends DAO {
	public DayBookDAO() {
		super.insSqlTemp = "insert into FFM_ACCOUNT_DAYBOOK(ID,DIRECTION,TYPE,AMOUNT,PURPOSE,REMARK,TRANDATE,TRANTIME,TRANOPER) values(id?,direction?,type?,amount?,purpose?,remark?,tranDate?,tranTime?,tranOper?)";
		super.updSqlTemp = "update FFM_ACCOUNT_DAYBOOK set DIRECTION=direction?,TYPE=type?,AMOUNT=amount?,PURPOSE=purpose?,REMARK=remark?,TRANDATE=tranDate?,TRANTIME=tranTime?,TRANOPER=tranOper? where ID=id?";
		super.delSqlTemp = "delete FFM_ACCOUNT_DAYBOOK WHERE ID=id?";
		super.insSql = "insert into FFM_ACCOUNT_DAYBOOK(ID,DIRECTION,TYPE,AMOUNT,PURPOSE,REMARK,TRANDATE,TRANTIME,TRANOPER) values(?,?,?,?,?,?,?,?,?)";
		super.updSql = "update FFM_ACCOUNT_DAYBOOK set DIRECTION=?,TYPE=?,AMOUNT=?,PURPOSE=?,REMARK=?,TRANDATE=?,TRANTIME=?,TRANOPER=? where ID=?";
		super.delSql = "delete FFM_ACCOUNT_DAYBOOK WHERE ID=?";
	}
	@Override
	public <DayBook> void insert(DayBook dayBook) {
		try {
			DAOUtil.insert(this, dayBook);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <DayBook> void update(DayBook dayBook) {
		try {
			DAOUtil.update(this, dayBook);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <DayBook> void delete(DayBook dayBook) {
		try {
			DAOUtil.delete(this, dayBook);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <DayBook> void insert(List<DayBook> list) {
		// TODO Auto-generated method stub
	}

	@Override
	public <DayBook> void update(List<DayBook> list) {
		// TODO Auto-generated method stub
	}

	@Override
	public <DayBook> void delete(List<DayBook> list) {
		// TODO Auto-generated method stub
	}
}