package org.server.message;

public class ContentText extends Content{

	public ContentText(String value) {
		super(value);
	}

	@Override
	public Object getValue() {
		return value.substring(4, value.length()-1);
	}
	
}
