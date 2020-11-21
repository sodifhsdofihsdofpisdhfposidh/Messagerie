package org.server.message;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ContentImage extends Content{

	private File file; 
	
	public ContentImage(String value) {
		super(value);
		this.file = new File(value);
	}

	@Override
	public Object getValue() throws IOException {
		return ImageIO.read(file);
	}
	

}
