package com.sinse.graphic;

import java.awt.Graphics;

import javax.swing.JPanel;

public class MyPanel extends JPanel{
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g); //DO NOT REMOVE
		
		g.drawRect(100, 50, 200, 200);
	}
}
