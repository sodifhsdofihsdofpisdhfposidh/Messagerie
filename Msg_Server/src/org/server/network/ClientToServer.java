package org.server.network;

import org.server.Server;
import org.server.client.Client;

public abstract class ClientToServer extends NetworkObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5852495754602859091L;
	
	protected final static Server server = Server.getServer();

	public abstract void process(Client from);
	
}
