package org.server.message;

import java.util.Date;

import org.server.client.User;

public class SuperMessage extends Message {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1074522042635282138L;

	public SuperMessage(int offset, long chatRoom, User sender, Date date, Content content) {
		super(offset, chatRoom, sender, date, content);
	}
	
	public SuperMessage(int offset, User sender, Date date, Content content) {
		super(offset, 0L, sender, date, content);
	}
	
}
