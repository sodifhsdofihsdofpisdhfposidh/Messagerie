package org.client.message.content;

import javax.swing.ImageIcon;

public class ContentImage extends Content{
	
	public ContentImage(String value) {
		super(1+value);
	}

	public ImageIcon getValue() {
		return new ImageIcon(getContent());
	}

}
