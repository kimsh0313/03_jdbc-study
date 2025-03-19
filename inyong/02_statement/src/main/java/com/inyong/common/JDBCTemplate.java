package com.inyong.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {

    // Connection 생성 후 반환
    public static Connection getConnection() {
        Properties prop = new Properties();
        Connection conn = null;

        try {
            prop.load(new FileReader("src/main/java/com/inyong/config/connection-config.properties"));

            Class.forName(prop.getProperty("driver"));


            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));

        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }


        return conn;
    }
    // 전달받은 Connection 반납

    public static void close(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

}
