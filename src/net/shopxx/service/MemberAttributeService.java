/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.service;

import java.util.List;

import net.shopxx.Filter;
import net.shopxx.Order;
import net.shopxx.entity.MemberAttribute;

public interface MemberAttributeService extends BaseService<MemberAttribute, Long> {

	Integer findUnusedPropertyIndex();

	List<MemberAttribute> findList(Boolean isEnabled, Integer count, List<Filter> filters, List<Order> orders);

	List<MemberAttribute> findList(Boolean isEnabled, Integer count, List<Filter> filters, List<Order> orders, boolean useCache);

	List<MemberAttribute> findList(Boolean isEnabled, boolean useCache);

	boolean isValid(MemberAttribute memberAttribute, String[] values);

	Object toMemberAttributeValue(MemberAttribute memberAttribute, String[] values);

}