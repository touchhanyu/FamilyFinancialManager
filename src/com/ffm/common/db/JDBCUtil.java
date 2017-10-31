package com.ffm.common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@SuppressWarnings("unused")
public class JDBCUtil {
	private static ComboPooledDataSource dataSource;

	public static void setDataSource(ComboPooledDataSource dataSource) {
		JDBCUtil.dataSource = dataSource;
	}

/*	private static Properties prop = new Properties();
	static {
		InputStream is = null;
		try {
			is = JDBCUtil.class.getResourceAsStream("/com/ffm/common/jdbc.properties");
			prop.load(is);
		} catch (IOException e) {
			// TODO: handle exception
			throw new ExceptionInInitializerError(e);
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}*/

//	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	/**
	 * 获取连接
	 * 
	 * @return
	 */
	public static Connection getConn() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*Connection conn = tl.get();
		if (conn == null) {
			try {
				Class.forName(prop.getProperty("driver"));
				String url = prop.getProperty("url");
				String username = prop.getProperty("username");
				String password = prop.getProperty("password");
				conn = DriverManager.getConnection(url, username, password);
				tl.set(conn);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		return conn;
	}

	/**
	 * 释放资源
	 * 
	 * @param pstm
	 */
	public static void realse(Connection conn, PreparedStatement pstm, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}