/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.service;

import java.util.Date;
import java.util.List;

import net.shopxx.entity.Statistic;

public interface StatisticService extends BaseService<Statistic, Long> {

	boolean exists(int year, int month, int day);

	Statistic collect(int year, int month, int day);

	List<Statistic> analyze(Statistic.Period period, Date beginDate, Date endDate);

}