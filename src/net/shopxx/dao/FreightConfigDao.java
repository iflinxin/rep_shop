/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.dao;

import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.entity.Area;
import net.shopxx.entity.FreightConfig;
import net.shopxx.entity.ShippingMethod;

public interface FreightConfigDao extends BaseDao<FreightConfig, Long> {

	boolean exists(ShippingMethod shippingMethod, Area area);

	Page<FreightConfig> findPage(ShippingMethod shippingMethod, Pageable pageable);

}