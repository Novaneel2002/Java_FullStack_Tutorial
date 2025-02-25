package com.ems.EMS.model;

import java.util.List;

public class Employee {
    private int id;
    private String name;
    private String email;
    private String phone;
    private double salary;
    private String designation;
    private Address address;
    private Department department;
    private List<String> skills;
    
    public Employee() {
    }
    
    public Employee(int id, String name, String email, String phone, double salary, String designation, Address address,
                    Department department, List<String> skills) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.designation = designation;
        this.address = address;
        this.department = department;
        this.skills = skills;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public List<String> getSkills() {
        return skills;
    }
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", email=" + email + 
               ", phone=" + phone + ", salary=" + salary + ", designation=" + designation +
               ", address=" + address + ", department=" + department + 
               ", skills=" + skills + "]";
    }
}
