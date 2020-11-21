package org.server.message;

public abstract class Content {

	protected String value;
	
	public Content(String value) {
		this.value = value;
	}
	
	public abstract Object getValue() throws Exception;
	
	public static Content specifie(String content) {
		String type = content.substring(0, 3);
		String s = content.substring(4, content.length()-1);
		if (type.equals("txt"))
			return new ContentText(s);
		else if (type.equals("img"))
			return new ContentImage(s);
		return null;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
