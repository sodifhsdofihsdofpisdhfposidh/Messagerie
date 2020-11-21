package org.server.network.clienttoserver;

import org.server.client.Client;
import org.server.client.User;
import org.server.network.ClientToServer;
import org.server.utils.Utils;

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
	
	@Override
	public void process(Client from) {
		Utils.getClientByID(from.getID()).setUser(user);
	}

}
