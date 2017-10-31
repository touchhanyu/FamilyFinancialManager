package com.ffm.common.dict;

public enum TRADEDIRECTION {
	/**
	 * 买入
	 */
	BUY('B', "1", "买入"),
	/**
	 * 卖出
	 */
	SELL('S', "2", "卖出");
	private char dict;
	private String value;
	private String remark;

	private TRADEDIRECTION(char dict, String value, String remark) {
		this.dict = dict;
		this.value = value;
		this.remark = remark;
	}

	public char getDict() {
		return dict;
	}

	public void setDict(char dict) {
		this.dict = dict;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.value;
	}
}