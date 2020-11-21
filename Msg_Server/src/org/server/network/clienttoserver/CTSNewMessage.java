package org.server.network.clienttoserver;

import org.server.client.Client;
import org.server.message.ChatRoom;
import org.server.message.Message;
import org.server.message.SuperMessage;
import org.server.network.ClientToServer;
import org.server.network.servertoclient.STCRequestUser;
import org.server.utils.Utils;

public class CTSNewMessage extends ClientToServer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3644923250293667801L;
	private final Message message;

	public CTSNewMessage(Message message) {
		this.message = message;		
	}

	@Override
	public void process(Client who) {
		if (who.isIdentified()) {
			ChatRoom cr = Utils.getChatRoomByID(message.getChatRoom());
			
			//Super message: sending to everyone.
			if (message instanceof SuperMessage) {
				if (cr.isOwner(who.getID())) {
					cr.addMessage(message);
					Utils.sendToChatRoom(message);
				}
			}
			else {
				cr.addMessage(message);
				Utils.sendToChatRoom(message);
			}
		}
		else {
			who.sendToClient(new STCRequestUser());
		}
	}

}
