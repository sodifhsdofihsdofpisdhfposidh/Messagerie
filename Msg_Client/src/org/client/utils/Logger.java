package org.client.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	
	public enum LogType {
		ERROR, INFO, SYSTEM, WARNING
	}  
	
	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("HH:mm:ss");
	
	private PrintWriter writer;
	
	public Logger(String path) {
		File f = new File(path);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			writer = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		writer.write("\n\n--------------------" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) + "--------------------:");
	}
	
	public void printOut(LogType type, String out) {
		out = "["+FORMATTER.format(new Date())+"] "+type+": "+out;
		System.out.println(out);
		writer.write(out);
	} 
	
	public void printOut(LogType type, String out, Exception e) {
		printOut(type, out);
		e.printStackTrace();
		e.printStackTrace(writer);
	}
	
	public void close() {
		writer.close();
	}
	
}
