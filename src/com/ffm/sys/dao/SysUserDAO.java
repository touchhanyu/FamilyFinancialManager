package com.ffm.sys.dao;

import java.sql.SQLException;
import java.util.List;

import com.ffm.common.db.DAO;
import com.ffm.common.db.DAOUtil;

public class SysUserDAO extends DAO {
	public SysUserDAO() {
		super.insSqlTemp = "insert into FFM_SYS_USER(ID,NAME,PASSWORD,REALNAME,IDCARD,CARDTYPE,SEX,NATIONALID,CULTRUE,TELPHONE,PHONE,EMAIL,QQ,CITYID,HOMEADDRESS,OFFICEADDRESS,AUTHORITY,MAKEDATE,MAKETIME,MODIFYDATE,MODIFYTIME,OPERID) values(id?,name?,password?,realName?,idCard?,cardType?,sex?,nationalId?,cultrue?,telphone?,phone?,email?,qq?,cityId?,homeAddress?,officeAddress?,authority?,makeDate?,makeTime?,modifyDate?,modifyTime?,operId?)";
		super.updSqlTemp = "update FFM_SYS_USER set NAME=name?,PASSWORD=password?,REALNAME=realName?,IDCARD=idCard?,CARDTYPE=cardType?,SEX=sex?,NATIONALID=nationalId?,CULTRUE=cultrue?,TELPHONE=telphone?,PHONE=phone?,EMAIL=email?,QQ=qq?,CITYID=cityId?,HOMEADDRESS=homeAddress?,OFFICEADDRESS=officeAddress?,AUTHORITY=authority?,MODIFYDATE=modifyDate?,MODIFYTIME=modifyTime?,OPERID=operId? where ID=id?";
		super.delSqlTemp = "delete FFM_SYS_USER where ID=id?";
		super.insSql = "insert into FFM_SYS_USER(ID,NAME,PASSWORD,REALNAME,IDCARD,CARDTYPE,SEX,NATIONALID,CULTRUE,TELPHONE,PHONE,EMAIL,QQ,CITYID,HOMEADDRESS,OFFICEADDRESS,AUTHORITY,MAKEDATE,MAKETIME,MODIFYDATE,MODIFYTIME,OPERID) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		super.updSql = "update FFM_SYS_USER set NAME=?,PASSWORD=?,REALNAME=?,IDCARD=?,CARDTYPE=?,SEX=?,NATIONALID=?,CULTRUE=?,TELPHONE=?,PHONE=?,EMAIL=?,QQ=?,CITYID=?,HOMEADDRESS=?,OFFICEADDRESS=?,AUTHORITY=?,MODIFYDATE=?,MODIFYTIME=?,OPERID=? where ID=?";
		super.delSql = "delete FFM_SYS_USER where ID=?";
	}

	@Override
	public <SysUser> void insert(SysUser user) {
		try {
			DAOUtil.insert(this, user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <SysUser> void update(SysUser obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public <SysUser> void delete(SysUser obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public <SysUser> void insert(List<SysUser> list) {
		// TODO Auto-generated method stub
	}

	@Override
	public <SysUser> void update(List<SysUser> list) {
		// TODO Auto-generated method stub
	}

	@Override
	public <SysUser> void delete(List<SysUser> list) {
		// TODO Auto-generated method stub
	}
}