package com.ffm.currency.dao;

import java.sql.SQLException;
import java.util.List;

import com.ffm.common.db.DAO;
import com.ffm.common.db.DAOUtil;

public class CurrencyDAO extends DAO {

	public CurrencyDAO() {
		super.insSqlTemp = "insert into FI_CURRENCY_PRICE(ID,NAME,FBUYFRI,MBUYPRI,FSELLPRI,MSELLPRI,BANKCONVERSIONPRI,PUBLICDATE,PUBLICTIME,MAKEDATE,MAKETIME) values(id?,name?,fBuyPri?,mBuyPri?,fSellPri?,mSellPri?,bankConversionPri?,date?,time?,makeDate?,makeTime?)";
		super.updSqlTemp = "";
		super.insSql = "insert into FI_CURRENCY_PRICE(ID,NAME,FBUYFRI,MBUYPRI,FSELLPRI,MSELLPRI,BANKCONVERSIONPRI,PUBLICDATE,PUBLICTIME,MAKEDATE,MAKETIME) values(?,?,?,?,?,?,?,?,?,?,?)";
		super.updSql = "";
		super.delSql = "";
	}

	@Override
	public void insert(Object obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public <Currency> void insert(List<Currency> list) {
		try {
			DAOUtil.insert(this, list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <Currency> void update(List<Currency> list) {
		// TODO Auto-generated method stub
	}

	@Override
	public <Currency> void delete(List<Currency> list) {
		// TODO Auto-generated method stub
	}
}