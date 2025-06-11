package com.sinse.dbproject;

import java.awt.event.WindowAdapter;

/*
 * In the case of interfaces with three or more methods to override, such as keylistener and windowlistner, 
 * intermediate objects that have already implemented the listener interface in java api support 
 * instead of the developer are called adapters.
 * 
 * Because the method has been redefined instead of us, the developer has no obligation to implement it.
 */

public class MyWindowAdapter extends WindowAdapter{
	private void windowClosing() {
		// TODO Auto-generated method stub
		System.out.println("window closing");
	}

}
