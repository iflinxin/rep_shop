/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.service.impl;

import javax.annotation.Resource;

import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.DepositLogDao;
import net.shopxx.entity.DepositLog;
import net.shopxx.entity.Member;
import net.shopxx.service.DepositLogService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("depositLogServiceImpl")
public class DepositLogServiceImpl extends BaseServiceImpl<DepositLog, Long> implements DepositLogService {

	@Resource(name = "depositLogDaoImpl")
	private DepositLogDao depositLogDao;

	@Transactional(readOnly = true)
	public Page<DepositLog> findPage(Member member, Pageable pageable) {
		return depositLogDao.findPage(member, pageable);
	}

}