package com.dataart.jsptest.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dataart.jsptest.BaseSpringTest;
import com.dataart.jsptest.domain.Product;

public class ProductDaoTest extends BaseSpringTest {

	@Autowired
	private ProductDao productDao;

	@Test
	public void shouldProperlyRetrieveAllProductsOrderedByName()
			throws Exception {
		Collection<Product> products = productDao.getAllForGroup(
				ProductOrderField.PRODUCT_NAME, SqlOrder.ASC, 0, 5);
		assertNotNull(products);
		assertFalse(products.isEmpty());
		assertEquals(4, products.size());
	}

	@Test
	public void shouldProperlyRetrieveAllProductsOrderedByNameWithOffset()
			throws Exception {
		Collection<Product> products = productDao.getAllForGroup(
				ProductOrderField.PRODUCT_NAME, SqlOrder.ASC, 5, 5);
		assertNotNull(products);
		assertTrue(products.isEmpty());
	}

	@Test
	public void shouldProperlyRetrieveAllProductsOrderedByPrice()
			throws Exception {
		Collection<Product> products = productDao.getAllForGroup(
				ProductOrderField.PRODUCT_PRICE, SqlOrder.DESC, 0, 2);
		assertNotNull(products);
		assertFalse(products.isEmpty());
		assertEquals(4, products.size());

		double lastPrice = Double.MAX_VALUE;

		for (Product eachProd : products) {
			assertTrue(eachProd.getPrice() < lastPrice);
			lastPrice = eachProd.getPrice();
		}
	}

}
