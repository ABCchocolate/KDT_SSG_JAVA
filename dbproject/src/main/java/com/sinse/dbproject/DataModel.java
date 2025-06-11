package com.sinse.dbproject;

import javax.swing.table.AbstractTableModel;

/*
 * JTable is not a subject with actual data, and it is only a UI that users see (like a shell), 
 * so the data to be displayed on JTable depends on the model. 
 * In this way, a method of developing a separate design area (View), 
 * data, and its processing logic (Model) is called an MVC pattern open methodology.
 */
public class DataModel extends AbstractTableModel{

	//Two-dimensional array representing data
	//One-dimensional array representing columns
	String[][] data;
	String[] title;
	
	@Override
	public int getRowCount() {
		
		
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return title.length;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return title[column];
	}
	
	@Override
	
	//Because of Jtable, call method much as row X column
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return data[rowIndex][columnIndex];
	}

}
