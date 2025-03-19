package com.seungjoo.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application2 {
    public static void main(String[] args) {

//이것은 정적 코딩방식, 좋지 않음, 필요한 것들을 자바 소스코드에작성하는 방식, 외부파일에 작성하면 동적 코딩 방식
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/";
    String user = "seungjoo";
    String password = "seungjoo";
    Connection conn = null;


    try {
        Class.forName("com.mysql.jdbc.Driver");
        //2.db 연결 -> Connection 인스턴스 생성
        conn = DriverManager.getConnection(url,user, password);    //접속할 db의 url, 접속할 계정, 계정의 비번





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
