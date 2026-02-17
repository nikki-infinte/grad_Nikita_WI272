package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.entity.*;
import com.example.demo.repository.h2repo.H2StudentRepository;
import com.example.demo.repository.pgrepo.PgStudentRepository;
import org.springframework.ui.Model;

@Controller
public class StudentController {
	
	
    @Autowired
    private H2StudentRepository h2Repo;

    @Autowired
    private PgStudentRepository pgRepo;

	@GetMapping("/")
	public String showform() {
		//model.addAttribute("student", new Student());
		return "form";
	}
	
	@PostMapping("/save")
	public String saveStudent(Student student) {
	    h2Repo.save(student);
	    pgRepo.save(student);
	    return "redirect:/";
	}

}
