package com.ffm.loan.entity;

import java.util.Date;

public class AmortizationSchedule {
	/* 还款期数 */
	private int no;
	/* 还款日期 */
	private Date dueDate;
	/* 月供 */
	private double payment;
	/* 利息 */
	private double interest;
	/* 本期归还本金 */
	private double principal;
	/* 剩余本金 */
	private double balance;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String toString() {
		return "AmortizationSchedule [no=" + no + ", dueDate="
				+ dueDate.toLocaleString() + ", payment=" + payment
				+ ", interest=" + interest + ", principal=" + principal
				+ ", balance=" + balance + "]";
	}

}