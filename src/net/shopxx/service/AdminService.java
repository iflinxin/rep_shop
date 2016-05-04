/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.service;

import java.util.List;

import net.shopxx.entity.Admin;

public interface AdminService extends BaseService<Admin, Long> {

	boolean usernameExists(String username);

	Admin findByUsername(String username);

	List<String> findAuthorities(Long id);

	boolean isAuthenticated();

	Admin getCurrent();

	String getCurrentUsername();

	String getLoginToken();

}