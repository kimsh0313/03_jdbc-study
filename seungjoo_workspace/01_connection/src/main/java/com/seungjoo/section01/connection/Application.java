package com.seungjoo.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {
        Connection conn = null;

        // 1. JDBC 드라이버 등록(MYSQL 전용의 드라이버 클래스 등록)
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2.db 연결 -> Connection 인스턴스 생성
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb","seungjoo", "seungjoo");    //접속할 db의 url, 접속할 계정, 계정의 비번





        } catch (ClassNotFoundException | SQLException e) { //Driver 클래스를 찾지 못할 경우 발생되는 예(mysql-connector-j라이브러리 등록 안했거나, 오타있거나)

            e.printStackTrace();


        }finally{
            if(conn != null){ //널이면 널.close()가 돼 널포인터 예외 발생 가능
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
        }
    }

}
