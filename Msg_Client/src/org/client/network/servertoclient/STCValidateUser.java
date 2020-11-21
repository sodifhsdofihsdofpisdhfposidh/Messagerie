package org.client.network.servertoclient;

import org.client.network.ServerToClient;

public class STCValidateUser extends ServerToClient{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6803226861011867533L;
	
	private final boolean validate;
	
	public STCValidateUser(boolean validate) {
		this.validate = validate;
	}
	
	@Override
	public void process() {
		if (!validate) {
			client.setUser(null);
		}
	}

}
