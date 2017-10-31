package com.ffm.currency.service;

import java.util.List;

import com.ffm.currency.entity.Currency;

public interface CurrencyService {
	/**
	 * 请求远程数据
	 * 
	 * @return
	 */
	List<Currency> requestData();

	/**
	 * 新增外汇数据
	 * 
	 * @param list
	 */
	void addCurrency(List<Currency> list);
}