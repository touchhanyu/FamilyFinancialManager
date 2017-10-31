package com.ffm.account.service;

import com.ffm.account.entity.UserBalance;

public interface UserBalanceService {
	public void save(UserBalance userBalance);

	public void modify(UserBalance userBalance);
}