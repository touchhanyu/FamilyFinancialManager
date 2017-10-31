package com.ffm.common.db;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.ffm.common.annotation.Column;

public class SQLUtil {
	private static Logger logger = Logger.getLogger(SQLUtil.class);

	/**
	 * 分页查询
	 * 
	 * @param sql
	 * @param cls
	 * @param row 每页条数
	 * @param page 当前页数
	 * @return
	 */
	public static <E> List<E> queryByPage(String sql, Class<E> cls, int row, int page) {
		int startPage = row * (page - 1);
		int endPage = row * page;
		StringBuffer sb = new StringBuffer();
		sb.append("select * from (select tb_a.*,rownum rn from (");
		sb.append(sql);
		sb.append(")tb_a)tb_b where rn>").append(startPage);
		sb.append(" and rn<=").append(endPage);
		return query(sb.toString(), cls);
	}

	/**
	 * 计数
	 * 
	 * @param sql
	 * @return
	 */
	public static int queryCount(String sql) {
		int count = 0;
		sql = "select count(*) from (" + sql + ")";
		List<Object[]> list = query(sql);
		if (list.size() == 1) {
			Object[] obj = list.get(0);
			BigDecimal object = (BigDecimal) obj[0];
			count = object.intValue();
		}
		return count;
	}

	/**
	 * 自定义查询
	 * 
	 * @param sql 执行sql
	 * @param cls
	 * @return
	 */
	public static <E> List<E> query(String sql, Class<E> cls) {
		Connection conn = JDBCUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<E> list = new ArrayList<E>();
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int colCount = metaData.getColumnCount();
			String[] columns = new String[colCount + 1];
			for (int i = 1; i <= colCount; i++) {// 确定列与属性对应关系
				String columnName = metaData.getColumnName(i);
				Field[] fields = cls.getDeclaredFields();
				for (int j = 0; j < fields.length; j++) {
					Field field = fields[j];
					Column column = field.getAnnotation(Column.class);
					if (column != null && columnName.equals(column.value())) {
						columns[i] = field.getName();
						break;
					}
				}
			}
			while (rs.next()) {
				E object = cls.newInstance();
				for (int i = 1; i <= colCount; i++) {
					Object obj = rs.getObject(i);
					if (columns[i] != null && obj != null) {
						Field field = cls.getDeclaredField(columns[i]);
						field.setAccessible(true);
						int type = metaData.getColumnType(i);
						if (type == Types.NUMERIC) {// 处理数值类型
							BigDecimal bd = (BigDecimal) obj;
							Type genericType = field.getGenericType();
							if ("int".equals(genericType.toString()) || genericType.toString().contains("Integer")) {
								field.set(object, bd.intValue());
							} else if ("double".equals(genericType.toString()) || genericType.toString().contains("Double")) {
								field.set(object, bd.doubleValue());
							} else if (genericType.toString().contains("BigDecimal")){
								field.set(object, bd);
							}
						} else if (type == Types.DATE){
							field.set(object, obj);
						} else if (type == Types.VARCHAR || type == Types.CHAR) {
							field.set(object, obj);
						} else {
							field.set(object, obj);
						}
					}
				}
				list.add(object);
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.realse(conn, pstm, rs);
		}
		return list;
	}

