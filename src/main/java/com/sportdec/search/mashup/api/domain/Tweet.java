package com.sportdec.search.mashup.api.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.annotations.Expose;

public class Tweet implements Serializable {

	/**
	 * 
	 */
	@Expose(serialize=false, deserialize=false)
	private static final long serialVersionUID = 1L;

	@Expose
	private Date createdAt;
	
	@Expose
	private String text;
	
	@Expose
	private String user;
	
	@Expose
	private String userUrl;
	
	@Expose(serialize = false, deserialize=false)
	public final DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getUserUrl() {
		return userUrl;
	}
	
	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}

	public Tweet() {
		
	}
	
	public Tweet(Date createdAt, String text, String user, String userUrl) {
		super();
		this.createdAt = createdAt;
		this.text = text;
		this.user = user;
		this.userUrl = userUrl;
	}

	@Override
	public String toString() {
		return "Tweet [createdAt=" + createdAt + ", text=" + text + ", user=" + user + ", userUrl=" + userUrl
				+ ", dateFormat=" + dateFormat + "]";
	}
}