package com.blog.entity;

public class ChatMessage {
	private Integer id;
	
	private Integer uId;
	
	private String content;
	
	private String createDate;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public ChatMessage(Integer messageID, Integer uId, String content, String createDate) {
		super();
		this.id = messageID;
		this.uId=uId;
		this.content = content;
		this.createDate = createDate;
	}
	public ChatMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
