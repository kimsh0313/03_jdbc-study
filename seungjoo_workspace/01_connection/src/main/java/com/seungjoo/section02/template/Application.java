package com.seungjoo.section02.template;

import java.sql.Connection;
import java.sql.SQLException;

import static com.seungjoo.section02.template.JDBCTemplate.getConnection; //정적 임포트, 이거 사용하면 정적메소드를 클래스명도 생략 가능
import static com.seungjoo.section02.template.JDBCTemplate.close;
public class Application {
    public static void main(String[] args) throws SQLException {
        Connection conn = getConnection();
        System.out.println(conn);
        System.out.println("sql문 실행완료");

        close(conn);
    }
}
