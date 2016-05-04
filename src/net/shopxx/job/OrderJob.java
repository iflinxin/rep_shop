/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.job;

import javax.annotation.Resource;

import net.shopxx.service.OrderService;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Lazy(false)
@Component("orderJob")
public class OrderJob {

	@Resource(name = "orderServiceImpl")
	private OrderService orderService;

	@Scheduled(cron = "${job.order_expired_processing.cron}")
	public void expiredProcessing() {
		orderService.undoExpiredUseCouponCode();
		orderService.undoExpiredExchangePoint();
		orderService.releaseExpiredAllocatedStock();
	}

}