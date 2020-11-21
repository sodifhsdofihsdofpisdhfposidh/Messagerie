package org.server.network.servertoclient;

import org.server.network.ServerToClient;

public class STCUpdateChatRoom extends ServerToClient{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5832449401855683489L;
	@SuppressWarnings("unused")
	private final long id;
	@SuppressWarnings("unused")
	private final String name;
	
	public STCUpdateChatRoom(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
}
