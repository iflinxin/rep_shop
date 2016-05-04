/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.service;

import java.math.BigDecimal;

import net.shopxx.entity.MemberRank;

public interface MemberRankService extends BaseService<MemberRank, Long> {

	boolean nameExists(String name);

	boolean nameUnique(String previousName, String currentName);

	boolean amountExists(BigDecimal amount);

	boolean amountUnique(BigDecimal previousAmount, BigDecimal currentAmount);

	MemberRank findDefault();

	MemberRank findByAmount(BigDecimal amount);

}