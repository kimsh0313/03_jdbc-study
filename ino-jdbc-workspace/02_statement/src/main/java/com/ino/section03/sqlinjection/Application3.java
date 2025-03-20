package com.ino.section03.sqlinjection;

import com.ino.model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ino.common.JDBCTemplate.close;
import static com.ino.common.JDBCTemplate.getConnection;

public class Application3 {
    public static void main(String[] args) {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = "SELECT * FROM employee WHERE EMP_NAME LIKE ?";
        Properties prop = new Properties();
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("insert sirname: ");
        String sirname = sc.nextLine();
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ino/mapper/employee-query.xml"));
            String query = prop.getProperty("selectByEmpByFamilyName");
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, sirname);
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
