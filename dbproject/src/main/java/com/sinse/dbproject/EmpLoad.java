package com.sinse.dbproject;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//===============================이론============================
// JT Table is only the shell, so if it's only hundreds of tape
// JT Table is enough.It's just a query question.

//Including WindowListenr,If there are too many methods to override, 
//there is a situation in which parents' methods that are not even used must be left in the class code.
//

public class EmpLoad extends JFrame {
	// Access Once when Frame is popped up, and Close Access when Frame is closed;
	String url = "jdbc:mysql://localhost:3306/dev";
	String user = "java";
	String pwd = "12341234";

	Connection con; // If closing the window, you have to close access to db...
					// so connection have to take member function

	JPanel p_north;
	JButton bt_emp;
	JButton bt_dept;
	JButton bt_execl;

	DataModel model; // Looking at Model Object from JTable
	JTable table;
	JScrollPane scroll;
	JFileChooser chooser = new JFileChooser();
	public EmpLoad() {
		p_north = new JPanel();
		bt_emp = new JButton("emptable load");
		bt_dept = new JButton("dept table load");
		bt_execl = new JButton("excel data load");
		
		table = new JTable(); // when press the button, show the table ... so you don't have to input the
								// model table
		// Connection between model and table don't only access through access object.
		scroll = new JScrollPane(table);
		p_north.add(bt_execl);
		p_north.add(bt_dept);
		p_north.add(bt_emp);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		bt_emp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("you cliked button");
				//loadDept();
			}
		});

		bt_execl.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = chooser.showOpenDialog(EmpLoad.this);
				if(result == JFileChooser.APPROVE_OPTION) {
					//Obtain user selected file information
					File file = chooser.getSelectedFile();
					
					loadExecel(file);
				}
			}
			
		});
		
		bt_dept.addActionListener(new ActionListener() {
			// The listener class defined when implementing the event is not reusable,
			// so there is no need to make it into a .java file.

			// Therefore, the unnamed class among the internal classes is called the
			// anonymous function internal class.
			// Mainly when using one-time objects (ex.event)

			// The anonymous inner class can be used with members of the outer outer class
			// surrounding it.
			// That is, the advantage is that it can be accessed.

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("you cliked button");
				loadEmp("select * from emp");
			}
		});
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				System.exit(0);
			}

		});
		Connect(); // Access Database

		setSize(800, 630);
		setVisible(true);
	}

	// access Database
	public void Connect() {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection(url, user, pwd); // 지역변수 선언 제거!
	        if (con != null) {
	            this.setTitle("Access is successed");
	        } else {
	            this.setTitle("Access denied");
	        }
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	//adding excel data
	
	/*
	 * Java's own API does not have an API that links Excel.
		So let's use a package distributed by apache called POI.
		
		WorkBook > WorkSheet > TableRow > Tablecol
	 */
	public void loadExecel(File file) {
		//Excel 97 ~ 2001 analog xls : HSSFWorkBook
		//current version: XSSWorkbok
		
		
		try {
		    XSSFWorkbook workbook = new XSSFWorkbook(file);
		    // Get First sheet
		    XSSFSheet sheet = workbook.getSheetAt(0);

		    // Get Table Row (first row, header)
		    XSSFRow row = sheet.getRow(0);

		    // Initialize model
		    model = new DataModel();
		    model.title = new String[row.getLastCellNum()];
		    
		    // Read header
		    for (int i = 0; i < model.title.length; i++) {
		        model.title[i] = row.getCell(i).getStringCellValue();
		    }

		    // Initialize data array: number of data rows × number of columns
		    int dataRowCount = sheet.getLastRowNum(); // excluding header
		    int colCount = row.getLastCellNum();
		    model.data = new String[dataRowCount][colCount];

		    // Read each data row starting from row index 1
		    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
		        XSSFRow rw = sheet.getRow(i);
		        for (int j = 0; j < colCount; j++) {
		            XSSFCell cell = rw.getCell(j);
		            if (cell != null) {
		                model.data[i - 1][j] = cell.getStringCellValue();
		                System.out.print(cell.getStringCellValue() + " ");
		            } else {
		                model.data[i - 1][j] = "";
		                System.out.print("EMPTY ");
		            }
		        }
		        System.out.println();
		    }

		    workbook.close();
		    //모든 데이터가 완성되었으므로, 동적으로 적용한다.
			table.setModel(model);
			table.updateUI();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	
	// load EmpTable from database
	public void loadEmp(String sql) {
		
		model = new DataModel();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery(); // 표 반환
			// Current rs is not understandable position. therefore you just put in the rs
			// decorated rs table
			// Checking row about the column
			ResultSetMetaData metadata = pstmt.getMetaData();
			int colCount = metadata.getColumnCount();

			rs.last();
			int rowCount = rs.getRow();

			// Now you know the number of record, column
			// show table in db
			model.data = new String[rowCount][colCount];
			model.title = new String[colCount];

			for (int i = 1; i <= colCount; i++) {
			    model.title[i - 1] = metadata.getColumnClassName(i); // 혹은 getColumnName(i)
			}


			// Transfer and plant data from rs
			rs.beforeFirst();// Can scroll in db

			int rowIndex = 0;
			while (rs.next()) {
				// Repeat until the number of column
				for (int i = 0; i < colCount; i++) {
					model.data[rowIndex][i] = rs.getString(i + 1); // rsgetString is started with 1
				}
				rowIndex++;
			}
			//모든 데이터가 완성되었으므로, 동적으로 적용한다.
			table.setModel(model);
			table.updateUI();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new EmpLoad();
	}

}
