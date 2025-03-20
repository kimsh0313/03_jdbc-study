package com.podoseee.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.podoseee.common.JDBCTemplate.close;
import static com.podoseee.common.JDBCTemplate.getConnection;

public class Application3 {
    public static void main(String[] args) {

        // 1. db 연결된 Connection 객체 필요
        Connection conn = getConnection();

        Statement stmt = null;
        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("조회하고자하는 사번을 입력해주세요: ");
        String empId = sc.nextLine();

        String query = "SELECT emp_id, emp_name, salary FROM employee WHERE emp_id = '" + empId + "'";

        //System.out.println(query);

        try {
            // 2. Statement 객체 생성
            stmt = conn.createStatement();
            // 3. sql문 실행 후 결과 받기
            rset = stmt.executeQuery(query);

            // 4. 결과 로직 처리
            if(rset.next()){
                System.out.println(rset.getString("emp_id") + ", " + rset.getString("emp_name") + ", " + rset.getInt("salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. 자원반납
            close(rset);
            close(stmt);
            close(conn);
        }

    }
}