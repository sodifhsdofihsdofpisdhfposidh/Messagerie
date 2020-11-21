package org.client;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.client.utils.Logger;
import org.client.utils.Logger.LogType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class InitClient {
	
public static final Logger log = new Logger("client.log");
	
	@SuppressWarnings("unchecked")
	public static void main(String args[]) {
		
		long id;
		int port, color;
		String ip, username = null;
		List<Long> chatRooms = null;

        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader("client.json"));
            ip = (String) jsonObject.get("ip");
            port = ((Long) jsonObject.get("port")).intValue();
            id = (long) jsonObject.get("id");
            color = ((Long) jsonObject.get("color")).intValue();
            username = (String) jsonObject.get("username");
            chatRooms = (JSONArray) jsonObject.get("chatrooms");
            if (chatRooms==null)
            	chatRooms = new ArrayList<Long>() {{add(0L);add(1L);add(2L);} private static final long serialVersionUID = 1L;};            
            	
        } catch (IOException | ParseException e) {
        	log.printOut(LogType.WARNING, "Failed to parse client config file, using default parameters", e);
        	ip = "127.0.0.1";
        	port = 50000;
        	id = -1L;
        	color = 0;
        	chatRooms = new ArrayList<Long>() {{add(0L);add(1L);add(2L);} private static final long serialVersionUID = 1L;};        }
        
        if (args.length == 2) {
        	ip = args[0];
        	port = Integer.valueOf(args[1]);
        }
        
		Client.init(ip, port, id, username, color, chatRooms);
	}
	
}
