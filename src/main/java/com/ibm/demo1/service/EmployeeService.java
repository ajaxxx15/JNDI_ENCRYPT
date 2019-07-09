package com.ibm.demo1.service;


import com.ibm.demo1.domain.Employee;
import com.ibm.demo1.domain.Student;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    public  Iterable<Employee> getAllEmployee();
    public  Iterable<Student> getAllStudent();
    public Employee getEmployeeByName(String name);
    public List getEmployeeDetails(String name, String job);
}
