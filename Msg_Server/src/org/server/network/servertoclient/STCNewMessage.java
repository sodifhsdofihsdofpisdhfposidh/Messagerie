package org.server.network.servertoclient;

import org.server.message.Message;
import org.server.network.ServerToClient;

public class STCNewMessage extends ServerToClient{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8558721643328105856L;
	@SuppressWarnings("unused")
	private final Message message;

	public STCNewMessage(Message message) {
		this.message = message;
	}
	
	
}
