package com.ffm.stock.dao;

import java.sql.SQLException;
import java.util.List;

import com.ffm.common.db.DAO;
import com.ffm.common.db.DAOUtil;

public class CompanyDAO extends DAO {
	public CompanyDAO() {
		super.insSqlTemp = "insert into FFM_STOCK_COMPANY(ID,NAME,PINYIN,LEGALNAME,ORGID,GID,GNAME,GTYPE,MAKEDATE,MAKETIME) values(id?,name?,pinyin?,legalName?,orgId?,gid?,gname?,gtype?,makeDate?,makeTime?)";
		super.updSqlTemp = "update FFM_STOCK_COMPANY set NAME=name?,PINYIN=pinyin?,LEGALNAME=legalName?,ORGID=orgId?,GID=gid?,GNAME=gname?,GTYPE=gtype? where ID=id?";
		super.insSql = "insert into FFM_STOCK_COMPANY(ID,NAME,PINYIN,LEGALNAME,ORGID,GID,GNAME,GTYPE,MAKEDATE,MAKETIME) values(?,?,?,?,?,?,?,?,?,?)";
		super.updSql = "update FFM_STOCK_COMPANY set NAME=?,PINYIN=?,LEGALNAME=?,ORGID=?,GID=?,GNAME=?,GTYPE=? where ID=?";
		super.delSql = "delete FFM_STOCK_COMPANY where ID=?";
	}

	@Override
	public <Company> void insert(List<Company> list) {
		try {
			DAOUtil.insert(this, list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <Company> void insert(Company obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public <Company> void update(Company obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public <Company> void delete(Company obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public <Company> void update(List<Company> list) {
		// TODO Auto-generated method stub
	}

	@Override
	public <Company> void delete(List<Company> list) {
		// TODO Auto-generated method stub
	}
}