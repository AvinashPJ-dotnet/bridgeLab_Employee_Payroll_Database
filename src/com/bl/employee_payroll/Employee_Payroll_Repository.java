package com.bl.employee_payroll;

import java.sql.*;

public class Employee_Payroll_Repository {
    DBConnection dbConnection = null;
    //DQL: select -> executeQuery();
    static String selectQuery = "select * from employee_payroll;";
    //DML->executeUpdate()
    public void start() {
        try {
            retrieveAllData();


        }catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    public void retrieveAllData(){
        try{
            dbConnection = DBConnection.getInstance();
            Connection con = dbConnection.getConnection();;
            assert con != null;
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(selectQuery);
            System.out.println("id | name | gender | salary | start date | phone | address");
            while (rs.next()) {
                System.out.println(rs.getInt(1) +
                        " | " + rs.getString(2) +
                        " | " + rs.getString(3)+
                        " | " + rs.getFloat(4)+
                        " | " + rs.getDate(5)+
                        " | " + rs.getLong(6)+
                        " | " + rs.getString(7));
            }
            stmt.close();
            con.close();

            System.out.println("Done!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void retrieveDataByName(String name){
        try{
            dbConnection = DBConnection.getInstance();
            Connection con = dbConnection.getConnection();
            assert con != null;
            PreparedStatement stmt = con.prepareStatement("select * from employee_payroll where name = ?;");
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();

            displayData(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void retrieveDataByDate(String dateFrom, String dateTo) {
        try {
            dbConnection = DBConnection.getInstance();
            Connection con = dbConnection.getConnection();
            assert con != null;
            PreparedStatement stmt = con.prepareStatement("select * from employee_payroll where start_date between cast(? as Date) and cast(? as Date);");
            stmt.setDate(1, Date.valueOf(dateFrom));
            stmt.setDate(2, Date.valueOf(dateTo));
            ResultSet rs = stmt.executeQuery();

            displayData(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getCalculatedData(String gender){
        try{
            dbConnection = DBConnection.getInstance();
            Connection con = dbConnection.getConnection();
            assert con != null;
            PreparedStatement stmt = con.prepareStatement("select " +
                    "max(salary)," +
                    "min(salary)," +
                    "avg(salary)," +
                    "count(salary)," +
                    "sum(salary) as Sum from employee_payroll where gender = ? group by gender;");
            stmt.setString(1, gender);
            ResultSet rs =stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Max Salary");
                System.out.println(rs.getInt(1));
                System.out.println("Min Salary");
                System.out.println(rs.getInt(2));
                System.out.println("Avg Salary");
                System.out.println(rs.getInt(3));
                System.out.println("Count Salary");
                System.out.println(rs.getInt(4));
                System.out.println("Sum Salary");
                System.out.println(rs.getInt(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayData(ResultSet rs) {
        try{
            System.out.println("id | name | gender | salary | start date | phone | address");
            while (rs.next()) {
                System.out.println(rs.getInt(1) +
                        " | " + rs.getString(2) +
                        " | " + rs.getString(3)+
                        " | " + rs.getFloat(4)+
                        " | " + rs.getDate(5)+
                        " | " + rs.getLong(6)+
                        " | " + rs.getString(7));
            }
        }catch (SQLException e){
            System.out.println(e);
        }

    }

    public int updateEmployeePayroll(String colName, long updateValue, int id) {
        int count = 0;
        try {
            dbConnection = DBConnection.getInstance();
            Connection con = dbConnection.getConnection();
            String updateQuery = "update employee_payroll set "+ colName +"=? where id = ?;" ;
            assert con != null;
            PreparedStatement stmt = con.prepareStatement(updateQuery);
            stmt.setLong(1,updateValue);
            stmt.setLong(2,id);
            count = stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
        return count;
    }
}
