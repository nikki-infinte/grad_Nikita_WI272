package com.example.demo.service;
import com.example.demo.entity.*;
import java.util.*;

import org.springframework.stereotype.Service;


@Service
public interface EmpService {

	  List<Employee> getAllEmployees();

	    Optional<Employee> getEmployeeById(int id);

	    Employee saveEmployee(Employee e);

	    Employee updateEmployee(int id, Employee e);

}
