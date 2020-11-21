package org.client.gui.renderer;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.client.message.ChatRoom;


@SuppressWarnings("serial")
public class RendererChatRoom extends JLabel implements ListCellRenderer<ChatRoom>{

	public RendererChatRoom() {
		this.setFont(new Font("Lucida Console", Font.PLAIN, 12));
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends ChatRoom> list, ChatRoom value, int index, boolean isSelected, boolean cellHasFocus) {
		setText(value.getName());
		return this;
	}

}
