package com.sinse.dbproject;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Vector;

public class Homework extends JFrame {
    JTable table;
    DefaultTableModel model;

    public Homework() {
        model = new DefaultTableModel();
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        //  상단 버튼 패널
        JPanel topPanel = new JPanel();
        JButton btnLoad = new JButton("Load Excel");
        JButton btnSave = new JButton("Save to DB");

        topPanel.add(btnLoad);
        topPanel.add(btnSave);
        add(topPanel, BorderLayout.NORTH);

        //  버튼 이벤트
        btnLoad.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                loadExcel(file.getAbsolutePath());
            }
        });

        btnSave.addActionListener(e -> saveToDatabase());

        setTitle("Excel to JTable to DB");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    //  Excel 불러오기
    public void loadExcel(String filePath) {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            model.setRowCount(0);
            model.setColumnCount(0);

            boolean isFirstRow = true;

            for (Row row : sheet) {
                Vector<Object> rowData = new Vector<>();
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING: rowData.add(cell.getStringCellValue()); break;
                        case NUMERIC: rowData.add((int) cell.getNumericCellValue()); break;
                        default: rowData.add(""); break;
                    }
                }

                if (isFirstRow) {
                    for (Object col : rowData) {
                        model.addColumn(col);
                    }
                    isFirstRow = false;
                } else {
                    model.addRow(rowData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "엑셀 로딩 실패: " + e.getMessage());
        }
    }

    //  DB 저장
    public void saveToDatabase() {
        String url = "jdbc:mysql://localhost:3306/dev"; // ⬅ DB 설정
        String user = "java";
        String password = "12341234"; // ⬅ 비밀번호 설정

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);
            String sql = "select * from member4";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (int i = 0; i < model.getRowCount(); i++) {
                    pstmt.setInt(1, Integer.parseInt(model.getValueAt(i, 0).toString()));
                    pstmt.setString(2, model.getValueAt(i, 1).toString());
                    pstmt.setInt(3, Integer.parseInt(model.getValueAt(i, 2).toString()));
                    pstmt.addBatch();
                }

                pstmt.executeBatch();
                conn.commit();
                JOptionPane.showMessageDialog(this, "DB 저장 완료");
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "DB 저장 실패: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
    	new Homework();
    }
}
