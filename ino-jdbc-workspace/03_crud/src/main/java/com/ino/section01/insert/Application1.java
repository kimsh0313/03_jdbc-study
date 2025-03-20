package com.ino.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ino.common.JDBCTemplate.close;
import static com.ino.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        Properties prop = new Properties();
        Scanner sc = new Scanner(System.in);
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ino/mapper/menu-query.xml"));
            String sql = prop.getProperty("insertMenu");
            pstmt = conn.prepareStatement(sql);
            System.out.print("insert name: ");
            String mName = sc.nextLine();
            System.out.print("insert price: ");
            int menuPrice = sc.nextInt();
            System.out.print("insert cCode : ");
            int categoryCode = sc.nextInt();
            sc.nextLine();
            System.out.println("insert orderable_status: ");
            char orderable_status = sc.nextLine().charAt(0);
            pstmt.setString(1, mName);
            pstmt.setInt(2, menuPrice);
            pstmt.setInt(3, categoryCode);
            pstmt.setString(4, String.valueOf(orderable_status));
            result = pstmt.executeUpdate();
            System.out.println(result);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(conn);
        }

    }
}
