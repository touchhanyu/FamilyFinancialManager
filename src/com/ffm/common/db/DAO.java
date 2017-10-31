package com.ffm.common.db;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class DAO {
	/* 插入sql模版 */
	protected String insSqlTemp;
	/* 修改sql模版 */
	protected String updSqlTemp;
	/* 删除sql模版 */
	protected String delSqlTemp;
	/* 查询sql模版 */
	protected String qrySqlTemp;
	/* 插入sql */
	protected String insSql;
	/* 修改sql */
	protected String updSql;
	/* 删除sql */
	protected String delSql;
	/* 查询sql */
	protected String qrySql;
	private JdbcTemplate jdbcTemplate;

	public String getInsSqlTemp() {
		return insSqlTemp;
	}

	public void setInsSqlTemp(String insSqlTemp) {
		this.insSqlTemp = insSqlTemp;
	}

	public String getUpdSqlTemp() {
		return updSqlTemp;
	}

	public void setUpdSqlTemp(String updSqlTemp) {
		this.updSqlTemp = updSqlTemp;
	}

	public String getDelSqlTemp() {
		return delSqlTemp;
	}

	public void setDelSqlTemp(String delSqlTemp) {
		this.delSqlTemp = delSqlTemp;
	}

	public String getQrySqlTemp() {
		return qrySqlTemp;
	}

	public void setQrySqlTemp(String qrySqlTemp) {
		this.qrySqlTemp = qrySqlTemp;
	}

	public String getInsSql() {
		return insSql;
	}

	public void setInsSql(String insSql) {
		this.insSql = insSql;
	}

	public String getUpdSql() {
		return updSql;
	}

	public void setUpdSql(String updSql) {
		this.updSql = updSql;
	}

	public String getDelSql() {
		return delSql;
	}

	public void setDelSql(String delSql) {
		this.delSql = delSql;
	}

	public String getQrySql() {
		return qrySql;
	}

	public void setQrySql(String qrySql) {
		this.qrySql = qrySql;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public abstract <T> void insert(T obj);

	public abstract <T> void update(T obj);

	public abstract <T> void delete(T obj);

	public abstract <T> void insert(List<T> list);

	public abstract <T> void update(List<T> list);

	public abstract <T> void delete(List<T> list);
}