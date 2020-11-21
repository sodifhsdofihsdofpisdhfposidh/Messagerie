package org.server.utils;

import java.util.Random;

import org.server.Server;
import org.server.client.Client;
import org.server.message.ChatRoom;
import org.server.message.Message;
import org.server.network.servertoclient.STCNewMessage;

public class Utils {
	
	private static final Server server = Server.getServer();
	private static final Random rand = new Random();
	
	public static ChatRoom getChatRoomByID(long id) {
		for (ChatRoom cr : server.chatRooms) {
			if (id == cr.getID()) {
				return cr;
			}
		}
		return null;
	}
	
	public static Client getClientByID(long id) {
		for (Client c : server.clientList) {
			if (c.getID() == id)
				return c;
		}
		return null;
	}
	
	public static void sendToChatRoom(Message message) {
		for (long id : getChatRoomByID(message.getChatRoom()).getMembers()) {
			getClientByID(id).sendToClient(new STCNewMessage(message));
		}
	}
	
	public static long generateID() {
		return rand.nextLong();	
	}
	
	public static long gererateClientID() {
		long id;
		do {
			id = generateID();
		} 
		while (server.doClientExist(id));
		return id;
	}
	
	static {
		rand.setSeed(1783704896326964680L);
	}
}
