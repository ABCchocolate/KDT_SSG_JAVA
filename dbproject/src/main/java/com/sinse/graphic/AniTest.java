package com.sinse.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AniTest extends JFrame{
	/*
	 * All of Java's components paint themselves. However, the components that
	 * developers can take away pictures are mainly empty container types, JPanel,
	 * Canvas
	 */
	JButton bt;
	JPanel p_center;
	int x = 100;
	int y = 50;

	public AniTest() {
		// TODO Auto-generated constructor stub
		bt = new JButton() {
			@Override
			public void paint(Graphics g) {
				// TODO Auto-generated method stub
				super.paint(g);
				g.setColor(Color.red);
				g.fillRect(0,0,70,30);
			}
		};
		
		add(bt, BorderLayout.NORTH);
		p_center= new JPanel(){
			@Override
			public void paint(Graphics g) {
				// TODO Auto-generated method stub
				super.paint(g); //DO NOT REMOVE
				
				g.drawRect(x, y, 200, 200);
				g.drawOval(x+ 100, y + 100, 100, 100);
			}
		};
		
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				x += 5;
				y += 5;
				
				p_center.repaint(); //update() -> paint()
			}
		});
		
		add(p_center);
		setSize(800,700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new AniTest();
	}
}
