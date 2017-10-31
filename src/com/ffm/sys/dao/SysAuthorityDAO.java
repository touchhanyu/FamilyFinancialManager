package com.ffm.sys.dao;

import java.sql.SQLException;
import java.util.List;

import com.ffm.common.db.DAO;
import com.ffm.common.db.DAOUtil;

public class SysAuthorityDAO extends DAO {
	public SysAuthorityDAO() {
		super.insSqlTemp = "insert into FFM_SYS_AUTHORITY(USERID,MENUID,MAKEDATE,MAKETIME,OPERID) values(userId?,menuId?,makeDate?,makeTime?,operId?)";
		super.updSqlTemp = "update FFM_SYS_AUTHORITY set MAKEDATE=makeDate?,MAKETIME=makeTime?,OPERID=operId? where USERID=userId? and MENUID=menuId?";
		super.delSqlTemp = "delete FFM_SYS_AUTHORITY where USERID=userId? and MENUID=menuId?";
		super.insSql = "insert into FFM_SYS_AUTHORITY(USERID,MENUID,MAKEDATE,MAKETIME,OPERID) values(?,?,?,?,?)";
		super.updSql = "update FFM_SYS_AUTHORITY set MAKEDATE=?,MAKETIME=?,OPERID=? where USERID=? and MENUID=?";
		super.delSql = "delete FFM_SYS_AUTHORITY where USERID=? and MENUID=?";
	}

	@Override
	public <SysAuthority> void insert(SysAuthority authority) {
		// TODO Auto-generated method stub
		try {
			DAOUtil.insert(this, authority);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <SysAuthority> void update(SysAuthority authority) {
		// TODO Auto-generated method stub
		try {
			DAOUtil.update(this, authority);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <SysAuthority> void delete(SysAuthority authority) {
		// TODO Auto-generated method stub
		try {
			DAOUtil.delete(this, authority);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <SysAuthority> void insert(List<SysAuthority> list) {
		// TODO Auto-generated method stub
		try {
			DAOUtil.insert(this, list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <SysAuthority> void update(List<SysAuthority> list) {
		// TODO Auto-generated method stub
		try {
			DAOUtil.update(this, list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <SysAuthority> void delete(List<SysAuthority> list) {
		// TODO Auto-generated method stub
		try {
			DAOUtil.delete(this, list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}