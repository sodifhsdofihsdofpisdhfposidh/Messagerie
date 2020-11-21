package org.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.server.client.Client;
import org.server.message.ChatRoom;
import org.server.utils.Logger.LogType;

public class Server extends ServerSocket {
	
	private static Server server;
	
	public Map<Long,Client> clientList;
	public Map<Long,ChatRoom> chatRooms;
	
	public List<Long> superUsers;
	public List<Long> users;
	
	public static void init(int port, int backlog, List<Long> users, List<Long> superUsers, List<Long> chatRooms) {
		try {
			server = new Server(port, backlog, users, superUsers, chatRooms);
			server.run();
		} catch (IOException e) {
			InitServer.log.printOut(LogType.ERROR, "Server unable to start", e);
		}
	}
	
	public Server(int port, int backlog, List<Long> users, List<Long> superUsers, List<Long> chatRooms) throws IOException {
		super(port, backlog);
		this.clientList = new HashMap<Long, Client>();
		this.users = users;
		this.superUsers = superUsers;
		this.chatRooms = new HashMap<Long, ChatRoom>();
		ChatRoom cr;
		for (long id : chatRooms) {
			try {
				cr = new ChatRoom(id);
				this.chatRooms.put(id,cr);
			} catch (IOException e) {
				InitServer.log.printOut(LogType.WARNING, "Unable to create chatroom "+id, e);
			} catch (ParseException | NumberFormatException | java.text.ParseException e) {
				InitServer.log.printOut(LogType.WARNING, "Unable to parse config file of chatroom "+id, e);
			}
		}
		InitServer.log.printOut(LogType.SYSTEM, "Server init (ip: "+getInetAddress()+", port: "+getLocalPort()+", nbclients: "+backlog+", superuser: "+this.superUsers.size()+", chatrooms: "+this.chatRooms.size()+")");
	}
	
	public void run() {
		while(true) {
			try {
				Client newClient = new Client(accept());
	            
				InitServer.log.printOut(LogType.INFO, "New user connected from " + newClient.getSocket().getRemoteSocketAddress());
	            clientList.add(newClient);
	            Thread thread = new Thread(newClient);
	            newClient.setThread(thread);
	            thread.start();
	       
	         } catch (SocketTimeoutException s) {
	        	 InitServer.log.printOut(LogType.WARNING, "A connection timed out");
	         } catch (IOException e) {
	        	 InitServer.log.printOut(LogType.ERROR, "A connection error has occured", e);
	            break;
	         }
		}
	}
	
	public boolean isSuperUser(Client client) {
		long id = client.getID();
		for (long ids : superUsers) {
			if (ids == id)
				return true;
		}
		return false;
	}
	
	public static Server getServer() {
		return server;
	}
	
	public boolean doClientExist(long id) {
		return clientList.containsKey(id);
	}
	
	@Override
	public void close() {
		try {
			for (Client c : clientList)
				c.close();
		} catch (IOException e) {
			InitServer.log.printOut(LogType.WARNING, "Failed to close connection(s)", e);
		}
		for (ChatRoom m : chatRooms) {
			m.close();
		}
		try {
			super.close();
		} catch (IOException e) {
			InitServer.log.printOut(LogType.ERROR, "Failed to close server", e);
		}
		InitServer.log.close();
	}
	
}
