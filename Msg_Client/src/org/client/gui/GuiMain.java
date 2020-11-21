package org.client.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.client.Client;
import org.client.User;
import org.client.gui.renderer.RendererChatRoom;
import org.client.gui.renderer.RendererMessage;
import org.client.gui.renderer.RendererUser;
import org.client.message.ChatRoom;
import org.client.message.Message;
import org.client.message.content.Content;
import org.client.message.content.ContentText;
import org.client.network.clienttoserver.CTSNewMessage;

public class GuiMain{
	
	private static final Client client = Client.getClient();
	
	private JFrame window;
	private GuiUser promptIdentity;
	
	private DefaultListModel<ChatRoom> chatRoomListModel;
	private DefaultListModel<Message<?>> messageListModel;
	private DefaultListModel<User> userListModel;
	
	public JList<ChatRoom> chatRoomList;
	public JList<Message<?>> messageList;
	public JList<User> userList;
	
	public JTextField field;
	public JButton send;
	public JButton add;
	
	private Content addCase;
	
	public GuiMain() {
		window = new JFrame();
		addCase = null;
		
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setTitle("Message Client");
		window.setBounds(200, 200, 400, 300);
		
		promptIdentity = new GuiUser();
		
		initToolBar();
		initWindow();
		initEvents();
	}
	
	private void initWindow() {
		GridBagLayout layout = new GridBagLayout();
		window.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		//gbc.fill = GridBagConstraints.HORIZONTAL;
		
	    gbc.weightx = 20;
	    gbc.weighty = 20;
	    
		
		chatRoomListModel = new DefaultListModel<ChatRoom>();
		chatRoomListModel.addElement(new ChatRoom(1L, "test"));
		chatRoomList = new JList<ChatRoom>(chatRoomListModel);
		chatRoomList.setCellRenderer(new RendererChatRoom());
		gbc.gridx = 0;
		gbc.gridy = 0;
		window.add(chatRoomList, gbc);
		
		messageListModel = new DefaultListModel<Message<?>>();
		messageListModel.addElement(new Message<ContentText>(1L, new User("rt", Color.RED), new Date(), new ContentText("test 1")));
		messageList = new JList<Message<?>>(messageListModel);
		messageList.setCellRenderer(new RendererMessage());
		gbc.gridx = 1;
		gbc.gridy = 0;
		window.add(messageList, gbc);
		
		userListModel = new DefaultListModel<User>();
		userListModel.add(0, new User("rt", Color.RED));
		userList = new JList<User>(userListModel);
		userList.setCellRenderer(new RendererUser());
		gbc.gridx = 2;
		gbc.gridy = 0;
		window.add(userList, gbc);
		
		field = new JTextField();
		gbc.gridx = 0;
		gbc.gridy = 1;
		window.add(field, gbc);
		
		send = new JButton("send");
		gbc.gridx = 1;
		gbc.gridy = 1;
		window.add(send, gbc);
		
		add = new JButton("add");
		gbc.gridx = 2;
		gbc.gridy = 1;
		window.add(add, gbc);
	}
	
	private void initToolBar() {
		//JToolBar toolBar = new JToolBar();
		
	}
	
	private void initEvents() {
		window.addComponentListener(new ComponentAdapter() {
		    @Override
			public void componentResized(ComponentEvent componentEvent) {
				Dimension window = componentEvent.getComponent().getBounds().getSize();
				int ninth1 = window.height / 20;
				int ninth9 = ninth1 * 18;
				int third = window.width / 3;
				chatRoomList.setBounds(0, 0, third, ninth9);
				messageList.setBounds(third, 0, third, ninth9);
				userList.setBounds(third * 2, 0, third, ninth9);
				field.setBounds(0, ninth9, third, ninth1);
				send.setBounds(third, ninth9, third, ninth1);
				add.setBounds(third * 2, ninth9, third, ninth1);
		    }
		});
		
		send.addActionListener(new ActionListener() {
	         @Override
			public void actionPerformed(ActionEvent e) {
	        	if (client.chatRoom != null) {
	        		if (client.isIdentified()) {
	        			System.out.println("id");
	        			//send msg
	        			if (!field.getText().isEmpty()) { 
	        				Message<Content> m = new Message<Content>(client.chatRoom.getId(), client.getUser(), new Date(), addCase);
	        				client.sendToServer(new CTSNewMessage(m));
	        				client.chatRoom.addMessage(m);
	        				field.setText("");
	        				window.setEnabled(true);
	        			}
	        		}
	        		else {
	        			System.out.println("noid");
	        			//window.setEnabled(false);
	        			User user = promptIdentity.prompt();
	        			if (user != null) {
	        				client.setUser(user);
	        			}
		        	}
	        	}
	         }
	    });
		
		add.addActionListener(new ActionListener() {
	         @Override
			public void actionPerformed(ActionEvent e) {
	        	 // addCase = ...
	        	 if (!(addCase instanceof ContentText)) {
	        		 window.setEnabled(false);
	        	 }
	         }
	    });
	}
	
	public void addChatRoom(ChatRoom chat) {
		chatRoomListModel.addElement(chat);
		chatRoomList.setModel(chatRoomListModel);
	}
	
	public void addUser(User user) {
		userListModel.addElement(user);
		userList.setModel(userListModel);
	}
	
	public void addMessage(Message<?> msg) {
		messageListModel.addElement(msg);
		messageList.setModel(messageListModel);
	}
	
	public void show() {
		window.setVisible(true);
	}
}
