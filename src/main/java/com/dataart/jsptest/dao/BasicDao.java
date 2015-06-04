package com.dataart.jsptest.dao;

import org.springframework.beans.factory.annotation.Required;

import com.dataart.jsptest.configuration.JdbcConfiguration;

public abstract class BasicDao {

	protected static final String LIMIT_10_OFFSET = " LIMIT 10 OFFSET ";
	protected static final String SPACE = " ";

	private JdbcConfiguration jdbcConfiguration;

	public String getPassword() {
		return jdbcConfiguration.getPassword();
	}

	public String getUserName() {
		return jdbcConfiguration.getUserName();
	}

	public String getJdbcUrl() {
		return jdbcConfiguration.getJdbcUrl();
	}

	public String getDriver() {
		return jdbcConfiguration.getDriver();
	}

	public JdbcConfiguration getJdbcConfiguration() {
		return jdbcConfiguration;
	}

	@Required
	public void setJdbcConfiguration(JdbcConfiguration jdbcConfiguration) {
		this.jdbcConfiguration = jdbcConfiguration;
	}
}
