package com.ibm.demo1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibm.demo1.domain.Employee;
import com.ibm.demo1.domain.Student;
import com.ibm.demo1.repository.student.StudentRepository;

@Service
public class StudentServiceImpl implements EmployeeService {
	

	public StudentRepository studentRepository;
	
	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}


	@Override
	public Iterable<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Employee getEmployeeByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getEmployeeDetails(String name, String job) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Student> getAllStudent() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

}
