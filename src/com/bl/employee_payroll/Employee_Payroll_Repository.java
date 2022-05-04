package com.bl.employee_payroll;

import java.sql.*;

public class Employee_Payroll_Repository {
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
            Connection con = DBConnection.getConnection();
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

    public int updateEmployeePayroll(String colName, String updateValue, int id) {
        int count = 0;
        try {
            Connection con = DBConnection.getConnection();
            String updateQuery = "update employee_payroll set "+ colName + " = " + updateValue + " where id =" +id ;
            assert con != null;
            Statement stmt = con.createStatement();
            count = stmt.executeUpdate(updateQuery);

        }catch (SQLException e){
            System.out.println(e);
        }
        return count;
    }
}
