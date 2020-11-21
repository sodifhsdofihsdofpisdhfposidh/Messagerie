package org.server.message;

import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.server.InitServer;
import org.server.utils.Logger.LogType;

public class ChatRoom implements Closeable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5552574120322688377L;
	private final long id;
	private String name;
	private List<Message> msg;
	private List<Long> members;
	private List<Long> owners;
	private int offset;
	
	private PrintWriter out;
	
	@SuppressWarnings("unchecked")
	public ChatRoom(long id) throws IOException, ParseException, NumberFormatException, java.text.ParseException  {
		this.id = id;
		new File("chatrooms/"+id).mkdirs();
		File config = new File(folderPath()+"config.json"); 
		if (!config.createNewFile()) {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(config));
			this.name = (String) jsonObject.get("name");
			this.owners = (JSONArray) jsonObject.get("owners");
			this.members = (JSONArray) jsonObject.get("members");
		}
		else {
			this.name = "New Chat";
			this.owners = new ArrayList<Long>();
			this.members = new ArrayList<Long>();
		}
		this.msg = new ArrayList<Message>();
		File chat = new File(folderPath()+"chat.txt");
		this.out = new PrintWriter(chat);
		if (!chat.createNewFile()) {
			Scanner scan = new Scanner(chat);
			while(scan.hasNextLine()) {
				this.msg.add(new Message(this.offset++, scan.nextLine()));
			}
			scan.close();
		}
		this.offset = this.msg.size();
		InitServer.log.printOut(LogType.INFO, "New chatroom created (id: "+id+", name: "+name+")");
	}
	
	private String folderPath() {
		return "chatrooms/" + id + "/";
	}
	
	public long getID() {
		return id;
	}

	public List<Message> getMessages() {
		return msg;
	}

	public List<Long> getMembers() {
		return members;
	}

	public int getOffset() {
		return offset;
	} 
	
	public void addMessage(Message message) {
		this.msg.add(message);
		offset++;
		out.write(message.toString());
	}
	
	public void kick(long clientID) {
		members.remove(clientID);
	}
	
	public void addMenber(long clientID) {
		members.add(clientID);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatRoom other = (ChatRoom) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public boolean isOwner(long id) {
		for (long id2 : owners) {
			if (id == id2)
				return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void close() {
		new File("chatrooms/"+id).mkdirs();
		out.close();
		InitServer.log.printOut(LogType.SYSTEM, "ChatRoom "+id+" saved");
		
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("name", name);
		JSONArray arr = new JSONArray();
		arr.addAll(owners);
		JSONArray arr2 = new JSONArray();
		arr2.addAll(members);
		json.put("owners", owners);
		json.put("members", members); 
		
		try {
			File config = new File(folderPath()+"config.json");
			config.createNewFile();
			FileWriter file = new FileWriter(config);
            file.write(json.toJSONString());
            file.close();
            InitServer.log.printOut(LogType.SYSTEM, "ChatRoom "+id+" config saved");
        } catch (IOException e) {
            InitServer.log.printOut(LogType.WARNING, "Failed to save config file of chatroom "+id, e);
        }
	}
}
