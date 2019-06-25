package com.ibm.demo1.controller;

import com.ibm.demo1.domain.Employee;
import com.ibm.demo1.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class EmployeeController {
    private EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<Iterable<Employee>> getEmployees() {
        Iterable<Employee> allEmployee = employeeService.getAllEmployee();
        return new ResponseEntity<Iterable<Employee>>(allEmployee, HttpStatus.OK);
    }

    @GetMapping("/employee/{name}")
    public ResponseEntity<Employee> getEmployeeByname(@PathVariable String name) {
        return new ResponseEntity<Employee>(employeeService.getEmployeeByName(name), HttpStatus.OK);
    }

    @GetMapping("/employee/{name}/{job}")
    public ResponseEntity<List> getEmployeeDetails(@PathVariable String name, @PathVariable String job) {
        return new ResponseEntity<List>(employeeService.getEmployeeDetails(name, job), HttpStatus.OK);
    }
}
