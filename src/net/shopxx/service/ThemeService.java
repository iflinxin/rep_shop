/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.service;

import java.util.List;

import net.shopxx.Theme;

import org.springframework.web.multipart.MultipartFile;

public interface ThemeService {

	List<Theme> getAll();

	Theme get(String id);

	boolean upload(MultipartFile multipartFile);

}