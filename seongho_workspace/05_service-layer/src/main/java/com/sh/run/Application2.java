package com.sh.run;

import com.sh.model.dao.MenuDAO;
import com.sh.model.dto.MenuDTO;

import java.sql.Connection;
import java.sql.SQLException;

import static com.sh.common.JDBCTemplate.getConnection;

public class Application2 {    public static void main(String[] args) {
    //신규 메뉴 등록 요청

    //사용자에게 값 입력
    String menuName = "정어리비빔밥";
    int menuPrice = 50000;
    int categoryCode = 4;
    String orderableStatus = "Y";
    // DTO객체에 사용자 입려값 담기
    MenuDTO menu = new MenuDTO();
    menu.setMenuName(menuName);
    menu.setMenuPrice(menuPrice);
    menu.setCategoryCode(categoryCode);
    menu.setOrderableStatus(orderableStatus);
    // SQL문 실행해주는 DAO측 메소드 호출 후 결과받기
    Connection conn = getConnection();
    try {
        System.out.println("상태 : "+ conn.getAutoCommit());
    } catch (SQLException e) {
        e.printStackTrace();
    }


    MenuDAO dao = new MenuDAO();
    int result = dao.inserMenu(conn, menu);
    // 결과를 가지고 응답메시지 출력
    if (result>0){ //성공적으로 수행
        try {
            if (conn != null && !conn.isClosed())
                conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }else{
        try {
            if (conn != null && !conn.isClosed())
                conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }}
}
}
