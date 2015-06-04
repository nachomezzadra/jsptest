package com.dataart.jsptest.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dataart.jsptest.BaseSpringTest;
import com.dataart.jsptest.domain.Group;

public class GroupDaoTest extends BaseSpringTest {

	@Autowired
	private GroupDao groupDao;

	@Test
	public void shouldProperlyRetrieveAllGroups() throws Exception {
		Collection<Group> groups = groupDao.getAll(SqlOrder.ASC, 0);
		assertNotNull(groups);
		assertFalse(groups.isEmpty());
		assertEquals(7, groups.size());
	}

	@Test
	public void shouldProperlyRetrieveGroupsWithOffset() throws Exception {
		Collection<Group> groups = groupDao.getAll(SqlOrder.ASC, 5);
		assertNotNull(groups);
		assertFalse(groups.isEmpty());
		assertEquals(2, groups.size());
	}

}
