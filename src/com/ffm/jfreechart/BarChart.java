package com.ffm.jfreechart;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.ffm.common.util.COMUtil;

public class BarChart {
	/* 图表标题 */
	private String title;
	/* x轴标题 */
	private String xLabel;
	/* y轴标题 */
	private String yLabel;
	/* 图表数据 */
	private List<ChartData> data;

	public BarChart(String title, String xLabel, String yLabel, List<ChartData> data) {
		this.title = title;
		this.xLabel = xLabel;
		this.yLabel = yLabel;
		this.data = data;
	}

	/**
	 * 柱状图数据集
	 * 
	 * @return
	 */
	public CategoryDataset makeDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < data.size(); i++) {
			ChartData chartData = this.data.get(i);
			dataset.addValue(chartData.getValue(), chartData.getType(), chartData.getName());
		}
		return dataset;
	}

	/**
	 * 输出JFreeChart
	 * 
	 * @param path 文件全路径
	 * @param chart
	 */
	public void outputPic(String path, JFreeChart chart) {
		this.outputPic(path, chart, 600, 800);
	}

	public void outputPic(String path, JFreeChart chart, int height, int weight) {
		COMUtil.mkDir(path);// 确认目标文件夹是否存在
		OutputStream os = null;
		try {
			os = new FileOutputStream(path);
			ChartUtilities.writeChartAsJPEG(os, chart, weight, height);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void makeBarChart(String path) {
		JFreeChart barChart = ChartFactory.createBarChart(this.title, this.xLabel, this.yLabel, makeDataset());
		/**
		 * VALUE_TEXT_ANTIALIAS_OFF表示将文字的抗锯齿关闭,
		 * 使用的关闭抗锯齿后，字体尽量选择12到14号的宋体字,这样文字最清晰好看
		 */
		barChart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		barChart.setBackgroundPaint(Color.white);
		LegendTitle legend = barChart.getLegend();
		legend.setItemFont(new Font("华文新魏", Font.PLAIN, 16));
		Font titleFont = new Font("宋体", Font.BOLD, 14);
		barChart.setTitle(new TextTitle(this.title, titleFont));
		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
//		plot.setBackgroundPaint(Color.yellow);
		CategoryAxis categoryAxis = plot.getDomainAxis();// 横坐标轴
		categoryAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
		categoryAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
		categoryAxis.setLabel("日期");
		categoryAxis.setLowerMargin(0.1d);
		categoryAxis.setUpperMargin(0.1d);
		ValueAxis rangeAxis = plot.getRangeAxis();// 纵坐标
		rangeAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
		rangeAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
		rangeAxis.setLabel("金额");
		rangeAxis.setUpperMargin(0.5);
		rangeAxis.setLowerMargin(0.5);
		rangeAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
		BarRenderer barRenderer = (BarRenderer) plot.getRenderer();// 中间部分
		barRenderer.setMaximumBarWidth(0.05);// 柱子宽度
		barRenderer.setBaseOutlinePaint(Color.black);// 柱子表框颜色
		barRenderer.setItemMargin(0.0);// 柱子间隔
		barRenderer.setIncludeBaseInRange(false);
		this.outputPic(path , barChart);
	}
}