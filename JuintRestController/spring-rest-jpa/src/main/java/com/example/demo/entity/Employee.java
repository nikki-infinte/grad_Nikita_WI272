package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Emp")

public class Employee {
	@Id
	private int eid;
	private String name;
	private int age,salary;
	private String designation;
	 public Employee() {}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Employee(int eid, String name, int age, int salary, String designation) {
		super();
		this.eid = eid;
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.designation = designation;
	}
	
	
	
}
