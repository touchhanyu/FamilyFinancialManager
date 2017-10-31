package com.ffm.account.dao;

import java.sql.SQLException;
import java.util.List;

import com.ffm.common.db.DAO;
import com.ffm.common.db.DAOUtil;

public class AssetDynamicDAO extends DAO {
	public AssetDynamicDAO() {
		super.insSqlTemp = "insert into FFM_Account_AssetDynamic(BALANCEID,DETAILID,TOTAL,NUM,AVERAGEPRICE) values(balanceId?,detailId?,total?,num?,averagePrice?)";
		super.updSqlTemp = "update FFM_Account_AssetDynamic set TOTAL=total?,NUM=num?,AVERAGEPRICE=averagePrice? where BALANCEID=balanceId? and DETAILID=detailId?";
		super.delSqlTemp = "delete FFM_Account_AssetDynamic WHERE BALANCEID=balanceId? and DETAILID=detailId?";
		super.insSql = "insert into FFM_Account_AssetDynamic(BALANCEID,DETAILID,TOTAL,NUM,AVERAGEPRICE) values(?,?,?,?,?)";
		super.updSql = "update FFM_Account_AssetDynamic set TOTAL=?,NUM=?,AVERAGEPRICE=? where BALANCEID=? and DETAILID=?";
		super.delSql = "delete FFM_Account_AssetDynamic WHERE BALANCEID=? and DETAILID=?";
	}

	@Override
	public <AssetDynamic> void insert(AssetDynamic asset) {
		// TODO Auto-generated method stub
		try {
			DAOUtil.insert(this, asset);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <AssetDynamic> void update(AssetDynamic asset) {
		// TODO Auto-generated method stub
		try {
			DAOUtil.update(this, asset);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <AssetDynamic> void delete(AssetDynamic asset) {
		// TODO Auto-generated method stub
		try {
			DAOUtil.delete(this, asset);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <AssetDynamic> void insert(List<AssetDynamic> list) {
		// TODO Auto-generated method stub
	}

	@Override
	public <AssetDynamic> void update(List<AssetDynamic> list) {
		// TODO Auto-generated method stub
	}

	@Override
	public <AssetDynamic> void delete(List<AssetDynamic> list) {
		// TODO Auto-generated method stub
	}
}