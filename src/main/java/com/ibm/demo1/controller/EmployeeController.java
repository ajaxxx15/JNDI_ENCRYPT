package com.ibm.demo1.controller;

import com.ibm.demo1.configuration.CipherEncrypte;
import com.ibm.demo1.domain.Employee;
import com.ibm.demo1.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
    
    @GetMapping("/getencryption/{password}")
    public ResponseEntity<String> encryption(@PathVariable String password) {
    	
    	
    	String encryptpassword = null;
		
			try {
				encryptpassword = new CipherEncrypte().encrypt(password);
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
					| BadPaddingException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
    	
    	return new ResponseEntity<String> (encryptpassword,HttpStatus.OK);
    	
    }
}
