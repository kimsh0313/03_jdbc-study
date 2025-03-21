package com.sh.model.service;

import com.sh.model.dao.MenuDAO;
import com.sh.model.dto.CategoryDTO;
import com.sh.model.dto.MenuDTO;

import java.sql.Connection;

import static com.sh.common.JDBCTemplate.getConnection;

public class MenuService {
    /*
    Service
    1. 비지니스 로직 처리와 트랜잭션 관리를 담다앙
        ㄴ사용자의 요청에 따라 순차적으로 실행해야되는 작업들을 하나로 묶어 관리
        중간 ㅗ가정에 문제 발생기 rollback진행해야되므로 하나의 트랜잭션으로 묶어 관리
    2. 처리 과정
    1)Connection
    순차적으로 작업실행
    트랜잭션 처리가 필요할 경우 트랜잭션 처리
    connction반납

    비즈니스 로직 : 데이터베이스와 사용자인터페이스간의 정보 교환을 위한 규칙이나 알고리즘 의미
     */
    public void registCategortAndMenu(CategoryDTO category, MenuDTO menu){
        MenuDAO dao = new MenuDAO();

        Connection conn=getConnection();

        int result1 = dao.insertCategory(conn,category  );
        int result2 = dao.inserMenu(conn,menu);

    }
}
