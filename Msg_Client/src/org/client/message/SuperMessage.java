package org.client.message;

import java.util.Date;

import org.client.User;
import org.client.message.content.Content;

public class SuperMessage<E extends Content> extends Message<E> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1074522042635282138L;

	public SuperMessage(long chatRoom, User sender, Date date, E content) {
		super(chatRoom, sender, date, content);
	}
	
	public SuperMessage(User sender, Date date, E content) {
		super(0L, sender, date, content);
	}
	
}
