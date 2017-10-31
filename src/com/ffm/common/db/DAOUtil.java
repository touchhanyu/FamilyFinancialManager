package com.ffm.common.db;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.ffm.common.annotation.Column;
import com.ffm.common.annotation.Table;

public class DAOUtil {

	public static String parseEntity2SQL(Class<?> cls) {
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ");
		Table table = cls.getAnnotation(Table.class);
		sb.append(table.value());
		Field[] fields = cls.getDeclaredFields();
		StringBuffer fSb = new StringBuffer();
		StringBuffer vSb = new StringBuffer();
		for (int i = 0; i < fields.length; i++) {
			if (i != 0) {
				fSb.append(",");
				vSb.append(",");
			}
			Field field = fields[i];
			String name = field.getName();
			Column column = field.getAnnotation(Column.class);
			String columnName = column.value();
			fSb.append(columnName);
			vSb.append(name).append("?");
		}
		sb.append("(").append(fSb).append(") values(").append(vSb).append(")");
		System.out.println(sb);
		return sb.toString();
	}

	/**
	 * 从DAO中获取对象，解析属性值对应sql中的位置
	 * 
	 * @param dao
	 * @param sqlTemp
	 * @param sql
	 * @return
	 */
	public static Map<Integer, Object> parseEntity2Map(Object obj, String sqlTemp) {
		HashMap<Integer, Object> hm = null;
		try {
			if (obj == null)
				return hm;
			Field[] fields = obj.getClass().getDeclaredFields();
			String[] fieldNames = new String[fields.length];// 属性名
			Object[] fieldValues = new Object[fields.length];// 属性值
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				field.setAccessible(true);
				fieldNames[i] = field.getName() + "[?]";
				fieldValues[i] = field.get(obj);
			}
			hm = new HashMap<Integer, Object>();
			int i = 0, count = 1;
			while (true) {
				sqlTemp = sqlTemp.replaceFirst(fieldNames[i], "?");// 一次只替换一次确定具体位置
				hm.put(count++, fieldValues[i++]);// 存放属性值在sql中的对应位置
				if (i == fieldNames.length)
					i = 0;// 重置
				if (!sqlTemp.matches("^.*\\w[?]\\S*$"))
					break;// 全部属性替换完毕
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hm;
	}

	/**
	 * 从DAO中获取对象，解析属性值对应sql中的位置
	 * 
	 * @param dao
	 * @param sqlTemp
	 * @param sql
	 * @return
	 */
	public static Map<Integer, Object> parseEntity(Object obj, String sqlTemp) {
		HashMap<Integer, Object> hm = new HashMap<Integer, Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		Pattern pattern = Pattern.compile("\\w*[?]");
		Matcher matcher = pattern.matcher(sqlTemp);
		int count = 1;
		while (matcher.find()) {
			String condition = matcher.group();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				String name = field.getName();
				if ((name + "?").equals(condition)) {
					field.setAccessible(true);
					try {
						Object value = field.get(obj);
						hm.put(count++, value);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		}
		return hm;
	}

	/**
	 * 组装SQL
	 * 
	 * @param pstm
	 * @param param
	 * @return
	 */
	public static PreparedStatement packageSQL(PreparedStatement pstm, Map<Integer, Object> param) {
		try {
			if (param != null) {
				Set<Entry<Integer, Object>> entrySet = param.entrySet();
				Iterator<Entry<Integer, Object>> it = entrySet.iterator();
				while (it.hasNext()) {
					Entry<Integer, Object> entry = it.next();
					Object value = entry.getValue();
					if (value instanceof String && (value + "").matches("^[\'\"]*null[\'\"]*$"))
						value = null;
					if (value instanceof java.util.Date) {
						value = new Date(((java.util.Date) value).getTime());
					}
					pstm.setObject(entry.getKey(), value);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pstm;
	}

	public static void insert(DAO dao, Object obj) throws SQLException {
		String insSqlTemp = dao.getInsSqlTemp();
		String insSql = dao.getInsSql();
		final Map<Integer, Object> param = DAOUtil.parseEntity(obj, insSqlTemp);
		/** 调用Spring JDBCTemplate **/
		JdbcTemplate jdbcTemplate = dao.getJdbcTemplate();
		jdbcTemplate.update(insSql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstm) throws SQLException {
				// TODO Auto-generated method stub
				DAOUtil.packageSQL(pstm, param);
			}
		});
	}

	public static int[] insert(DAO dao, final List<?> list) throws SQLException {
		final String insSqlTemp = dao.getInsSqlTemp();
		String insSql = dao.getInsSql();
//		Connection conn = JDBCUtil.getConn();
//		PreparedStatement pstm = conn.prepareStatement(insSql);
//		for (int i = 0; i < list.size(); i++) {
//			Object obj = list.get(i);
//			Map<Integer, Object> param = DAOUtil.parseEntity(obj, insSqlTemp);
//			pstm = DAOUtil.packageSQL(pstm, param);
//			pstm.addBatch();
//		}
//		int[] batch = pstm.executeBatch();
//		JDBCUtil.realse(null, pstm, null);
		/** 调用Spring JDBCTemplate **/
		JdbcTemplate jdbcTemplate = dao.getJdbcTemplate();
		int[] batch = jdbcTemplate.batchUpdate(insSql, new BatchPreparedStatementSetter() {
			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return list.size();
			}

			@Override
			public void setValues(PreparedStatement pstm, int index) throws SQLException {
				// TODO Auto-generated method stub
				Object obj = list.get(index);
				Map<Integer, Object> param = DAOUtil.parseEntity(obj, insSqlTemp);
				DAOUtil.packageSQL(pstm, param);
			}
		});
		return batch;
	}

	public static int update(DAO dao, Object obj) throws SQLException {
		String updSqlTemp = dao.getUpdSqlTemp();
		String updSql = dao.getUpdSql();
		final Map<Integer, Object> param = DAOUtil.parseEntity(obj, updSqlTemp);
		/** 调用Spring JDBCTemplate **/
		JdbcTemplate jdbcTemplate = dao.getJdbcTemplate();
		int i = jdbcTemplate.update(updSql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstm) throws SQLException {
				// TODO Auto-generated method stub
				DAOUtil.packageSQL(pstm, param);
			}
		});
		return i;
	}

	public static int[] update(DAO dao, final List<?> list) throws SQLException {
		final String updSqlTemp = dao.getUpdSqlTemp();
		String updSql = dao.getUpdSql();
		/** 调用Spring JDBCTemplate **/
		JdbcTemplate jdbcTemplate = dao.getJdbcTemplate();
		int[] batch = jdbcTemplate.batchUpdate(updSql, new BatchPreparedStatementSetter() {
			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return list.size();
			}

			@Override
			public void setValues(PreparedStatement pstm, int index) throws SQLException {
				// TODO Auto-generated method stub
				Object obj = list.get(index);
				Map<Integer, Object> param = DAOUtil.parseEntity(obj, updSqlTemp);
				DAOUtil.packageSQL(pstm, param);
			}
		});
		return batch;
	}

