package com.ino.section02.preparedstatement;

import com.ino.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ino.common.JDBCTemplate.close;
import static com.ino.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = "SELECT * FROM employee WHERE dept_code = ?";
        Scanner sc = new Scanner(System.in);
        try {
            pstmt = conn.prepareStatement(sql);
            System.out.print("insert dept_code : ");
            pstmt.setString(1, sc.nextLine());
            rset = pstmt.executeQuery();
            while(rset.next()) {
                EmployeeDTO employeeDTO = new EmployeeDTO(
                        rset.getString("emp_id"),
                        rset.getString("emp_name"),
                        rset.getString("emp_no"),
                        rset.getString("email"),
                        rset.getString("phone"),
                        rset.getString("dept_code"),
                        rset.getString("job_code"),
                        rset.getString("sal_level"),
                        rset.getInt("salary"),
                        rset.getDouble("bonus"),
                        rset.getString("manager_id"),
                        rset.getDate("hire_date"),
                        rset.getDate("quit_date"),
                        rset.getString("quit_yn").charAt(0)
                );
                if (employeeDTO != null) {
                    System.out.println(employeeDTO.toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
            close(conn);
        }
    }
}
