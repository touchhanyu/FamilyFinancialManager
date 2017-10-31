package com.ffm.loan.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ffm.algorithm.dichotomie.Dichotomie;
import com.ffm.common.util.CalUtil;
import com.ffm.loan.entity.AmortizationSchedule;
import com.ffm.loan.service.LoanAmortizationService;

public class LoanAmortizationServiceImpl implements LoanAmortizationService {
	private AmortizationSchedule[] schedules;
	private List<AmortizationSchedule> list;

	@Override
	public int calTerm(String loanDate, String endDate) {
		// TODO Auto-generated method stub
		int term = 0;
		String[] temp1 = loanDate.split("-");
		String[] temp2 = endDate.split("-");
		if (temp1.length != 3 || temp2.length != 3) {
			return 0;
		}
		int loanYear = Integer.parseInt(temp1[0]);
		int loanMonth = Integer.parseInt(temp1[1]);
		int loanDay = Integer.parseInt(temp1[2]);
		int endYear = Integer.parseInt(temp2[0]);
		int endMonth = Integer.parseInt(temp2[1]);
		int endDay = Integer.parseInt(temp2[2]);
		int y = endYear - loanYear;
		int d = endDay - loanDay;
		if (d > 0) {// 结束日期大于开始日期
			endMonth++;
		}
		if (y > 0) {// 不同年
			term = (12 - loanMonth) + 12 * (y - 1) + endMonth;
		} else {// 同年
			term = endMonth - loanMonth;
		}
		return term;
	}

	@Override
	public void calBalance() {
		// TODO Auto-generated method stub
		double rate = 0.09;
//		this.calByDichotomie(280000d, rate, 1, new Date("2016/01/12"));
//		this.printInfo(this.schedules);
		this.calByDichotomie(10000000d, rate, new Date("2016/05/13"), new Date("2017/05/13"), "");
		this.printInfo(this.list);
	}

