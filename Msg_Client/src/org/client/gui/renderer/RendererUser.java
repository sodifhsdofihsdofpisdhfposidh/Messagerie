package org.client.gui.renderer;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.client.User;


@SuppressWarnings("serial")
public class RendererUser extends JLabel implements ListCellRenderer<User>{
	
	public RendererUser() {
		this.setFont(new Font("Lucida Console", Font.PLAIN, 12));
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends User> list, User value, int index, boolean isSelected, boolean cellHasFocus) {
		setText(value.getUsername());
		setForeground(value.getColor());
		return this; 
	}

}
