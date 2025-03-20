package com.ino.section01.statement;

import com.ino.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ino.common.JDBCTemplate.close;
import static com.ino.common.JDBCTemplate.getConnection;

public class Application3 {
    public static void main(String[] args) {

        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("insert emp_id : ");
        String empId = sc.nextLine();
        String sql = "SELECT * FROM employee WHERE emp_id = "+empId;

        try {
            stmt = conn.createStatement();
            rset = stmt.executeQuery(sql);
            while(rset.next()) {
                // System.out.println("empid : " + rset.getString("emp_id") + ", empname : " + rset.getString("emp_name") + ", salary : " + rset.getInt("salary"));
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
            sc.close();
            close(rset);
            close(stmt);
            close(conn);
        }
    }
}
