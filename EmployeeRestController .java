package com.mt.employeesystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mt.employeesystem.message.Response;
import com.mt.employeesystem.model.Employee;
import com.mt.employeesystem.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/employee")
public class EmployeeRestController {
	@Autowired
	private EmployeeService employeeService;

	@PostMapping(value = "/save")
	public Response addEmployee(@RequestBody Employee employee) {
		log.info("Inside addEmployee class");
		employeeService.addEmployee(employee);
		Response response = new Response("Done", employee);
		return response;
	}

	@GetMapping(value = "/all")
	public Response getAllEmployees() {
		log.info("Inside getAllEmployees class");
		List<Employee> employees = employeeService.getAllEmployees();
		Response response = new Response("Done", employees);
		return response;
	}

	@PutMapping(value = "/update/{id}")
	public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
		employee.setId(id);
		Employee savedEmployee = employeeService.updateEmployee(employee);
		return savedEmployee;
	}

	@DeleteMapping(value = "/delete/{id}")
	public String deleteEmployee(@PathVariable int id) {
		// Employee employee = employeeService.getEmployeeById(id);
		employeeService.deleteEmployee(id);
		return "OK!";
	}

	@GetMapping("/download/employees.xlsx")
	public ResponseEntity<Resource> getExcelFile() {
		String filename = "employees.xlsx";
		InputStreamResource file = new InputStreamResource(employeeService.loadExcelFile());
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}
}