	/**
	 * 二分法计算
	 * 
	 * @param amount
	 * @param rate
	 * @param years
	 * @param startDate
	 */
	public void calByDichotomie(final double amount, final double rate, final int years, final Date startDate) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("amount", amount);
		data.put("rate", rate);
		data.put("years", years);
		data.put("startDate", startDate);
		Dichotomie dichotomie = new Dichotomie() {
			@Override
			public double function(double x, Map<String, Object> data) {
				// TODO Auto-generated method stub
				AmortizationSchedule[] schedules = buildSchedule(amount, rate, years, startDate, x);
				AmortizationSchedule schedule = schedules[schedules.length - 1];
				return schedule.getBalance();
			}
		};
		dichotomie.setAccu(0.0001);
		try {
			dichotomie.binarySearch(amount, 0, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.printInfo(this.schedules);
	}

	/**
	 * 二分法计算
	 * 
	 * @param amount
	 * @param rate
	 * @param loanDate
	 * @param endDate
	 * @param dueDate
	 *            每月还款日
	 */
	public void calByDichotomie(final double amount, final double rate, final Date loanDate, final Date endDate, final String dueDate) {
		Dichotomie dichotomie = new Dichotomie() {
			@Override
			public double function(double x, Map<String, Object> data) {
				// TODO Auto-generated method stub
				List<AmortizationSchedule> list = buildSchedule(amount, rate, loanDate, endDate, x);
				AmortizationSchedule schedule = list.get(list.size() - 1);
				return schedule.getBalance();
			}
		};
		dichotomie.setAccu(0.0001);
		try {
			dichotomie.binarySearch(amount, 0, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 生成还款计划表
	 * 
	 * @param amount
	 * @param rate
	 * @param years
	 * @param startDate
	 * @param payment
	 * @return
	 */
	public AmortizationSchedule[] buildSchedule(double amount, double rate, int years, Date startDate, double payment) {
		long daytime = 1000l * 3600l * 24l;
		int term = years * 12;
		double balance = amount;
		Calendar calendar = Calendar.getInstance();
		Date dueDate = startDate;
		AmortizationSchedule[] schedules = new AmortizationSchedule[term];
		for (int i = 1; i <= schedules.length; i++) {
			long time = dueDate.getTime();
			calendar.setTime(startDate);
			calendar.add(Calendar.MONTH, i);
			dueDate = calendar.getTime();
			double day = Math.abs(Math.ceil((dueDate.getTime() - time) / daytime));// 本次利息计算天数
			double interest = CalUtil.mul(CalUtil.mul(balance, rate).doubleValue(), day).doubleValue();// 当月利息
			double principal = CalUtil.sub(payment, interest);
			balance = CalUtil.sub(balance, principal);
			AmortizationSchedule schedule = new AmortizationSchedule();
			schedule.setNo(i);
			schedule.setDueDate(dueDate);
			schedule.setPayment(payment);
			schedule.setInterest(interest);
			schedule.setPrincipal(principal);
			schedule.setBalance(balance);
			schedules[i - 1] = schedule;
		}
		this.schedules = schedules;
		return schedules;
	}

	/**
	 * 生成还款计划表
	 * 
	 * @param amount
	 * @param rate 利率
	 * @param loanDate 贷款开始日期
	 * @param endDate
	 * @param payment 月供
	 * @return
	 */
	public List<AmortizationSchedule> buildSchedule(double amount, double rate, Date loanDate, Date endDate, double payment) {
		long daytime = 1000l * 3600l * 24l;
		rate = CalUtil.div(rate, 365, 11);// 日利率
		double balance = amount;// 剩余本金
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(loanDate);
		List<AmortizationSchedule> list = new ArrayList<AmortizationSchedule>();
		int i = 1;// 期数
		int count = 0;
		while (true) {
			calendar.add(Calendar.MONTH, 1);
			Date date = calendar.getTime();// 还款日期
			if (date.getTime() - endDate.getTime() >= 0) {// 超过终止日期
				date = endDate;// 最后一期还款日
				count++;
			}
			if (count > 1)
				break;
			double day = Math.ceil((date.getTime() - loanDate.getTime()) / daytime);// 本次计息天数
			double interest = CalUtil.mul(CalUtil.mul(balance, rate).doubleValue(), day).doubleValue();// 当期利息
			double principal = CalUtil.sub(payment, interest);// 当期还款本金
			balance = CalUtil.sub(balance, principal);// 计算剩余本金
			AmortizationSchedule schedule = new AmortizationSchedule();
			schedule.setNo(i++);
			schedule.setDueDate(date);
			schedule.setPayment(payment);
			schedule.setInterest(interest);
			schedule.setPrincipal(principal);
			schedule.setBalance(balance);
			list.add(schedule);
			loanDate = date;
		}
		this.list = list;
		return list;
	}

	public Date calNextDueDate(String period) {
		return null;
	}

	/**
	 * 向控制台打印还款计划表
	 * 
	 * @param schedules
	 */
	public void printInfo(AmortizationSchedule[] schedules) {
		System.out.println("No\t\tDueDate\t\t\tPaymentDue\t\tInterest\t\tPrincipal\t\tBalance");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < schedules.length; i++) {
			AmortizationSchedule as = schedules[i];
			System.out.print(CalUtil.round(as.getNo(), CalUtil.FORMAT_NORMAL) + "\t\t");
			System.out.print(sdf.format(as.getDueDate()) + "\t\t");
			System.out.print(CalUtil.round(as.getPayment(), CalUtil.FORMAT_CURRENCY) + "\t\t");
			System.out.print(CalUtil.round(as.getInterest(), CalUtil.FORMAT_CURRENCY) + "\t\t");
			System.out.print(CalUtil.round(as.getPrincipal(), CalUtil.FORMAT_CURRENCY) + "\t\t");
			System.out.println(CalUtil.round(as.getBalance(), CalUtil.FORMAT_CURRENCY));
		}
	}

	public void printInfo(List<AmortizationSchedule> schedules) {
		System.out.println("No\t\tDueDate\t\t\tPaymentDue\t\tInterest\t\tPrincipal\t\tBalance");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < this.list.size(); i++) {
			AmortizationSchedule as = this.list.get(i);
			System.out.print(CalUtil.round(as.getNo(), CalUtil.FORMAT_NORMAL) + "\t\t");
			System.out.print(sdf.format(as.getDueDate()) + "\t\t");
			System.out.print(CalUtil.round(as.getPayment(), CalUtil.FORMAT_CURRENCY) + "\t\t");
			System.out.print(CalUtil.round(as.getInterest(), CalUtil.FORMAT_CURRENCY) + "\t\t");
			System.out.print(CalUtil.round(as.getPrincipal(), CalUtil.FORMAT_CURRENCY) + "\t\t");
			System.out.println(CalUtil.round(as.getBalance(), CalUtil.FORMAT_CURRENCY));
		}
	}
}