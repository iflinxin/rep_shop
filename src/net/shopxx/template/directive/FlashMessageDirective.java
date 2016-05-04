/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.template.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import net.shopxx.Message;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("flashMessageDirective")
public class FlashMessageDirective extends BaseDirective {

	public static final String FLASH_MESSAGE_ATTRIBUTE_NAME = FlashMessageDirective.class.getName() + ".FLASH_MESSAGE";

	private static final String VARIABLE_NAME = "flashMessage";

	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if (requestAttributes != null) {
			Message message = (Message) requestAttributes.getAttribute(FLASH_MESSAGE_ATTRIBUTE_NAME, RequestAttributes.SCOPE_REQUEST);
			if (body != null) {
				setLocalVariable(VARIABLE_NAME, message, env, body);
			} else {
				if (message != null) {
					Writer out = env.getOut();
					out.write("$.message(\"" + message.getType() + "\", \"" + message.getContent() + "\");");
				}
			}
		}
	}

}