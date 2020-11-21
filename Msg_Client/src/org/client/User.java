package org.client;

import java.awt.Color;
import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8028582071809801047L;
	private String username;
	private Color color;
	
	public User(String username, Color color) {
		this.username = username;
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public String getUsername() {
		return username;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
