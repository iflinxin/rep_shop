/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.dao;

import net.shopxx.entity.PaymentLog;

public interface PaymentLogDao extends BaseDao<PaymentLog, Long> {

	PaymentLog findBySn(String sn);

}