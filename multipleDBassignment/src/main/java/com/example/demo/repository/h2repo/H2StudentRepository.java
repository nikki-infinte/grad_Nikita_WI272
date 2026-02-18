package com.example.demo.repository.h2repo;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface H2StudentRepository extends JpaRepository<Student,Integer> {

}
