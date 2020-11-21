package org.client.gui;


import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.client.User;

public class GuiUser {
	
	private JComponent[] dialog;
	
	private JTextField username;
	private JColorChooser color;
	
    public GuiUser() {
    	username = new JTextField();
    	color = new JColorChooser();
    	
    	dialog = new JComponent[] {
    	        new JLabel("Username"),
    	        username,
    	        new JLabel("Color"),
    	        color
    	};       
    }
    
    public User prompt() {
    	if (JOptionPane.showConfirmDialog(null, dialog, "Your identity", JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
    		return new User(username.getText(), color.getColor());
    	}
    	return null;
    }

}