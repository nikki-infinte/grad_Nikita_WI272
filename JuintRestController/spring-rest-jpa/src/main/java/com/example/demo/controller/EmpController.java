package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmpService;

@RestController
@RequestMapping("/employees")
public class EmpController {

    private final EmpService service;

    public EmpController(EmpService service) {
        this.service = service;
    }

    @GetMapping("/greet")
    public String welcome() {
        return "<h1> Welcome</h1>";
    }

    @GetMapping
    public List<Employee> showAllEmp() {
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmpById(@PathVariable int id) {

        Optional<Employee> employee = service.getEmployeeById(id);

        return employee
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Employee> createEmp(@RequestBody Employee e) {

        if (service.getEmployeeById(e.getEid()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Employee saved = service.saveEmployee(e);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmp(@PathVariable int id,
                                              @RequestBody Employee e) {

        try {
            Employee updated = service.updateEmployee(id, e);
            return ResponseEntity.ok(updated);

        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}