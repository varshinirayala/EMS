package com.mt.employeesystem.service;

import java.io.InputStream;
import java.util.List;

import com.mt.employeesystem.model.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployees();

	Employee addEmployee(Employee employee);

	Employee getEmployeeById(int id);

	void deleteEmployee(int employeeId);

	Employee updateEmployee(Employee employee);

	InputStream loadExcelFile();

}
