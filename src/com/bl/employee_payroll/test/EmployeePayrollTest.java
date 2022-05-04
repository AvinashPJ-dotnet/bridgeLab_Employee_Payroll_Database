package com.bl.employee_payroll.test;

import com.bl.employee_payroll.Employee_Payroll_Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeePayrollTest {
    @Test
    public void givenSalary_MatchTheUpdatedSalary_ReturnTrue(){
        Employee_Payroll_Repository employee_payroll_repository = new Employee_Payroll_Repository();
        int count = employee_payroll_repository.updateEmployeePayroll("salary", 3000000,5);
        Assertions.assertEquals(1,count);
    }

    @Test
    public void givenName_GetSpecificResult_ReturnData(){
        Employee_Payroll_Repository employee_payroll_repository = new Employee_Payroll_Repository();
        employee_payroll_repository.retrieveDataByName("Terissa");
    }

    @Test
    public void givenRangeOfDate_GetSpecificResult_ReturnData(){
        Employee_Payroll_Repository employee_payroll_repository = new Employee_Payroll_Repository();
        employee_payroll_repository.retrieveDataByDate("2022-05-02" , "2022-05-03");
    }
}
