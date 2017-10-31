package com.ffm.currency.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ffm.common.action.BaseAction;
import com.ffm.currency.entity.Currency;
import com.ffm.currency.service.CurrencyService;
import com.ffm.currency.service.impl.CurrencyServiceImpl;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class CurrencyAction extends BaseAction {
	private CurrencyService service = new CurrencyServiceImpl();

	public void queryInfo() throws IOException {
		List<Currency> data = this.service.requestData();
		Gson gson = new Gson();
		String json = gson.toJson(data);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(json);
	}
}