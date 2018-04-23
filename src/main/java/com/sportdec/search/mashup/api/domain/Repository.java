package com.sportdec.search.mashup.api.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Repository implements Serializable {

	/**
	 * 
	 */
	@Expose(serialize=false, deserialize=false)
	private static final long serialVersionUID = 1L;
	
	@Expose
	private String name;
	
	@Expose
	private String description;
	
	@Expose
	private String url;
	
	@Expose
	private String owner;
	
	@Expose
	private Date createDate;
    
	@Expose
	private List<Tweet> latestTweet;
	
    @Expose(serialize = false, deserialize=false)
	public final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public List<Tweet> getLatestTweet() {
		return latestTweet;
	}

	public void setLatestTweet(List<Tweet> latestTweet) {
		this.latestTweet = latestTweet;
	}

	public Repository() {
		
	}

	public Repository(String name, String description, String url, String owner, Date createDate) {
		super();
		this.name = name;
		this.description = description;
		this.url = url;
		this.owner = owner;
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Repository [name=" + name + ", description=" + description + ", url=" + url + ", owner=" + owner
				+ ", createDate=" + createDate + "]";
	}
}
