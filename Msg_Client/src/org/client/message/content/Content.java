package org.client.message.content;

public abstract class Content {

	private String value;
	
	private final char type;
	
	public Content(String value) {
		this.value = value.substring(1);
		this.type = value.charAt(0);
	}
	
	protected char getType() {
		return type;
	}
	
	protected String getContent() {
		return value;
	}
	

}
