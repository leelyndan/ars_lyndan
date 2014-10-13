package com.ars.common.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao
{
    protected Connection getConnection() throws SQLException,
            java.lang.ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/ars";
        String username = "root";
        String password = "root";
        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }

}
