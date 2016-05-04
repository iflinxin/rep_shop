/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.listener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import net.shopxx.entity.BaseEntity;

public class EntityListener {

	@PrePersist
	public void prePersist(BaseEntity<?> entity) {
		entity.setCreateDate(new Date());
		entity.setModifyDate(new Date());
		entity.setVersion(null);
	}

	@PreUpdate
	public void preUpdate(BaseEntity<?> entity) {
		entity.setModifyDate(new Date());
	}

}