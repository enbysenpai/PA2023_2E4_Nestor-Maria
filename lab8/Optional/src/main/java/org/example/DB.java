package org.example;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DB {
    private static final String url = "jdbc:mysql://localhost:3306/lab8";
    private static final String user = "root";
    private static final String password = "parola";
    private static BasicDataSource dataSource;

    public static Connection getConnection() throws SQLException {
        dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource.getConnection();
    }
}
