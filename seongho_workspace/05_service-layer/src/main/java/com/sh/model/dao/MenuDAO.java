package com.sh.model.dao;

import com.sh.model.dto.CategoryDTO;
import com.sh.model.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.sh.common.JDBCTemplate.close;

public class MenuDAO {
private Properties prop = new Properties();
    public MenuDAO(){
        try {
            prop.load(new FileInputStream("src/main/java/com/sh/config/connection-config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int inserMenu(Connection conn,MenuDTO menu){
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("insertMenu");
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, menu.getMenuName());
            pstmt.setInt(2,menu.getMenuPrice());
            pstmt.setInt(3,menu.getCategoryCode());
            pstmt.setString(4,menu.getOrderableStatus());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
return result;

    }
    public int insertCategory(Connection conn, CategoryDTO category){
        PreparedStatement pstmt = null;
        int result = 0;

        String query = prop.getProperty("insertCategory");
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, category.getCategoryName());
            pstmt.setObject(2,  category.getRefCategoryCode());

            result =pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return result;
    }
}
