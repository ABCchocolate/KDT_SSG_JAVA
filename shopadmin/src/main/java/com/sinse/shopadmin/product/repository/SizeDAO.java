package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.Size;

public class SizeDAO {
	DBManager db= DBManager.getInstance();
	
	public int insert(Size size) {
	    int result = 0;
	    String sql = "IN INTO size(size_name) VALUES(?)";

	    try (Connection con = db.getConnetion();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {
	        
	        pstmt.setString(1, size.getSize_name()); // 값 바인딩
	        result = pstmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace(); // 필요 시 로깅 처리
	    }

	    return result;
	}

	
	public ArrayList<Size> selectAll() {
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        ArrayList<Size> list = new ArrayList<>();

        String sql = "SELECT * FROM size";

        try {
            connection = db.getConnetion(); // 오타 수정
            pStatement = connection.prepareStatement(sql);
            rSet = pStatement.executeQuery();

            while (rSet.next()) {
                Size size = new Size();
                size.setSize_id(rSet.getInt("size_id"));
                size.setSize_name(rSet.getString("size_name"));
                list.add(size);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.release(pStatement, rSet);
        }

        return list;
    }
}
