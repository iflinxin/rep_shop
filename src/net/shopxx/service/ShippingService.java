/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.service;

import java.util.List;
import java.util.Map;

import net.shopxx.entity.Shipping;

public interface ShippingService extends BaseService<Shipping, Long> {

	Shipping findBySn(String sn);

	List<Map<String, String>> getTransitSteps(Shipping shipping);

}