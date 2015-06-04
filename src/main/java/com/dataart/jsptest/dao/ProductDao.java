package com.dataart.jsptest.dao;

import java.util.Collection;

import com.dataart.jsptest.domain.Product;

public interface ProductDao {

	/**
	 * Retrieves all Products for the given Group ID. If group ID is negative,
	 * no Products will be returned.
	 * 
	 * @param order
	 * @param offset
	 * @param groupId
	 * @return
	 * @throws Exception
	 */
	Collection<Product> getAllForGroup(ProductOrderField orderField,
			SqlOrder order, int offset, long groupId) throws Exception;
}
