package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
    private static final String url="jdbc:mysql://localhost:3306/lab8";
    private static final String user="root";
    private static final String password="parola";
    private static Connection connection=null;

    private Database(){}

    public static Connection getConnection()
    {
        if(connection==null)
            createConnection();
        return connection;
    }

    private static void createConnection()
    {
        try
        {
            connection = DriverManager.getConnection(url,user,password);
            connection.setAutoCommit(false);
        }
        catch(SQLException e)
        {
            System.err.println(e);
        }
    }

    public static void closeConnection()
    {
        if(connection!=null)
        {
            try
            {
                connection.close();
                connection=null;
            }
            catch(SQLException e)
            {
                System.err.println(e);
            }
        }
    }
}
