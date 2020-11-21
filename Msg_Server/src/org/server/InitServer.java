package org.server;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.server.utils.Logger;
import org.server.utils.Logger.LogType;

public class InitServer {
	
	public static final Logger log = new Logger("server.log");
	
	@SuppressWarnings("unchecked")
	public static void main(String args[]) {
		
		int port, backlog;
		List<Long> users = null, superUsers = null, chatRooms = null;

        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader("server.json"));
            port = ((Long) jsonObject.get("port")).intValue();
            backlog = ((Long) jsonObject.get("backlog")).intValue();
            users = (JSONArray) jsonObject.get("users");
            superUsers = (JSONArray) jsonObject.get("superUsers");
            chatRooms = (JSONArray) jsonObject.get("chatrooms");
            if (users==null)
    			users = new ArrayList<Long>();
    		if (superUsers==null)
    			superUsers = new ArrayList<Long>();
    		if (chatRooms==null)
    			chatRooms = new ArrayList<Long>() {{add(0L);add(1L);add(2L);} private static final long serialVersionUID = 1L;};

        } catch (IOException | ParseException e) {
        	log.printOut(LogType.WARNING, "Failed to parse server config file, using default parameters", e);
        	port = 50000;
        	backlog = 32;
        	users = new ArrayList<Long>();
        	superUsers = new ArrayList<Long>();
        	chatRooms = new ArrayList<Long>() {{add(0L);add(1L);add(2L);} private static final long serialVersionUID = 1L;};
        }

		Server.init(port, backlog, users, superUsers, chatRooms);
	}
}
