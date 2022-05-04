package com.bl.employee_payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static String url = "jdbc:mysql://localhost:3306/payroll_service";
    private static String dbUserName = "root";
    private static String dbPass = "Deed747558@";
    public Connection getConnection(){
        try{
            //1. load driver class
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.connection
            Connection con = DriverManager.getConnection(url, dbUserName, dbPass);
            return con;
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private static DBConnection dbConnection = null;
    DBConnection()
    {
    }

    // Static method
    // Static method to create instance of Singleton class
    public static DBConnection getInstance()
    {
        if (dbConnection == null)
            dbConnection = new DBConnection();

        return dbConnection;
    }
}
