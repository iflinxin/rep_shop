/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.service;

import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.entity.Area;
import net.shopxx.entity.FreightConfig;
import net.shopxx.entity.ShippingMethod;

public interface FreightConfigService extends BaseService<FreightConfig, Long> {

	boolean exists(ShippingMethod shippingMethod, Area area);

	boolean unique(ShippingMethod shippingMethod, Area previousArea, Area currentArea);

	Page<FreightConfig> findPage(ShippingMethod shippingMethod, Pageable pageable);

}