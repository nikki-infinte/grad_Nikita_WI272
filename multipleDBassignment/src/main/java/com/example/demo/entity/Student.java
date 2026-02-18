package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="students")
public class Student {

	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long rollno;

	    private String name;
	    private Integer standard;
	    private String school;
		public Long getRollno() {
			return rollno;
		}
		public void setRollno(Long rollno) {
			this.rollno = rollno;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getStandard() {
			return standard;
		}
		public void setStandard(Integer standard) {
			this.standard = standard;
		}
		public String getSchool() {
			return school;
		}
		public void setSchool(String school) {
			this.school = school;
		}
		public Student(Long rollno, String name, Integer standard, String school) {
			super();
			this.rollno = rollno;
			this.name = name;
			this.standard = standard;
			this.school = school;
		}
		public Student() {
			
		}

}
