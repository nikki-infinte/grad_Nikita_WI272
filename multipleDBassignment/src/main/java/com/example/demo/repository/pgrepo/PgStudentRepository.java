package com.example.demo.repository.pgrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Student;

public interface PgStudentRepository extends JpaRepository<Student,Integer>{

}
