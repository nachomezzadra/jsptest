package com.dataart.jsptest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedHashSet;

import com.dataart.jsptest.domain.Group;

public class GroupDaoImpl extends BasicDao implements GroupDao {

	private static final String SELECT_ALL_GROUP = "SELECT GROUP_ID, GROUP_NAME FROM T_GROUP ORDER BY GROUP_NAME ";

	public Collection<Group> getAll(SqlOrder order, int offset)
			throws Exception {
		Collection<Group> groups = new LinkedHashSet<Group>();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		Class.forName(getDriver()).newInstance();
		connection = DriverManager.getConnection(getJdbcUrl(), getUserName(),
				getPassword());
		String sqlQuery = SELECT_ALL_GROUP.concat(order.toString())
				.concat(SPACE).concat(LIMIT_10_OFFSET)
				.concat(String.valueOf(offset));

		statement = connection.createStatement();

		try {
			rs = statement.executeQuery(sqlQuery);
			while (rs != null && rs.next()) {
				Group group = new Group();
				group.setId(rs.getLong("GROUP_ID"));
				group.setName(rs.getString("GROUP_NAME"));
				groups.add(group);
			}

			return groups;

		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}

	}

}
