package com.sh.model.dao;
/*
DAO
Data Access Object
데이터베이스 접근용 객체
CRUD 연산을 담당하는 메소드들의 집합으로 이루어진 클래스
실행할 sql문 별로 메소드 따로 구성
 */

import com.sh.model.dto.CategoryDTO;
import com.sh.model.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.sh.common.JDBCTemplate.close;
import static java.awt.SystemColor.menu;

public class MenuDAO {
    private Properties prop = new Properties();

    public  MenuDAO(){
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/sh/mapper/menu-query.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //마지막 메뉴 번호 조회용

    /**
     * 현존하는 메뉴들 중에 가장 마지막 메뉴 번호 조회
     * @param conn db와 연겨로딘 connection 객체
     * @return     조회된 마지막 메뉴 번호
     */
    public int selectLastMenuCode(Connection conn){
        PreparedStatement pstmt = null;
        ResultSet rset=null;
        int lastMenuCode =0;

        String query = prop.getProperty("selectLastMenuCode");
        try {
            pstmt=conn.prepareStatement(query);
            rset=pstmt.executeQuery();
            if (rset.next()){
                lastMenuCode = rset.getInt("last_menu_code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rset);
            close(pstmt);
        }
        return lastMenuCode;
    }
    //카테고리 전체 정보 조회용

    /**
     * 현존하는 카테고리의 전체 정보를 조회해주는 메소드
     * @param conn  db와 연결된 Connection 객체
     * @return      조회결과가 담겨있는 List<CategoryDTO>객체
     */
    public List<CategoryDTO> selectCategoryList(Connection conn){
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<CategoryDTO> list = new ArrayList<>();

        String query = prop.getProperty("selectCategoryList");

        try {
            pstmt = conn.prepareStatement(query);
            rset = pstmt.executeQuery();

            while (rset.next()){
                CategoryDTO category = new CategoryDTO();
                category.setCategoryCode(rset.getInt("category_code"));
                category.setCategoryName(rset.getString("category_name"));
                list.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rset);
            close(pstmt);

        }
        return list;
    }
    //메뉴 등록용

    /**
     * 신규 메뉴를 등록해주는 메소드
     * @param conn db와 연결된 커넥션 객체
     * @param menu insert할 데이터들이 각 필드에 담겨있는 MenuDTO객체 전달
     * @return      insert 후 처리된 행수(삽입된 행 수)
     */
    public int insertMenu(Connection conn, MenuDTO menu){
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("insertMenu");

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,menu.getMenuCode());
            pstmt.setString(2,menu.getMenuName());
            pstmt.setInt(3,menu.getMenuPrice());
            pstmt.setInt(4,menu.getCategoryCode());
            pstmt.setString(5,menu.getOrderableStatus());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return result;
    }
}
