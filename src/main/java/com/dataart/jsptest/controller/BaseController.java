package com.dataart.jsptest.controller;

import java.util.Collection;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dataart.jsptest.dao.GroupDao;
import com.dataart.jsptest.dao.ProductDao;
import com.dataart.jsptest.dao.ProductOrderField;
import com.dataart.jsptest.dao.SqlOrder;
import com.dataart.jsptest.domain.Group;
import com.dataart.jsptest.domain.Product;

@Controller
public class BaseController {

	private static final String PARAM_GROUP_SORT = "groupSort";
	private static final String PARAM_GROUP_OFFSET = "groupOffset";
	private static final String PARAM_GROUP_ID = "groupId";
	private static final String PARAM_PRODUCT_SORT = "productSort";
	private static final String PARAM_PRODUCT_OFFSET = "productOffset";
	private static final String PARAM_PRODUCT_FIELD = "productOrderField";
	private static final String VIEW_INDEX = "index";

	private final static org.slf4j.Logger logger = LoggerFactory
			.getLogger(BaseController.class);

	@Autowired
	private GroupDao groupDao;
	@Autowired
	private ProductDao productDao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String initialPage(
			@RequestParam(required = false, value = PARAM_GROUP_SORT) String groupSort,
			@RequestParam(required = false, value = PARAM_GROUP_OFFSET) Integer groupOffset,
			@RequestParam(required = false, value = PARAM_PRODUCT_FIELD) String productOrderField,
			@RequestParam(required = false, value = PARAM_PRODUCT_SORT) String productSort,
			@RequestParam(required = false, value = PARAM_PRODUCT_OFFSET) Integer productOffset,
			@RequestParam(required = false, value = PARAM_GROUP_ID) Long groupId,
			ModelMap model) {
		logger.debug("Group Sort: {}", groupSort);
		logger.debug("Group Offset: {}", groupOffset);
		logger.debug("Product Sort: {}", productSort);
		logger.debug("Product Offset: {}", productOffset);

		if (groupSort == null) {
			groupSort = SqlOrder.ASC.toString();
		}

		if (groupOffset == null) {
			groupOffset = Integer.valueOf(0);
		}

		if (productSort == null) {
			productSort = SqlOrder.ASC.toString();
		}

		if (productOffset == null) {
			productOffset = Integer.valueOf(0);
		}

		if (productOrderField == null) {
			productOrderField = ProductOrderField.PRODUCT_NAME.toString();
		}

		if (groupId == null) {
			groupId = Long.valueOf(-1);
		}

		String nextProductSort = "DESC";
		if (productSort == null || productSort.equals("DESC")) {
			nextProductSort = "ASC";
		}

		String nextGroupSort = "DESC";
		if (groupSort == null || groupSort.equals("DESC")) {
			nextGroupSort = "ASC";
		}

		model.addAttribute("groups", getGroups(groupSort, groupOffset));
		model.addAttribute(
				"products",
				getProducts(productOrderField, productSort, productOffset,
						groupId));
		model.addAttribute("selectedGroup", groupId);
		model.addAttribute("groupOffset", groupOffset);
		model.addAttribute("productOffset", productOffset);
		model.addAttribute("nextProductSort", nextProductSort);
		model.addAttribute("nextGroupSort", nextGroupSort);

		return VIEW_INDEX;

	}

	private Collection<Product> getProducts(String productOrderField,
			String productSort, Integer productOffset, long groupId) {
		logger.info("Retrieving Products for Group ID {}", groupId);
		Collection<Product> products = null;
		try {
			products = productDao.getAllForGroup(
					ProductOrderField.valueOf(productOrderField),
					SqlOrder.valueOf(productSort), productOffset, groupId);
			logger.info(products.toString());
		} catch (Exception e) {
			logger.error("An error occurred while retrieving the Products", e);
		}

		return products;

	}

	private Collection<Group> getGroups(String order, int offset) {
		logger.info("Retrieving Groups");
		Collection<Group> groups = null;
		try {
			groups = groupDao.getAll(SqlOrder.valueOf(order), offset);
			logger.info(groups.toString());
		} catch (Exception e) {
			logger.error("An error occurred while retrieving the Groups", e);
		}

		return groups;
	}

}
