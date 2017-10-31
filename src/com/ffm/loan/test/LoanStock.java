package com.ffm.loan.test;

import org.junit.Test;

import com.ffm.loan.service.LoanAmortizationService;
import com.ffm.loan.service.impl.LoanAmortizationServiceImpl;

public class LoanStock {

	@Test
	public void testloan() {
		LoanAmortizationService service = new LoanAmortizationServiceImpl();
		service.calBalance();
	}
}