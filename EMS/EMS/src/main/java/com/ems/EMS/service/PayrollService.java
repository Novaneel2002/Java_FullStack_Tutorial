package com.ems.EMS.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.ems.EMS.model.Payroll;
 
@Service
public class PayrollService {
 
    @Autowired
    private Payroll payroll;
 
    public double calculateNetSalary() {
        return payroll.getBaseSalary() + payroll.getBonuses() - payroll.getDeductions();
    }
 
    public void printPayrollReceipt() {
        double netSalary = calculateNetSalary();
        System.out.println("Payroll Receipt:");
        System.out.println("Base Salary: " + payroll.getBaseSalary());
        System.out.println("Bonuses: " + payroll.getBonuses());
        System.out.println("Deductions: " + payroll.getDeductions());
        System.out.println("Net Salary: " + netSalary);
    }
}