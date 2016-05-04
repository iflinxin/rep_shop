/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.dao;

import net.shopxx.entity.Admin;

public interface AdminDao extends BaseDao<Admin, Long> {

	boolean usernameExists(String username);

	Admin findByUsername(String username);

}