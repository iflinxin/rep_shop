/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.dao;

import java.util.List;

import net.shopxx.entity.Area;

public interface AreaDao extends BaseDao<Area, Long> {

	List<Area> findRoots(Integer count);

	List<Area> findParents(Area area, boolean recursive, Integer count);

	List<Area> findChildren(Area area, boolean recursive, Integer count);

}