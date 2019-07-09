package com.ibm.demo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibm.demo1.domain.Employee;
import com.ibm.demo1.domain.Student;
import com.ibm.demo1.service.StudentServiceImpl;

@Controller
@RequestMapping("/api")
public class StudentController {
	private StudentServiceImpl studentServiceImpl;

	@Autowired
	public StudentController(StudentServiceImpl studentServiceImpl) {
		super();
		this.studentServiceImpl = studentServiceImpl;
	}
	
	@GetMapping("/students")
	 public ResponseEntity<Iterable<Student>> getStudents() {
        Iterable<Student> allStudent = studentServiceImpl.getAllStudent();
        return new ResponseEntity<Iterable<Student>>(allStudent, HttpStatus.OK);
    }

	

}
