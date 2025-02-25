package com.ems.EMS.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.ems.EMS.model.Employee;
 
@Service
public class EmployeeService {
 
	@Autowired
	private Employee employee;
	//print the employee details
	public void printEmpDetails() {
		System.out.println("Employee Details : "+employee);
	}
	//call manually
	public double calAnnualSalary() {
		return employee.getSalary()*12;
	}
	//fetch the employee skills
	public List<String> getEmployeeSkills(){
		return employee.getSkills();
	}
	//eligible for promotion or not
	public boolean isEligiblePromotion() {
		return employee.getDesignation().equalsIgnoreCase("Senior Developer");
	}

}