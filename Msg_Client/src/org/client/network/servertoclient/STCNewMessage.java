package org.client.network.servertoclient;

import org.client.message.Message;
import org.client.network.ServerToClient;

public class STCNewMessage extends ServerToClient{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8558721643328105856L;
	private final Message<?> message;

	public STCNewMessage(Message<?> message) {
		this.message = message;
	}
	
	@Override
	public void process() {
		client.chatRoom.addMessage(message);
	}

}
