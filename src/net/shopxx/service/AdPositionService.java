/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.service;

import net.shopxx.entity.AdPosition;

public interface AdPositionService extends BaseService<AdPosition, Long> {

	AdPosition find(Long id, boolean useCache);

}