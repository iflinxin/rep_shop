/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.service;

import net.shopxx.entity.MessageConfig;

public interface MessageConfigService extends BaseService<MessageConfig, Long> {

	MessageConfig find(MessageConfig.Type type);

}