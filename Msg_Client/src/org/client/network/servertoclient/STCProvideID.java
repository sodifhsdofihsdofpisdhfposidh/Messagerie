package org.client.network.servertoclient;

import org.client.network.ServerToClient;
import org.client.network.clienttoserver.CTSProvideID;

public class STCProvideID extends ServerToClient{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1570013140775023334L;

	private final long id;
	
	public STCProvideID(long id) {
		this.id = id;
	}

	@Override
	public void process() {
		client.setId(id);
		client.sendToServer(new CTSProvideID(id));
	}

}
