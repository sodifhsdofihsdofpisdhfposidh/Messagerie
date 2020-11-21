package org.client.network.servertoclient;

import org.client.network.ServerToClient;

public class STCProvideChatRoom extends ServerToClient{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5832449401855683489L;
	@SuppressWarnings("unused")
	private final long id;
	@SuppressWarnings("unused")
	private final String name;
	
	public STCProvideChatRoom(long id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public void process() {
		client.chatRooms.add
		
	}
	
}
