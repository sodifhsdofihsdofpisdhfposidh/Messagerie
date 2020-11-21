package org.client.network.clienttoserver;

import org.client.message.Message;
import org.client.network.ClientToServer;

public class CTSNewMessage extends ClientToServer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3644923250293667801L;
	@SuppressWarnings("unused")
	private final Message<?> message;

	public CTSNewMessage(Message<?> message) {
		this.message = message;
	}
	
}
