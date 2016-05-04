/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.dao;

import java.util.List;

import net.shopxx.entity.ArticleCategory;

public interface ArticleCategoryDao extends BaseDao<ArticleCategory, Long> {

	List<ArticleCategory> findRoots(Integer count);

	List<ArticleCategory> findParents(ArticleCategory articleCategory, boolean recursive, Integer count);

	List<ArticleCategory> findChildren(ArticleCategory articleCategory, boolean recursive, Integer count);

}