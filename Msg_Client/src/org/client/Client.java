package org.client;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import org.client.gui.GuiMain;
import org.client.message.ChatRoom;
import org.client.network.ClientToServer;
import org.client.network.ServerToClient;
import org.client.utils.Logger.LogType;

public class Client extends Socket{

	private static Client client;
	
	private long id;
	private User user;
	private boolean identified;
	private GuiMain gui;
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	public List<Long> chatRooms;
	public ChatRoom chatRoom; 
	
	public static void init(String ip, int port, long id, String username, int color, List<Long> chatRooms) {
		try {
			InitClient.log.printOut(LogType.SYSTEM, "Client init (user: "+username+", chatrooms: "+chatRooms.size()+")");
			client = new Client(ip, port, id, username, color, chatRooms);
			InitClient.log.printOut(LogType.SYSTEM, "Client has connected to "+ip);
			InitClient.log.printOut(LogType.SYSTEM, "Starting GUI");
			client.gui = new GuiMain();
			client.run();
		} catch (IOException e) {
			InitClient.log.printOut(LogType.ERROR, "Failed to connect to server", e);
		}
	}
	
	public Client(String ip, int port, long id, String username, int color, List<Long> chatRooms) throws UnknownHostException, IOException {
		super(ip, port);
		this.out = new ObjectOutputStream(getOutputStream());
		this.in = new ObjectInputStream(getInputStream());
		this.id = id;
		if (color == 0 || username != null) {
			this.identified = false;
		}
		else {
			this.user = new User(username, new Color(color));
			this.identified = true;
		}
		this.chatRooms = chatRooms;
	}
	
	public void run() {
		gui.show();
	}
	
	public boolean isIdentified() {
		return identified;
	}
	
	public static Client getClient() {
		return client;
	}
	
	@Override
	public void close() {
		try {
			super.close();
		} catch (IOException e) {
			InitClient.log.printOut(LogType.SYSTEM, "Failed to close server", e);
		}
		InitClient.log.close();
	}
	
	public ServerToClient receiveFromServer() {
		try {
			Object obj = in.readObject();
			if (obj != null) {
				if (obj instanceof ServerToClient) {
					return (ServerToClient) obj;
				}
			}
		} catch (ClassNotFoundException e) {
			InitClient.log.printOut(LogType.WARNING, "Unknown network object received", e);
		} catch (IOException e) {
			InitClient.log.printOut(LogType.WARNING, "Server unreacheable", e);
		}
		return null;
	}
	
	public void sendToServer(ClientToServer obj) {
		try {
			out.writeObject(obj);
		} catch (IOException e) {
			InitClient.log.printOut(LogType.WARNING, "Failed to send "+obj.getClass().getName()+" network object to server", e);
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUser(User user) {
		if (user != null) {
			this.user = user;
			this.identified = true;
		}
	}
	
	public User getUser() {
		return user;
	}
	
}
