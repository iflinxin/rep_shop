/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.service;

import java.security.interfaces.RSAPublicKey;

import javax.servlet.http.HttpServletRequest;

public interface RSAService {

	RSAPublicKey generateKey(HttpServletRequest request);

	void removePrivateKey(HttpServletRequest request);

	String decryptParameter(String name, HttpServletRequest request);

}