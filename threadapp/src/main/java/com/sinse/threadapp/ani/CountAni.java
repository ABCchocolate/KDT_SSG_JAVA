package com.sinse.threadapp.ani;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CountAni extends JFrame {
	//레이아웃 만들기
	JPanel p_north;
	JPanel p_center;
	JTextField t_countup;
	JTextField t_countup2;
	JButton bt;
	
	Thread t1;
	Thread t2;
	
	public CountAni() {
		p_north= new JPanel();
		p_center = new JPanel();
		t_countup = new JTextField();
		t_countup2 = new JTextField();
		bt = new JButton("start");
		
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		
		p_center.setLayout(new FlowLayout());
		p_center.add(t_countup);
		p_center.add(t_countup2);
		t_countup.setPreferredSize(new Dimension(400,600));
		t_countup2.setPreferredSize(new Dimension(400,600));
		add(p_center);
		t_countup.setFont(new Font(null, Font.BOLD, 32));
		t_countup2.setFont(new Font(null, Font.BOLD, 32));
		setSize(1200,1400);
		setVisible(true);
		
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				t1 = new CountAniThread(t_countup, 0, 1000);
				t2 = new CountAniThread(t_countup2, 0, 500);
				
				t1.start();
				t2.start();
				
			}
		});
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		new CountAni();
	}
	
}
