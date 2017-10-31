package com.ffm.currency.service.impl;

import java.util.List;

import com.ffm.currency.dao.CurrencyDAO;
import com.ffm.currency.entity.Currency;
import com.ffm.currency.entity.Result;
import com.ffm.currency.input.ExchangeRate;
import com.ffm.currency.service.CurrencyService;

public class CurrencyServiceImpl implements CurrencyService {
	private CurrencyDAO dao = new CurrencyDAO();

	@Override
	public List<Currency> requestData() {
		// TODO Auto-generated method stub
		ExchangeRate c = new ExchangeRate();
		Result result = c.requestRMBCurrency();
		if (result == null)
			return null;
		List<Currency> res = result.getResult();
		return res;
	}

	@Override
	public void addCurrency(List<Currency> list) {
		// TODO Auto-generated method stub
		this.dao.insert(list);
	}
}