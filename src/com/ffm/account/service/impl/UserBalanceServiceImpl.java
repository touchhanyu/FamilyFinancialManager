package com.ffm.account.service.impl;

import com.ffm.account.dao.UserBalanceDAO;
import com.ffm.account.entity.UserBalance;
import com.ffm.account.service.UserBalanceService;
import com.ffm.common.util.PKUtil;

public class UserBalanceServiceImpl implements UserBalanceService {
	private UserBalanceDAO dao;

	public void setDao(UserBalanceDAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(UserBalance userBalance) {
		// TODO Auto-generated method stub
		Integer id = PKUtil.generatePK("ACCOUNT_USERBALANCE");
		userBalance.setId(id);
		this.dao.insert(userBalance);
	}

	@Override
	public void modify(UserBalance userBalance) {
		// TODO Auto-generated method stub
		this.dao.update(userBalance);
	}
}