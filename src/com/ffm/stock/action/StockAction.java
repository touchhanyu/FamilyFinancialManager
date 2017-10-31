package com.ffm.stock.action;

import java.io.IOException;
import java.util.List;

import com.ffm.common.action.BaseAction;
import com.ffm.common.entity.DataGrid;
import com.ffm.common.entity.PropertyGrid;
import com.ffm.stock.entity.Company;
import com.ffm.stock.entity.Stock;
import com.ffm.stock.service.StockService;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class StockAction extends BaseAction {
	private String q;
	private int rows;
	private int page;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	private StockService stockService;

	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}

	public void listStockInfo() throws IOException {
		List<Stock> list = this.stockService.queryStockInfoByPage(page, rows);
		int max = this.stockService.queryStockInfoMax();
		DataGrid grid = new DataGrid();
		grid.setTotal(max);
		grid.setRows(list);
		Gson gson = new Gson();
		String json = gson.toJson(grid);
		super.outputJSON(json);
	}

	public void findStockName() throws Exception {
		// TODO Auto-generated method stub
		if (this.q == null || "".equals(this.q.trim()))
			return;
		List<Company> list = this.stockService.queryStockByPinyin(this.q);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		super.outputJSON(json);
	}

	/**
	 * 查询今日股票信息
	 * 
	 * @throws IOException
	 */
	public void queryTodayStock() throws IOException {
		DataGrid grid = this.stockService.queryTodayStock(this.rows, this.page, this.q);
		Gson gson = new Gson();
		String json = gson.toJson(grid);
		super.outputJSON(json);
	}

	/**
	 * 查询今日股票明细信息
	 * 
	 * @throws IOException
	 */
	public void queryTodayStockDetail() throws IOException {
		String json = "";
		if (this.q != null && !"".equals(this.q)) {
			PropertyGrid grid = this.stockService.queryTodayStockDetail(this.q);
			Gson gson = new Gson();
			json = gson.toJson(grid);
		}
		super.outputJSON(json);
	}

	public void queryStockHistory() throws IOException {
		String json = this.stockService.queryStockHistory(this.q, null, null, this.rows, this.page);
		super.outputJSON(json);
	}
}