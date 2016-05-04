/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.service;

import net.shopxx.TemplateConfig;

public interface TemplateService {

	String read(String templateConfigId);

	String read(TemplateConfig templateConfig);

	void write(String templateConfigId, String content);

	void write(TemplateConfig templateConfig, String content);

}