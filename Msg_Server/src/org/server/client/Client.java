package org.server.client;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.server.InitServer;
import org.server.Server;
import org.server.message.ChatRoom;
import org.server.network.ClientToServer;
import org.server.network.ServerToClient;
import org.server.network.servertoclient.STCProvideChatRoom;
import org.server.network.servertoclient.STCUpdateChatRoom;
import org.server.utils.Logger.LogType;

public class Client implements Runnable, Closeable {
	
	private static final Server server = Server.getServer();
	
	private long id;
	private User user;
	
	private Thread thread;
	private Socket socket;
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public Client(Socket socket) {
		this.id = -1;
		this.user = null;
		this.socket = socket;
		try {
			this.out = new ObjectOutputStream(socket.getOutputStream());
			this.in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			InitServer.log.printOut(LogType.ERROR, "Client failed to connect to server", e);
			try {
				socket.close();
			} catch (IOException e1) {
				InitServer.log.printOut(LogType.ERROR, "Cannot close connection", e1);
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public long getID() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	} 

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public boolean isIdentified() {
		return user != null;
	}

	public ClientToServer receiveFromClient() {
		try {
			Object obj = in.readObject();
			if (obj != null) {
				if (obj instanceof ClientToServer) {
					return (ClientToServer) obj;
				} else {
					InitServer.log.printOut(LogType.INFO, "Server to client network object receive from "+socket.getLocalAddress());
				}
			}
		} catch (ClassNotFoundException e) {
			InitServer.log.printOut(LogType.WARNING, "Unknown network object received from "+socket.getLocalAddress(), e);
		} catch (IOException e) {
			InitServer.log.printOut(LogType.INFO, "Client (ip: "+socket.getRemoteSocketAddress()+", username: "+(isIdentified() ? getUser().getUsername() : "Unknown")+") has disconnected");
		}
		return null;
	}
	
	public void sendToClient(ServerToClient obj) {
		try {
			out.writeObject(obj);
		} catch (IOException e) {
			InitServer.log.printOut(LogType.WARNING, "Failed to send "+obj.getClass().getName()+" network object to "+socket.getLocalAddress(), e);
		}
	}
	
	@Override
	public void run() {
		for (ChatRoom c : server.chatRooms) {
			sendToClient(new STCProvideChatRoom(id, c.getName()));
		}
		
		ClientToServer cts = receiveFromClient();
		while (cts != null) {
			cts.process(this);
			cts = receiveFromClient();
		}
	}
	
	public Socket getSocket() {
		return socket;
	}

	@Override
	public void close() throws IOException {
		thread.interrupt();
		socket.close();
	}
	
}
