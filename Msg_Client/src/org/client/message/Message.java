package org.client.message;

import java.io.Serializable;
import java.util.Date;

import org.client.User;
import org.client.message.content.Content;

public class Message<E extends Content> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6876910866280315518L;
	
	private final E content;
	
	private final long chatRoom;
	
	private final Date date;
	
	private final User sender;
	
	public Message(long chatRoom, User sender, Date date, E content) {
		this.chatRoom = chatRoom;
		this.sender = sender;
		this.content = content;
		this.date = date;
	}

	public E getContent() {
		return content;
	}
	
	public Date getDate() {
		return date;
	}
	
	public long getChatRoom() {
		return chatRoom;
	}

	public User getSender() {
		return sender;
	}
	
}
