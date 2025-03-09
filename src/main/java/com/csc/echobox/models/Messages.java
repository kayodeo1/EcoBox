package com.csc.echobox.models;

import java.sql.Timestamp;

public class Messages {
	private Long id;
	 private String text;
	 private String messageType;
	 private java.sql.Timestamp timestamp;
	 private String messageStatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public java.sql.Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(java.sql.Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessageStatus() {
		return messageStatus;
	}
	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}
	public Messages(Long id, String text, String messageType, Timestamp timestamp, String messageStatus) {
		super();
		this.id = id;
		this.text = text;
		this.messageType = messageType;
		this.timestamp = timestamp;
		this.messageStatus = messageStatus;
	}

	
}
	
		