package com.vp.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vp.Repository.EmployeeRepository;
import com.vp.entity.Employee;
import com.vp.exception.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@PostMapping("/employees")
	public Employee createEmplyee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

		return ResponseEntity.ok(employee);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(
	        @PathVariable("id") int id,
	        @RequestBody Employee employeeDetails) {

	    Employee employee = employeeRepository.findById(id)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Employee not found with id : " + id
	                    ));

	    employee.setFirstName(employeeDetails.getFirstName());
	    employee.setLastName(employeeDetails.getLastName());
	    employee.setSalary(employeeDetails.getSalary());

	    Employee updatedEmployee = employeeRepository.save(employee);

	    return ResponseEntity.ok(updatedEmployee);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable("id") int id) {

	    Employee employee = employeeRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException( "Employee not found with id: " + id));

	    employeeRepository.delete(employee);

	    Map<String, Boolean> response = new HashMap<String,Boolean>();
	    response.put("deleted", Boolean.TRUE);

	    return ResponseEntity.ok(response);
	}
}
