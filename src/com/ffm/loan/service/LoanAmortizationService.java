package com.ffm.loan.service;

public interface LoanAmortizationService {
	/**
	 * 计算总期数
	 * 
	 * @param loanDate
	 * @param endDate
	 * @return
	 */
	int calTerm(String loanDate, String endDate);

	void calBalance();
}