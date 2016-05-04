/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.dao;

import java.util.List;

import net.shopxx.entity.ProductCategory;

public interface ProductCategoryDao extends BaseDao<ProductCategory, Long> {

	List<ProductCategory> findRoots(Integer count);

	List<ProductCategory> findParents(ProductCategory productCategory, boolean recursive, Integer count);

	List<ProductCategory> findChildren(ProductCategory productCategory, boolean recursive, Integer count);

}