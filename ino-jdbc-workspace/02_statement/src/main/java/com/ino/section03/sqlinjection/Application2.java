package com.ino.section03.sqlinjection;

import com.ino.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ino.common.JDBCTemplate.close;
import static com.ino.common.JDBCTemplate.getConnection;

public class Application2 {
    public static void main(String[] args) {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        String sql = "SELECT * FROM employee WHERE emp_id = ?";
        System.out.println("input emp_id: ");
        Scanner sc = new Scanner(System.in);
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sc.nextLine());
            rset = pstmt.executeQuery();
            while(rset.next()) {
                employeeDTOList.add(new EmployeeDTO(
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
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
            close(conn);
        }
        for(EmployeeDTO e : employeeDTOList) {
            System.out.println(e.toString());
        }
    }
}
