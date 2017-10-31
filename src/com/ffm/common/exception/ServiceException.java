package com.ffm.common.exception;

@SuppressWarnings("serial")
public class ServiceException extends Exception {
	private String errorInfo;

	public ServiceException(String errorInfo) {
		super();
		this.errorInfo = errorInfo;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
}