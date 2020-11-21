package org.client.network;

import org.client.Client;

public abstract class ServerToClient extends NetworkObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7632463163472755117L;
	
	protected static final Client client = Client.getClient();

	public abstract void process();
}
