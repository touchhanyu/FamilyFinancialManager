package com.ffm.common.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

@SuppressWarnings("serial")
public class Log4jInit extends HttpServlet {

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		String realPath = getServletContext().getRealPath("/");
		String file = getInitParameter("log4j");
		if (file != null)
			PropertyConfigurator.configure(realPath + file);
	}

}