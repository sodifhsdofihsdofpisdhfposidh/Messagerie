package org.server.network.servertoclient;

import org.server.network.ServerToClient;

public class STCProvideID extends ServerToClient{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1570013140775023334L;
	
	@SuppressWarnings("unused")
	private final long id;
	
	public STCProvideID(long id) {
		this.id = id;
	}
}
