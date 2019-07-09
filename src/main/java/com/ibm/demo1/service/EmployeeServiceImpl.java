package com.ibm.demo1.service;

import com.ibm.demo1.domain.Employee;
import com.ibm.demo1.domain.Student;
import com.ibm.demo1.repository.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {


	public EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public Iterable<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public List getEmployeeDetails(String name, String job) {
		return employeeRepository.find(name, job);
	}

	@Override
	public Employee getEmployeeByName(String name) {
		return employeeRepository.findByName(name);
	}

	@Override
	public Iterable<Student> getAllStudent() {
		// TODO Auto-generated method stub
		return null;
	}
}
