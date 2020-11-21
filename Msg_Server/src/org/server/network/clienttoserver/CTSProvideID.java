package org.server.network.clienttoserver;

import org.server.client.Client;
import org.server.network.ClientToServer;
import org.server.network.servertoclient.STCProvideID;
import org.server.utils.Utils;

public class CTSProvideID extends ClientToServer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2194863971813802580L;

	private final long id;
	
	public CTSProvideID(long id) {
		this.id = id;
	}

	@Override
	public void process(Client from) {
		if (id == -1L) {
			from.sendToClient(new STCProvideID(Utils.gererateClientID()));
		}
		else {
			from.setId(id);
		}
	}

}
