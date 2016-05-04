/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.service;

import java.util.List;

import net.shopxx.plugin.LoginPlugin;
import net.shopxx.plugin.PaymentPlugin;
import net.shopxx.plugin.StoragePlugin;

public interface PluginService {

	List<PaymentPlugin> getPaymentPlugins();

	List<StoragePlugin> getStoragePlugins();

	List<LoginPlugin> getLoginPlugins();

	List<PaymentPlugin> getPaymentPlugins(boolean isEnabled);

	List<StoragePlugin> getStoragePlugins(boolean isEnabled);

	List<LoginPlugin> getLoginPlugins(boolean isEnabled);

	PaymentPlugin getPaymentPlugin(String id);

	StoragePlugin getStoragePlugin(String id);

	LoginPlugin getLoginPlugin(String id);

}