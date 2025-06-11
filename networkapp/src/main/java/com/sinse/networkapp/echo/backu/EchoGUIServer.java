package com.sinse.networkapp.echo.backu;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoGUIServer extends JFrame {
	JPanel p_north;
	JTextField t_port; //포트번호 표시
	JButton bt; //이동버튼 표
	JPanel p_center;
	JTextArea t_text; //실제 텍스트 표
	
	public EchoGUIServer() {
		p_north = new JPanel();
		t_port = new JTextField();
		bt = new JButton();
		p_center = new JPanel();
		t_text = new JTextArea();
		
		t_port.setPreferredSize(new Dimension(200,30));
		bt.setPreferredSize(new Dimension(60,30));
		t_text.setPreferredSize(new Dimension(260,300));
		p_north.add(t_port);
		p_north.add(bt);
		p_center.add(t_text);
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		setVisible(true);
		setBounds(0, 300, 300, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		new EchoGUIServer();
	}
}
