package org.client.network.clienttoserver;

import org.client.network.ClientToServer;

public class CTSProvideID extends ClientToServer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2194863971813802580L;
	
	@SuppressWarnings("unused")
	private final long id;
	
	public CTSProvideID(long id) {
		this.id = id;
	}
	
}
