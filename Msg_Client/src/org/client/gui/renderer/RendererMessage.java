package org.client.gui.renderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.SimpleDateFormat;

import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import org.client.message.Message;

@SuppressWarnings("serial")
public class RendererMessage extends JTextPane implements ListCellRenderer<Message<?>>{

	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("[dd/MM/yy HH:mm:ss] ");
	
	public RendererMessage() {
		this.setFont(new Font("Lucida Console", Font.PLAIN, 12));
	}
	
	private void appendToPane(String msg, Color c) {
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
	
		int len = getDocument().getLength();
		setCaretPosition(len);
		setCharacterAttributes(aset, false);
		replaceSelection(msg);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Message<?>> list, Message<?> value, int index,boolean isSelected, boolean cellHasFocus) {
		setText("");
		appendToPane(FORMATTER.format(value.getDate()), Color.BLACK);
		appendToPane(value.getSender().getUsername(), value.getSender().getColor());
		return this;
	}

}
