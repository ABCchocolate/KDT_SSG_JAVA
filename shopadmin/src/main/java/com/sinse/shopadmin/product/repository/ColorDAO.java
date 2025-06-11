package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.Color;

/**
 * Color 테이블의 쿼리 전담 객체
 */
public class ColorDAO {
    DBManager dbManager = DBManager.getInstance();

    /**
     * 색상 등록
     */
    public int insert(Color color) {
        Connection connection = null;
        PreparedStatement pStatement = null;
        int result = 0;
        
        String sql = "INSERT INTO color(color_name) VALUES(?)";

        try {
            connection = dbManager.getConnetion();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, color.getColor_name());

            result = pStatement.executeUpdate(); // DML 수행
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.release(pStatement);
           
        }

        return result;
    }

    /**
     * 모든 색상 조회
     */
    public ArrayList<Color> selectAll() {
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        ArrayList<Color> list = new ArrayList<>();

        String sql = "SELECT * FROM color";

        try {
            connection = dbManager.getConnetion(); // 오타 수정
            pStatement = connection.prepareStatement(sql);
            rSet = pStatement.executeQuery();

            while (rSet.next()) {
                Color color = new Color();
                color.setColor_id(rSet.getInt("color_id"));
                color.setColor_name(rSet.getString("color_name"));
                list.add(color);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.release(pStatement, rSet);
           
        }

        return list;
    }
}
