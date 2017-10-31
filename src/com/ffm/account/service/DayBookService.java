package com.ffm.account.service;

import com.ffm.account.entity.DayBook;
import com.ffm.common.entity.DataGrid;

public interface DayBookService {
	/**
	 * 分页查询
	 * 
	 * @param sql
	 * @param page
	 * @param size
	 * @return
	 */
	DataGrid queryByPage(String sql, int page, int size);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	DayBook queryById(String id);

	void save(DayBook dayBook);

	void modify(DayBook dayBook);

	void remove(DayBook dayBook);

	/**
	 * 根据流水账计算账户剩余资金
	 * 
	 * @param dayBook
	 * @param oper 操作标志
	 */
	void calWorth(DayBook dayBook, String oper);
}