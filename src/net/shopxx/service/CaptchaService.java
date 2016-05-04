/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.service;

import java.awt.image.BufferedImage;

import net.shopxx.Setting;

public interface CaptchaService {

	BufferedImage buildImage(String captchaId);

	boolean isValid(Setting.CaptchaType captchaType, String captchaId, String captcha);

}