package com.dataart.jsptest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedHashSet;

import com.dataart.jsptest.domain.Group;
import com.dataart.jsptest.domain.Product;

public class ProductDaoImpl extends BasicDao implements ProductDao {

	private static final String SELECT_ALL_PRODUCT = "SELECT G.GROUP_ID, G.GROUP_NAME, P.PRODUCT_ID, P.PRODUCT_NAME, P.PRODUCT_PRICE FROM T_PRODUCT P INNER JOIN T_GROUP G ON P.GROUP_ID = G.GROUP_ID WHERE G.GROUP_ID = ";
	private static final String ORDERBY = " ORDER BY ";

	public Collection<Product> getAllForGroup(ProductOrderField orderField,
			SqlOrder order, int offset, long groupId) throws Exception {
		Collection<Product> products = new LinkedHashSet<Product>();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		Class.forName(getDriver()).newInstance();
		connection = DriverManager.getConnection(getJdbcUrl(), getUserName(),
				getPassword());
		String sqlQuery = SELECT_ALL_PRODUCT.concat(String.valueOf(groupId))
				.concat(ORDERBY).concat(orderField.toString()).concat(SPACE)
				.concat(order.toString()).concat(SPACE).concat(LIMIT_10_OFFSET)
				.concat(String.valueOf(offset));

		statement = connection.createStatement();

		try {
			rs = statement.executeQuery(sqlQuery);
			while (rs != null && rs.next()) {
				Product product = new Product();
				product.setId(rs.getLong("PRODUCT_ID"));
				product.setName(rs.getString("PRODUCT_NAME"));
				product.setGroup(getGroupFrom(rs));
				product.setPrice(rs.getDouble("PRODUCT_PRICE"));
				products.add(product);
			}

			return products;

		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
	}

	private Group getGroupFrom(ResultSet rs) throws Exception {
		Group group = new Group();
		group.setId(rs.getLong("GROUP_ID"));
		group.setName(rs.getString("GROUP_NAME"));
		return group;
	}

}
