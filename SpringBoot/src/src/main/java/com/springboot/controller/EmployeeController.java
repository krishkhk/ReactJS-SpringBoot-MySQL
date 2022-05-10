package com.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Employee;
import com.springboot.repo.EmployeeRepository;

import Exception.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:3002")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	
	@Autowired
	private EmployeeRepository empRepo;

	// get all Employees details

//	@PostMapping("/employees")
	@RequestMapping(path="/{employeesList}", method = RequestMethod.GET)
	public List<Employee> getAllEmployees() {
		return empRepo.findAll();

	}

	// creating employe REST API

	@RequestMapping(path="/{employees}", method = RequestMethod.POST)
	public Employee create(@RequestBody Employee employee) {
		return empRepo.save(employee);

	}

	// get Employe By Id REST API
    @GetMapping("/employees/{id}")
    public ResponseEntity < Employee > getEmployeeById(@PathVariable Long id) {
        Employee employee = empRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id :" + id));
        return ResponseEntity.ok(employee);
    }
	//update Employee
    
    @PutMapping("/employees/{id}")
    public ResponseEntity < Employee > updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
    	 Employee employee = empRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id :" + id));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailid(employeeDetails.getEmailid());

        Employee updatedEmployee = empRepo.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
	
    //Delete Employee
    
    @DeleteMapping("/employees/{id}")
    
    public ResponseEntity<Map <String, Boolean>> deleteEmployee(@PathVariable Long id){
    	Employee employee = empRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id :" + id));
    	
    	empRepo.delete(employee);
    	Map<String,Boolean> response=new HashMap<>();
    	response.put("deleted",Boolean.TRUE);
    	
		return ResponseEntity.ok(response);
    	
    	
    	
    }
	

}
