/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.dao;

import java.math.BigDecimal;

import net.shopxx.entity.MemberRank;

public interface MemberRankDao extends BaseDao<MemberRank, Long> {

	boolean nameExists(String name);

	boolean amountExists(BigDecimal amount);

	MemberRank findDefault();

	MemberRank findByAmount(BigDecimal amount);

	void setDefault(MemberRank memberRank);

}