package org.client.message;

import java.io.Serializable;
import java.util.ArrayList;

public class ChatRoom implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5552574120322688377L;
	
	private long id;
	private String name;
	private ArrayList<Message<?>> msg;
	private ArrayList<Long> members;
	private ArrayList<Long> owners;
	private int offset;
	
	public ChatRoom(long id, String name) {
		this(id, name, new ArrayList<Message<?>>(), new ArrayList<Long>(), new ArrayList<Long>());
	}

	public ChatRoom(long id, String name, ArrayList<Message<?>> msg, ArrayList<Long> members, ArrayList<Long> owners) {
		this.id = id;
		this.name = name;
		this.msg = msg;
		this.members = members;
		this.owners = owners;
		this.offset = 20;
	}

	public long getId() {
		return id;
	}

	public ArrayList<Message<?>> getMessages() {
		return msg;
	}

	public ArrayList<Long> getMembers() {
		return members;
	}

	public int getOffset() {
		return offset;
	} 
	
	public void addMessage(Message<?> message) {
		this.msg.add(message);
		offset++;
	}
	
	public void kick(long clientID) {
		members.remove(clientID);
	}
	
	public void addMember(long clientID) {
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
	
	
}
