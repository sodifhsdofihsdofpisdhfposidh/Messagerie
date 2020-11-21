package org.client.message.content;

public class ContentText extends Content{

	public ContentText(String value) {
		super(0+value);
	}

	public String getValue() {
		return getContent();
	}
	
}
