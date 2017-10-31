package com.ffm.stock.input;

import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.ffm.common.db.SQLUtil;
import com.ffm.common.util.COMUtil;
import com.ffm.common.util.PropertiesUtil;
import com.ffm.common.util.RequestUtil;
import com.ffm.stock.dao.StockDAO;
import com.ffm.stock.entity.Stock;

public class StockInfo4Sina {
	/* 分时线图 */
	private static final String IMAGEURL_MIN = "/newchart/min/n/";
	/* 日K线图 */
	private static final String IMAGEURL_DAY = "/newchart/daily/n/";
	/* 周K线图 */
	private static final String IMAGEURL_WEEK = "/newchart/weekly/n/";
	/* 月K线图 */
	private static final String IMAGEURL_MON = "/newchart/monthly/n/";
	private static final String IMAGEPATH_MIN = "Min";
	private static final String IMAGEPATH_DAY = "Day";
	private static final String IMAGEPATH_WEEK = "Week";
	private static final String IMAGEPATH_MON = "Mon";
	private static final String URL;
	private static final String IMAGEURL;
	private DecimalFormat df = new DecimalFormat("#.##");
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static {
		Properties prop = PropertiesUtil.readProperties("/com/ffm/stock/input/res/stock.properties");
		URL = prop.getProperty("sinaApi");
		IMAGEURL = prop.getProperty("sinaImage");
	}

	private StockDAO dao;

	public void setDao(StockDAO dao) {
		this.dao = dao;
	}

	/**
	 * 同步股票最新信息
	 * 
	 * @throws SQLException
	 */
	public void updateStockInfoDaliy() throws SQLException {
		String sql = "select GTYPE||GID from FFM_STOCK_COMPANY order by id";
		List<Object[]> list = SQLUtil.query(sql);
		boolean flag = false;
		int i = 0;
		StringBuffer sb = new StringBuffer();
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		while (!flag) {
			Object[] data = list.get(i++);
			sb.append((String) data[0]).append(",");
			String gid = sb.toString();
			if (i >= list.size()) {
				flag = true;
				List<Stock> stockInfo = this.requestStockInfo(gid);
				stocks.addAll(stockInfo);
				break;
			}
			if (i % 20 == 0) {// 批量发送请求
				List<Stock> stockInfo = this.requestStockInfo(gid);
				stocks.addAll(stockInfo);
				sb = new StringBuffer();
			}
		}
		System.out.println("start database operate...");
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String currentDate = sdf.format(new Date());
		/* 先删除历史信息表中当天的数据 */
		String delStockHis = "delete FFM_STOCK_STOCKHISTORY where SERIALNO like '" + currentDate + "%'";
		SQLUtil.update(delStockHis, null, this.dao);
		/* 清空最新的股票信息表 */
		String delStock = "delete FFM_STOCK_STOCK";
		SQLUtil.update(delStock, null, this.dao);
		/* 同步最新的股票信息 */
		this.dao.insert(stocks);
		/* 将最新的股票信息插入历史信息表 */
		sb = new StringBuffer();
		sb.append("insert into FFM_STOCK_STOCKHISTORY select '").append(currentDate);
		sb.append("'||GID,GTYPE,GID,NAME,TODAYSTARTPRI,YESTODENDPRI,NOWPRI,TODAYMAX,TODAYMIN,");
		sb.append("COMPETITIVEPRI,RESERVEPRI,TRANUMBER,TRAAMOUNT,BUY1,BUY1PRI,BUY2,BUY2PRI,BUY3,BUY3PRI,BUY4,BUY4PRI,BUY5,BUY5PRI,");
		sb.append("SELL1,SELL1PRI,SELL2,SELL2PRI,SELL3,SELL3PRI,SELL4,SELL4PRI,SELL5,SELL5PRI,");
		sb.append("PUBLICDATE,PUBLICTIME,MAKEDATE,MAKETIME from FFM_STOCK_STOCK");
		SQLUtil.update(sb.toString(), null, this.dao);
	}

