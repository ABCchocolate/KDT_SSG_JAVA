package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.SubCategory;
import com.sinse.shopadmin.product.model.TopCategory;

public class SubCategoryDAO {

    DBManager db = DBManager.getInstance();

    public SubCategoryDAO() {
    }

    public List<SubCategory> select(TopCategory topcategory) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<SubCategory> list = new ArrayList<>();

        StringBuffer sql = new StringBuffer();
        StringBuffer sql2 = new StringBuffer();

        try {
            con = db.getConnetion();

            // 1차 쿼리: 하위 카테고리 조회
            sql.append("SELECT * FROM subcategory WHERE topcategory_id = ?");
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1, topcategory.getTopcategory_id());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                SubCategory subcategory = new SubCategory();
                subcategory.setSubcategory_id(rs.getInt("subcategory_id"));
                subcategory.setSub_name(rs.getString("sub_name"));
                subcategory.setTopcategory(topcategory);
                

                list.add(subcategory);
            }
        } finally {
        	db.release(pstmt,rs);
        }

        return list;
    }
}
