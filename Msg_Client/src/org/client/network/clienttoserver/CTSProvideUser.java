package org.client.network.clienttoserver;

import org.client.User;
import org.client.network.ClientToServer;

public class CTSProvideUser extends ClientToServer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2727060440664478536L;

	@SuppressWarnings("unused")
	private final User user;

	public CTSProvideUser(User user) {
		this.user = user;
	}
	
	
}
