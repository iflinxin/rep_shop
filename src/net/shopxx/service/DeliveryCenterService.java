/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.service;

import net.shopxx.entity.DeliveryCenter;

public interface DeliveryCenterService extends BaseService<DeliveryCenter, Long> {

	DeliveryCenter findDefault();

}