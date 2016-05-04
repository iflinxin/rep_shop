/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://3936242.01p.com/
 * License: http://3936242.01p.com/license
 */
package net.shopxx.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.Length;

@Embeddable
public class Invoice implements Serializable {

	private static final long serialVersionUID = 7616671327444042971L;

	private String title;

	private String content;

	public Invoice() {
	}

	public Invoice(String title, String content) {
		this.title = title;
		this.content = content;
	}

	@Length(max = 200)
	@Column(name = "invoice_title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Length(max = 200)
	@Column(name = "invoice_content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
