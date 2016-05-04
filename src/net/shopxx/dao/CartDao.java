/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.dao;

import java.util.List;

import net.shopxx.entity.Cart;

public interface CartDao extends BaseDao<Cart, Long> {

	Cart findByKey(String key);

	List<Cart> findList(Boolean hasExpired, Integer count);

}