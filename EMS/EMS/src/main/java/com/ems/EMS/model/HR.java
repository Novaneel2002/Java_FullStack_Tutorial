package com.ems.EMS.model;

import java.util.Map;

public class HR {
	private Map<Integer, Employee> employeeRecords;

	
	public HR() {
	}
	public HR(Map<Integer, Employee> employeeRecords) {
		super();
		this.employeeRecords = employeeRecords;
	}

	public Map<Integer, Employee> getEmployeeRecords() {
		return employeeRecords;
	}

	public void setEmployeeRecords(Map<Integer, Employee> employeeRecords) {
		this.employeeRecords = employeeRecords;
	}
	
	
	public void displayEmployees() {
        for (Employee emp : employeeRecords.values()) {
//        	System.out.println("=------------------------------------------------------------------------------------------=");
            System.out.println(emp.toString());
//            System.out.println("=------------------------------------------------------------------------------------------=");
        }
    }

    @Override
    public String toString() {
        return "HR [employeeRecords=" + employeeRecords + "]";
        
    }
}
