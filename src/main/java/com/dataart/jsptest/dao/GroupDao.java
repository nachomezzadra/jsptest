package com.dataart.jsptest.dao;

import java.util.Collection;

import com.dataart.jsptest.domain.Group;

public interface GroupDao {

	/**
	 * Retrieves all Groups for the given Offset.
	 * 
	 * @param order
	 *            either ASC or DESC
	 * @param offset
	 *            for pagination
	 * @return
	 * @throws Exception
	 */
	Collection<Group> getAll(SqlOrder order, int offset) throws Exception;

}
