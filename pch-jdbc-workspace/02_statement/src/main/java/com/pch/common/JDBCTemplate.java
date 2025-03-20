package com.pch.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {
    public static Connection getConnection() {
        Properties prop = new Properties();
        Connection conn = null;

        //DriverManager를 활용한 Connnection 객체 생성 코드 생략
        try {
            prop.load(new FileReader("src/main/java/com/pch/config/connection-config.properties"));

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (IOException | ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return conn;
    }

    public static void close(Connection con) {
        try {
            if(con != null & !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