	public static int delete(DAO dao, Object obj) throws SQLException {
		String delSqlTemp = dao.getDelSqlTemp();
		String delSql = dao.getDelSql();
		final Map<Integer, Object> param = DAOUtil.parseEntity(obj, delSqlTemp);
		/** 调用Spring JDBCTemplate **/
		JdbcTemplate jdbcTemplate = dao.getJdbcTemplate();
		int i = jdbcTemplate.update(delSql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstm) throws SQLException {
				// TODO Auto-generated method stub
				DAOUtil.packageSQL(pstm, param);
			}
		});
		return i;
	}

	public static int[] delete(DAO dao, final List<?> list) throws SQLException {
		final String delSqlTemp = dao.getInsSqlTemp();
		String delSql = dao.getDelSql();
		/** 调用Spring JDBCTemplate **/
		JdbcTemplate jdbcTemplate = dao.getJdbcTemplate();
		int[] batch = jdbcTemplate.batchUpdate(delSql, new BatchPreparedStatementSetter() {
			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return list.size();
			}

			@Override
			public void setValues(PreparedStatement pstm, int index) throws SQLException {
				// TODO Auto-generated method stub
				Object obj = list.get(index);
				Map<Integer, Object> param = DAOUtil.parseEntity(obj, delSqlTemp);
				DAOUtil.packageSQL(pstm, param);
			}
		});
		return batch;
	}
}