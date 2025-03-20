package com.ino.section03.sqlinjection;

import com.ino.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ino.common.JDBCTemplate.close;
import static com.ino.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rset = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("input empId: ");
        String empId = sc.nextLine();
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        String query = "SELECT * FROM employee WHERE emp_id = '" + empId+"'";

        try {
            stmt = conn.createStatement();
            rset = stmt.executeQuery(query);
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
            close(stmt);
            close(conn);
        }
        for(EmployeeDTO e : employeeDTOList) {
            System.out.println(e.toString());
        }
    }
}
