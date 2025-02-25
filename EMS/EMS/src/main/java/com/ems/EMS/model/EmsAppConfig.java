package com.ems.EMS.model;
 
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
 
@Configuration
@ComponentScan(basePackages = "com.ems.EMS")
@PropertySource("classpath:application.properties")

public class EmsAppConfig {
 
//    // Address Bean
//    @Bean
//    public Address address() {
//        Address address = new Address();
//        address.setStreet("123 Main St");
//        address.setCity("New Delhi");
//        address.setState("Delhi");
//        address.setZipcode("143890");
//        return address;
//    }
//    
//    @Bean
//    public Department department() {
//        Department department = new Department();
//        department.setDeptId(2);
//        department.setDeptName("Operations");
//        return department;
//    }
//    
//    @Bean
//    public List<String> skills() {
//        return Arrays.asList("Java", "MySQL", "Spring Framework");
//    }
//    
//    @Bean
//    public Employee employee() {
//        Employee employee = new Employee();
//        employee.setId(3);
//        employee.setName("Ankit Goyal");
//        employee.setEmail("ankit@example.com");
//        employee.setPhone("9876543210");
//        employee.setSalary(50000);
//        employee.setDesignation("Software Engineer");
//        employee.setAddress(address());
//        employee.setDepartment(department());
//        employee.setSkills(skills());
//        return employee;
//    }
//    
//    @Bean
//    public Payroll payroll() {
//        Payroll payroll = new Payroll();
//        payroll.setEmployeeId(3); // Ensure this matches the employee ID
//        payroll.setBaseSalary(66000);
//        payroll.setBonuses(5000);
//        payroll.setDeductions(3000);
//        return payroll;
//    }
//    
//    @Bean
//    public Performance performance() {
//        Performance performance = new Performance();
//        performance.setEmployee(3); // Use correct method name
//        performance.setRating(4.9);
//        performance.setFeedback("Excellent performance");
//        performance.setProjectsHandled(Arrays.asList("Project A", "Project B", "Project C"));
//        performance.setEligibleForpromotion(true); // Use correct method name
//        return performance;
//    }
////    
//    @Bean
//    public Map<Integer, Employee> empRecords() {
//        Map<Integer, Employee> records = new HashMap<>();
//        records.put(1, employee());
//        return records;
//    }
//    
//    @Bean
//    public HR hr() {
//        HR hr = new HR();
//        hr.setEmployeeRecords(empRecords());
//        return hr;
//    }
	
	
	//Address Bean
	@Value("${address.street}")
	private String street;
	@Value("${address.city}")
	private String city;
	@Value("${address.state}")
	private String state;
	@Value("${address.zipcode}")
	private String zipcode;
	
	
	@Bean
	public Address address() {
      Address address = new Address();
      address.setStreet(street);
      address.setCity(city);
      address.setState(state);
      address.setZipcode(zipcode);
      return address;
	}
	
	
	//Department Bean
	@Value("${department.id}")
	private int depid;
	@Value("${department.name}")
	private String depname;
	
  @Bean
  public Department department() {
      Department department = new Department();
      department.setDeptId(depid);
      department.setDeptName(depname);
      return department;
  }
  
  
  @Value("${employee.id}")
  private int empid;
  @Value("${employee.name}")
  private String empname;
  @Value("${employee.email}")
  private String email;
  @Value("${employee.phone}")
  private String phone;
  @Value("${employee.salary}")
  private double salary;
  @Value("${employee.designation}")
  private String designation;
  @Value("${employee.skills}")
  private String skillsString;
  
  @Bean
  public List<String> skills(){
	  return Arrays.asList(skillsString.split(","));
  }
  @Bean
  public Employee employee() {
    Employee employee = new Employee();
    employee.setId(empid);
    employee.setName(empname);
    employee.setEmail(email);
    employee.setPhone(phone);
    employee.setSalary(salary);
    employee.setDesignation(designation);
    employee.setAddress(address());
    employee.setDepartment(department());
    employee.setSkills(skills());
    return employee;
  }	
  

	
  
  @Value("${performance.employeeId}")
  private int perempid;
  @Value("${performance.rating}")
  private double rating;
  @Value("${performance.feedback}")
  private String feedback;
  @Value("${performance.projectsHandled}")
  private String prohandString;
  @Value("${performance.eligibleForPromotion}")
  private boolean iseli;
  
  
  @Bean
  public List<String> projects(){
	  return Arrays.asList(prohandString.split(","));
  }
  @Bean
public Performance performance() {
    Performance performance = new Performance();
    performance.setEmployee(perempid); // Use correct method name
    performance.setRating(rating);
    performance.setFeedback(feedback);
    performance.setProjectsHandled(projects());
    performance.setEligibleForpromotion(iseli); // Use correct method name
    return performance;
}
  
  
  
  @Value("${payroll.employeeId}")
  private int payempid;
  
  @Value("${payroll.baseSalary}")
  private int basesal;
  
  @Value("${payroll.bonuses}")
  private int bonus;
  
  @Value("${payroll.deductions}")
  private int deductions;
  
  @Bean
public Payroll payroll() {
    Payroll payroll = new Payroll();
    payroll.setEmployeeId(payempid); // Ensure this matches the employee ID
    payroll.setBaseSalary(basesal);
    payroll.setBonuses(bonus);
    payroll.setDeductions(deductions);
    return payroll;
}
  
  
  @Bean
public Map<Integer, Employee> empRecords() {
    Map<Integer, Employee> records = new HashMap<>();
    records.put(empid, employee());
    return records;
}

@Bean
public HR hr() {
    HR hr = new HR();
    hr.setEmployeeRecords(empRecords());
    return hr;
}
	
}
