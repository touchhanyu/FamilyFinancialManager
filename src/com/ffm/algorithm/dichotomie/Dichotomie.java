package com.ffm.algorithm.dichotomie;

import java.util.Map;

import com.ffm.common.util.CalUtil;

public abstract class Dichotomie {
	/* 二分精度 */
	private Double accu;

	public Double getAccu() {
		return accu;
	}

	public void setAccu(Double accu) {
		this.accu = accu;
	}

	/**
	 * 二分法求值
	 * 
	 * @param max 区间最大值
	 * @param min 区间最小值
	 * @param data 业务数据
	 * @return 二分次数
	 * @throws Exception
	 */
	public int binarySearch(double max, double min, Map<String, Object> data)
			throws Exception {
		if (this.accu == null)
			throw new Exception("未设置精度！");
		double lastMid = min;
		int count = 0;
		while (true) {
			double mid = CalUtil.div(CalUtil.add(max, min).doubleValue(), 2);
			if (Math.abs(CalUtil.sub(mid, lastMid)) < this.accu)// f(p(i))-f(p(i-1))<ε
				break;
			lastMid = mid;
			double y1 = this.function(mid, data);
			if (Math.abs(y1) < this.accu)// |f(p)|<ε
				break;
			double y0 = this.function(min, data);
			double result = CalUtil.mul(y0, y1).doubleValue();
			if (result > 0) {// f(p(i))*f(a(i))>0
				min = mid;
			} else if (result < 0) {// f(p(i))*f(a(i))<0
				max = mid;
			}
			if (count > 99999)
				throw new Exception("二分次数过多，请重新设置精度");
		}
		return count;
	}

	public abstract double function(double x, Map<String, Object> data);
}