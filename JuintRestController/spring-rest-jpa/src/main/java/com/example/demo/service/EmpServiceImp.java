package com.example.demo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmpRepository;
@Service
public class EmpServiceImp implements EmpService{

	  private final EmpRepository er;
	public EmpServiceImp(EmpRepository er) {
		 this.er = er;
		// TODO Auto-generated constructor stub
	}
	
	
	 @Override
	    public List<Employee> getAllEmployees() {
	        return er.findAll();
	    }

	    @Override
	    public Optional<Employee> getEmployeeById(int id) {
	        return er.findById(id);
	    }
	    
	    @Override
	    public Employee saveEmployee(Employee e) {
	        return er.save(e);
	    }

	    @Override
	    public Employee updateEmployee(int id, Employee e) {

	        if (!er.existsById(id)) {
	            throw new RuntimeException("Employee not found with id: " + id);
	        }

	        if (e.getEid() != id) {
	            throw new RuntimeException("Employee IDs do not match");
	        }

	        return er.save(e);
	    }   

}
