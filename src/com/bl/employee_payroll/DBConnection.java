package com.bl.employee_payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static String url = "jdbc:mysql://localhost:3306/blz";
    private static String dbUserName = "root";
    private static String dbPass = "Deed747558@";
    public static Connection getConnection(){
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
}
