package com.sinse.networkapp.echo.backu;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoClient extends JFrame{
	JPanel p_north;
	JComboBox<String> cb_port;
	JTextField t_port;
	JButton bt_port;
	JPanel p_center;
	JTextArea t_text;
	JTextField t_input;
	
	public EchoClient() {
		p_north = new JPanel();
		cb_port  = new JComboBox<String>();
		t_port = new JTextField();
		bt_port = new JButton("접속");
		p_center = new JPanel();
		t_text = new JTextArea();
		t_input = new JTextField();
		
		cb_port.setPreferredSize(new Dimension(200,30));
		t_port.setPreferredSize(new Dimension(60,30));
		bt_port.setPreferredSize(new Dimension(40,30));
		
		t_text.setPreferredSize(new Dimension(300,350));
		t_input.setPreferredSize(new Dimension (300,50));
		
		p_north.add(cb_port);
		p_north.add(t_port);
		p_north.add(bt_port);
		
		p_center.add(t_text);
		p_center.add(t_input);
		
		add(p_north,BorderLayout.NORTH);
		add(p_center);
		
		
		cb_port.addItem("192.168.60.4");
		cb_port.addItem("192.168.60.21");
		cb_port.addItem("192.168.60.45");
		cb_port.addItem("192.168.60.32");
		
		setVisible(true);
		setSize(new Dimension(400,500));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}
	
	public static void main(String[] args) {
		new EchoClient();
		new EchoGUIServer();
	}

}
