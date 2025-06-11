package com.sinse.threadapp.ani;

import javax.swing.JTextField;

public class CountAniThread extends Thread{
	JTextField textfield;
	private int count;
	private int n;
	
	public CountAniThread(JTextField textField, int start, int n) {
		this.textfield = textField;
		this.count = start;
		this.n = n;
	}
	
	@Override
	public void run() {
		while (true) {
			count++;
			textfield.setText(String.valueOf(count));
			try {
				Thread.sleep(n); // 1초 대기
			} catch (InterruptedException e) {
				break;
			}
		}
	}
	
}
