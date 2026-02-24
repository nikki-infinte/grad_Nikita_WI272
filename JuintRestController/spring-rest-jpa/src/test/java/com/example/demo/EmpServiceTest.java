package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.demo.service.EmpServiceImp;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmpRepository;

@ExtendWith(MockitoExtension.class)
class EmpServiceTest {
	

	    @Mock
	    private EmpRepository repository;

	    @InjectMocks
	    private EmpServiceImp service;

	    @Test
	    void testEmployeePresent() {

	        Employee emp = new Employee();
	        emp.setEid(104);
	        emp.setName("Priya Das");

	        when(repository.findById(104))
	                .thenReturn(Optional.of(emp));

	        Optional<Employee> result = service.getEmployeeById(104);

	        assertTrue(result.isPresent());
	        assertEquals(104, result.get().getEid());
	        assertEquals("Priya Das", result.get().getName());
	        verify(repository, times(1)).findById(104);
	    }

	    @Test
	    void testEmployeeNotPresent() {

	        when(repository.findById(2))
	                .thenReturn(Optional.empty());

	        Optional<Employee> result = service.getEmployeeById(2);

	        assertFalse(result.isPresent());

	        verify(repository, times(1)).findById(2);
	    }
	
}