	public List<Stock> requestStockInfo(String gid) {
		String url = URL + "/list=" + gid;
		String json = RequestUtil.requestString(url, RequestUtil.GET, "GBK");
		String[] split = json.split(";");
		ArrayList<Stock> list = new ArrayList<Stock>();
		Date date = new Date();
		String time = COMUtil.currentTime();
		try {
			for (int i = 0; i < split.length; i++) {
				String data = split[i];
				String[] datas = data.split("=");
				if (datas.length == 2) {
					String stockStr = datas[1].replaceAll("\"", "");
					String[] stockInfo = stockStr.split(",");
					if (stockInfo.length < 32)
						continue;// 数据格式异常
					Stock stock = new Stock();
					stock.setGid(datas[0].replaceAll("var hq_str_", "").replace("sh", "").replace("sz", ""));
					if (datas[0].contains("sh")) {
						stock.setgType("sh");
					} else {
						stock.setgType("sz");
					}
					stock.setName(stockInfo[0]);
					stock.setTodayStartPri(df.parse(stockInfo[1]).doubleValue());
					stock.setYestodEndPri(df.parse(stockInfo[2]).doubleValue());
					stock.setNowPri(df.parse(stockInfo[3]).doubleValue());
					stock.setTodayMax(df.parse(stockInfo[4]).doubleValue());
					stock.setTodayMin(df.parse(stockInfo[5]).doubleValue());
					stock.setCompetitivePri(df.parse(stockInfo[6]).doubleValue());
					stock.setReservePri(df.parse(stockInfo[7]).doubleValue());
					stock.setTraNumber(new BigDecimal(stockInfo[8]));
					stock.setTraAmount(new BigDecimal(stockInfo[9]));// 成交量超出int范围
					stock.setBuyOne(new Integer(stockInfo[10]));
					stock.setBuyOnePri(df.parse(stockInfo[11]).doubleValue());
					stock.setBuyTwo(new Integer(stockInfo[12]));
					stock.setBuyTwoPri(df.parse(stockInfo[13]).doubleValue());
					stock.setBuyThree(new Integer(stockInfo[14]));
					stock.setBuyThreePri(df.parse(stockInfo[15]).doubleValue());
					stock.setBuyFour(new Integer(stockInfo[16]));
					stock.setBuyFourPri(df.parse(stockInfo[17]).doubleValue());
					stock.setBuyFive(new Integer(stockInfo[18]));
					stock.setBuyFivePri(df.parse(stockInfo[19]).doubleValue());
					stock.setSellOne(new Integer(stockInfo[20]));
					stock.setSellOnePri(df.parse(stockInfo[21]).doubleValue());
					stock.setSellTwo(new Integer(stockInfo[22]));
					stock.setSellTwoPri(df.parse(stockInfo[23]).doubleValue());
					stock.setSellThree(new Integer(stockInfo[24]));
					stock.setSellThreePri(df.parse(stockInfo[25]).doubleValue());
					stock.setSellFour(new Integer(stockInfo[26]));
					stock.setSellFourPri(df.parse(stockInfo[27]).doubleValue());
					stock.setSellFive(new Integer(stockInfo[28]));
					stock.setSellFivePri(df.parse(stockInfo[29]).doubleValue());
					stock.setDate(this.sdf.parse(stockInfo[30]));
					stock.setTime(stockInfo[31]);
					stock.setMakeDate(date);
					stock.setMakeTime(time);
					list.add(stock);
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @param type 图片类型 0:分1:日 2:周 3:月
	 * @param gid
	 */
	public void requestStockImage(int type, String gid) {
		String path,filePathType;
		if (type == 0) {
			path = IMAGEURL_MIN;
			filePathType = IMAGEPATH_MIN;
		} else if (type == 1) {
			path = IMAGEURL_DAY;
			filePathType = IMAGEPATH_DAY;
		} else if (type == 2) {
			path = IMAGEURL_WEEK;
			filePathType = IMAGEPATH_WEEK;
		} else if (type == 3) {
			path = IMAGEURL_MON;
			filePathType = IMAGEPATH_MON;
		} else {
			return;
		}
		String url = IMAGEURL + path + gid + ".gif";
		String outPath = COMUtil.sysPath() + "Stock/Image/" + COMUtil.currentDate() + File.separator + filePathType;
		String fileName = gid + ".gif";
		File file = new File(outPath);
		if (!file.exists()) {
			file.mkdirs();// 创建文件路径
		}
		RequestUtil.requestImg(url, RequestUtil.GET, outPath, fileName);
	}
}