package com.sotogito.section02.template;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {


    // db 연결 후 연결된 Connection객체를 반환
    public static Connection getConnection() {

        Properties prop = new Properties();
        Connection conn = null;

        try {
            prop.load(new FileReader("src/main/java/com/sotogito/config/connection-config.properties"));

            Class.forName(prop.getProperty("driver"));
            conn = DriverManager.getConnection(prop.getProperty("url")
                                             , prop.getProperty("user")
                                             , prop.getProperty("password"));

        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }

    public static void close(Connection conn) {
        try {
            if(conn != null && !conn.isClosed()) conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