	/**
	 * 自定义查询，将查询结果放入传入的对象中
	 * 
	 * @param sql 执行sql
	 * @param obj 
	 * @return
	 */
	public static <E> List<E> query(String sql, E obj) {
		@SuppressWarnings("unchecked")
		Class<E> cls = (Class<E>) obj.getClass();
		ArrayList<E> list = new ArrayList<E>();
		Connection conn = JDBCUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int colCount = metaData.getColumnCount();
			String[] columns = new String[colCount + 1];
			Field[] temp = new Field[colCount + 1];
			int k = 0;// 记录temp数组实际长度
			/** 确定列与属性对应关系 **/
			for (int i = 1; i <= colCount; i++) {
				String columnName = metaData.getColumnName(i);
				Field[] fields = cls.getDeclaredFields();
				for (int j = 0; j < fields.length; j++) {
					Field field = fields[j];
					Column column = field.getAnnotation(Column.class);
					if (column != null && columnName.equals(column.value())) {
						columns[i] = field.getName();
						break;
					}
					field.setAccessible(true);
					Object value = field.get(obj);
					if (column == null && value != null) {
						temp[k++] = field;// 记录待复制的类属性
					}
				}
			}
			/** 封装数据 **/
			while (rs.next()) {
				E instance = cls.newInstance();
				for (int i = 1; i <= colCount; i++) {
					Object object = rs.getObject(i);
					if (columns[i] != null && object != null) {
						Field field = cls.getDeclaredField(columns[i]);
						field.setAccessible(true);
						int type = metaData.getColumnType(i);
						/** 属性赋值 **/
						if (type == Types.NUMERIC) {// 处理数值类型
							BigDecimal bd = (BigDecimal) object;
							Type genericType = field.getGenericType();
							if ("int".equals(genericType.toString()) || genericType.toString().contains("Integer")) {
								field.set(instance, bd.intValue());
							} else if ("double".equals(genericType.toString()) || genericType.toString().contains("Double")) {
								field.set(instance, bd.doubleValue());
							} else if (genericType.toString().contains("BigDecimal")){
								field.set(instance, bd);
							}
						} else if (type == Types.DATE){
							field.set(instance, object);
						} else if (type == Types.VARCHAR || type == Types.CHAR) {
							field.set(instance, object);
						} else {
							field.set(instance, object);
						}
					}
				}
				/** 处理原有属性值 **/
				for (int i = 0; i < k; i++) {
					Field field = temp[i];
					Object value = field.get(obj);// Field已经设置过访问权限
					field.set(instance, value);
				}
				list.add(instance);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.realse(conn, pstm, rs);
		}
		return list;
	}

	public static List<Object[]> query(String sql) {
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		Connection conn = JDBCUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			logger.info(sql);
			rs = pstm.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int count = metaData.getColumnCount();
			while (rs.next()) {
				Object[] objs = new Object[count];
				for (int i = 1; i <= count; i++) {
					objs[i - 1] = rs.getObject(i);
				}
				list.add(objs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.realse(conn, pstm, rs);
		}
		return list;
	}

	public static List<Map<String, Object>> query(String sql, Map<String, Object> para) {
		Map<Integer, Object> map = mappingSql(sql, para);
		Connection conn = JDBCUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			sql = sql.replaceAll("#\\w+#", "?");
			pstm = conn.prepareStatement(sql);
			Set<Entry<Integer, Object>> entrySet = map.entrySet();
			Iterator<Entry<Integer, Object>> it = entrySet.iterator();
			while (it.hasNext()) {
				Entry<Integer, Object> next = it.next();
				Integer index = next.getKey();
				Object value = next.getValue();
				pstm.setObject(index, value);
			}
			rs = pstm.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			while (rs.next()) {
				HashMap<String, Object> data = new HashMap<String, Object>();
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					data.put(metaData.getColumnName(i), rs.getObject(i));
				}
				list.add(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.realse(conn, pstm, rs);
		}
		return list;
	}

	public static void update(String sql, final Map<Integer, Object> param, DAO dao) {
		JdbcTemplate jdbcTemplate = dao.getJdbcTemplate();
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstm) throws SQLException {
				// TODO Auto-generated method stub
				DAOUtil.packageSQL(pstm, param);
			}
		});
	}

	public static void update(String sql, Map<String, Object> param) {
		Connection conn = JDBCUtil.getConn();
		update(sql, param, conn, true);
	}

	/**
	 * 
	 * @param sql
	 * @param param
	 * @param conn
	 * @param realseFlag 释放连接标志
	 */
	public static void update(String sql, Map<String, Object> param, Connection conn, boolean realseFlag) {
		if (param != null) {
			
		}
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (realseFlag) {
				JDBCUtil.realse(conn, pstm, null);// 释放连接（不考虑事务）
			} else {
				JDBCUtil.realse(null, pstm, null);// 不释放连接
			}
		}
	}

	/**
	 * 动态映射sql
	 * 
	 * @param sql
	 * @param para
	 * @return
	 */
	private static Map<Integer, Object> mappingSql(String sql, Map<String, Object> para) {
		HashMap<Integer, Object> hashMap = new HashMap<Integer, Object>();
		String regex = "#\\w+#";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(sql);
		int i = 1;// 参数所在位置
		while (matcher.find()) {
			String group = matcher.group();
			group = group.replaceAll("#", "");
			hashMap.put(i++, para.get(group));
		}
		return hashMap;
	}
}