package org.server.message;

import java.awt.Color;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.server.client.User;

public class Message implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6876910866280315518L;

	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy.MM.dd 'à' HH:mm:ss");
	
	private final int offset;
	
	private final Content content;
	
	private final long chatRoom;
	
	private final Date date;
	
	private final User sender;
	
	public Message(int offset, long chatRoom, User sender, Date date, Content content) {
		this.offset = offset;
		this.chatRoom = chatRoom;
		this.sender = sender;
		this.content = content;
		this.date = date;
	}
	
	public Message(int offset, String msg) throws NumberFormatException, ParseException {
		String[] tokens = msg.split("&$µ");
		this.offset = offset;
		this.chatRoom = Long.valueOf(tokens[0]);
		this.sender = new User(tokens[1],new Color(Integer.valueOf(tokens[2])));
		this.date = FORMATTER.parse(tokens[3]);
		this.content = Content.specifie(tokens[4]);
	}
	
	public String toString() {
		return chatRoom + "&$µ" + sender.getUsername() + "&$µ" 
				+ sender.getColor().getRGB() + FORMATTER.format(date) + "&$µ" + content;
	}
	
	public int getOffset() {
		return offset;
	}

	public Content getContent() {
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
