package com.ffm.stock.service;

import java.util.List;

import com.ffm.common.entity.DataGrid;
import com.ffm.common.entity.PropertyGrid;
import com.ffm.stock.entity.Company;
import com.ffm.stock.entity.Stock;
import com.ffm.sys.entity.SysUser;

public interface StockService {
	/**
	 * 请求远程数据
	 */
	void requestData();

	/**
	 * 查询全部股票信息
	 * 
	 * @return
	 */
	int queryStockInfoMax();

	/**
	 * 分页查询股票信息
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	List<Stock> queryStockInfoByPage(int page, int size);

	/**
	 * 根据股票名称模糊查询股票
	 * 
	 * @param name
	 * @return
	 */
	List<Company> queryStockByName(String name);

	/**
	 * 根据股票拼音模糊查询股票
	 * 
	 * @param name
	 * @return
	 */
	List<Company> queryStockByPinyin(String pinyin);

	/**
	 * 查询当前交易日的全部股票信息
	 * 
	 * @param row
	 * @param page
	 * @param param
	 * @return
	 */
	DataGrid queryTodayStock(int row, int page, String param);

	/**
	 * 查询单只股票的明细信息
	 * 
	 * @param param
	 * @return
	 */
	PropertyGrid queryTodayStockDetail(String param);

	/**
	 * 查询股票的历史信息
	 * 
	 * @param gid
	 * @param startDate
	 * @param endDate
	 * @param row
	 * @param page
	 * @return
	 */
	String queryStockHistory(String gid, String startDate, String endDate, int row, int page);

	/**
	 * 计算股票成本
	 * 
	 * @param gid
	 * @param user
	 */
	void calculateStockCost(String gid, SysUser user);
}