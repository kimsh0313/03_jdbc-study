package com.seungjoo.section01.connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Application3 {
    public static void main(String[] args) {
        Properties prop = new Properties();
        Connection conn = null;

        try {
            prop.load(new FileReader("src/main/java/com/seungjoo/connection-config.properties")); //읽어 들이고자하는 파일의 경로를 지정해줘야함

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            Class.forName(driver);

            conn = DriverManager.getConnection(url, user, password);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (conn != null && !conn.isClosed()) { //커넥션이 반납이 되어있는지를 체크해주는 메서드
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }
}
