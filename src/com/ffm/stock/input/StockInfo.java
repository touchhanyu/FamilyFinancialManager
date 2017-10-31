package com.ffm.stock.input;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ffm.common.db.SQLUtil;
import com.ffm.common.util.COMUtil;
import com.ffm.common.util.CalUtil;
import com.ffm.common.util.PropertiesUtil;
import com.ffm.common.util.RequestUtil;
import com.ffm.stock.dao.StockDAO;
import com.ffm.stock.entity.Stock;

public class StockInfo {
	private static final String STOCKURL;
	private static final String APPKEY;
	static {
		Properties prop = PropertiesUtil.readProperties("/com/ffm/stock/input/res/stock.properties");
		STOCKURL = prop.getProperty("stockUrl");
		APPKEY = prop.getProperty("appKey");
	}

	private StockDAO dao;

	public void setDao(StockDAO dao) {
		this.dao = dao;
	}

	public void requestStockById(String id) {
		String url = STOCKURL + "?gid=" + id + "&key=" + APPKEY;
		String json = RequestUtil.requestString(url, "GET");
		System.out.println(json);
	}

	/**
	 * 
	 * @param type 0:上证指数\n1:深证指数
	 */
	public void requestStockByType(int type) {
		String url = STOCKURL + "?type=" + type + "&key=" + APPKEY;
		String json = RequestUtil.requestString(url, "GET");
		System.out.println(json);
	}

	/**
	 * 计算利润
	 * 
	 * @param buyPri 买入价格
	 * @param num 买入数量
	 * @param nowPri 当前价格
	 * @return
	 */
	public double calProfit(double buyPri, int num, double nowPri) {
		double margin = CalUtil.sub(nowPri, buyPri);
		return CalUtil.mul(margin, num).doubleValue();
	}

	public void updateStockDailyByAPI() {
		String currentDate = COMUtil.currentDate().replace("-", "");
		StringBuffer sb = new StringBuffer();
		sb.append("insert into FFM_STOCK_STOCKHISTROY select '").append(currentDate);
		sb.append("'||GID,GID,NAME,TODAYSTARTPRI,YESTODENDPRI,NOWPRI,TODAYMAX,TODAYMIN,");
		sb.append("COMPETITIVEPRI,RESERVEPRI,TRANUMBER,TRAAMOUNT,BUY1,BUY1PRI,BUY2,BUY2PRI,BUY3,BUY3PRI,BUY4,BUY4PRI,BUY5,BUY5PRI,");
		sb.append("SELL1,SELL1PRI,SELL2,SELL2PRI,SELL3,SELL3PRI,SELL4,SELL4PRI,SELL5,SELL5PRI,");
		sb.append("PUBLICDATE,PUBLICTIME,MAKEDATE,MAKETIME from FFM_STOCK_STOCK");
		SQLUtil.update(sb.toString(), null, null);
		String sql = "select GTYPE||GID from FFM_STOCK_COMPANY order by id";
		List<Object[]> list = SQLUtil.query(sql);
		String url = STOCKURL + "?key=" + APPKEY + "&gid=";
		String[] jsons = new String[list.size()];
		System.out.println("start request...");
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = list.get(i);
			String gid = (String) obj[0];
			String urlStr = url + gid;
			String json = RequestUtil.requestString(urlStr, RequestUtil.GET);
			jsons[i] = json;
			double percent = CalUtil.div(i, list.size());
			percent = CalUtil.round(percent, 2);
			if (i % 50 == 0)
				System.out.println(percent * 100 + "%");
		}
		System.out.println("start database operate...");
		String delSql = "delete FFM_STOCK_STOCK";
		SQLUtil.update(delSql, null, this.dao);
		List<Stock> stocks = this.parseStock(jsons);
		StockDAO dao = new StockDAO();
		dao.insert(stocks);
		System.out.println("insert end...");
	}

	/**
	 * 解析返回后的数据
	 * 
	 * @param jsons
	 * @return
	 */
	public List<Stock> parseStock(String[] jsons) {
		ArrayList<Stock> list = new ArrayList<Stock>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat df = new DecimalFormat("#.##");
		try {
			for (int i = 0; i < jsons.length; i++) {
				String json = jsons[i];
				JSONObject jsonObject = JSONObject.fromObject(json);
				String resultcode = jsonObject.getString("resultcode");
				if ("200".equals(resultcode)) {// 返回码正常
					JSONArray jsonArray = jsonObject.getJSONArray("result");
					JSONObject result = jsonArray.getJSONObject(0);
					JSONObject data = result.getJSONObject("data");
					Stock stock = new Stock();
					stock.setGid(data.getString("gid").replace("sh", "").replace("sz", ""));
					stock.setName(data.getString("name"));
					stock.setTodayStartPri(df.parse(data.getString("todayStartPri")).doubleValue());
					stock.setYestodEndPri(df.parse(data.getString("yestodEndPri")).doubleValue());
					stock.setNowPri(df.parse(data.getString("nowPri")).doubleValue());
					stock.setTodayMax(df.parse(data.getString("todayMax")).doubleValue());
					stock.setTodayMin(df.parse(data.getString("todayMin")).doubleValue());
					stock.setCompetitivePri(df.parse(data.getString("competitivePri")).doubleValue());
					stock.setReservePri(df.parse(data.getString("reservePri")).doubleValue());
					stock.setTraNumber(new BigDecimal(data.getString("traNumber")));
					stock.setTraAmount(new BigDecimal(data.getString("traAmount")));
					stock.setBuyOne(new Integer(data.getString("buyOne")));
					stock.setBuyOnePri(df.parse(data.getString("buyOnePri")).doubleValue());
					stock.setBuyTwo(new Integer(data.getString("buyTwo")));
					stock.setBuyTwoPri(df.parse(data.getString("buyTwoPri")).doubleValue());
					stock.setBuyThree(new Integer(data.getString("buyThree")));
					stock.setBuyThreePri(df.parse(data.getString("buyThreePri")).doubleValue());
					stock.setBuyFour(new Integer(data.getString("buyFour")));
					stock.setBuyFourPri(df.parse(data.getString("buyFourPri")).doubleValue());
					stock.setBuyFive(new Integer(data.getString("buyFive")));
					stock.setBuyFivePri(df.parse(data.getString("buyFivePri")).doubleValue());
					stock.setSellOne(new Integer(data.getString("sellOne")));
					stock.setSellOnePri(df.parse(data.getString("sellOnePri")).doubleValue());
					stock.setSellTwo(new Integer(data.getString("sellTwo")));
					stock.setSellTwoPri(df.parse(data.getString("sellTwoPri")).doubleValue());
					stock.setSellThree(new Integer(data.getString("sellThree")));
					stock.setSellThreePri(df.parse(data.getString("sellThreePri")).doubleValue());
					stock.setSellFour(new Integer(data.getString("sellFour")));
					stock.setSellFourPri(df.parse(data.getString("sellFourPri")).doubleValue());
					stock.setSellFive(new Integer(data.getString("sellFive")));
					stock.setSellFivePri(df.parse(data.getString("sellFivePri")).doubleValue());
					Date date = sdf.parse(data.getString("date"));
					stock.setDate(date);
					stock.setTime(data.getString("time"));
					stock.setMakeDate(new Date());
					stock.setMakeTime(COMUtil.currentTime());
					list.add(stock);
//					JSONObject dapandata = jsonArray.getJSONObject(1);
//					JSONObject gopicture = jsonArray.getJSONObject(2);
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}