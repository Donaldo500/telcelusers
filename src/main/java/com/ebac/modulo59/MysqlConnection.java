package com.ebac.modulo59;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {
    public MysqlConnection(){
        //NOT NECESSARY
        //Class.forName("com.mysql.jdbc.Driver");
    }

    public Connection getConnection(String url, String username, String password) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}

