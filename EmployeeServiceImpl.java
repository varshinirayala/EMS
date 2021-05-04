package com.mt.ems.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mt.ems.exceptions.EmployeeNotFoundException;
import com.mt.ems.exceptions.NullCheckException;
import com.mt.ems.model.Employee;
import com.mt.ems.repository.EmployeeRepository;
import com.mt.ems.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImplementation implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee addEmployee(Employee employee) throws NullCheckException, NullPointerException {
		if (employee.getEmployeeName().isEmpty() || employee.getEmployeeAge() == 0 || employee.getSalary() == 0
				|| employee.getDepartment().isEmpty() || employee == null) {
			log.error("NullCheckException occurred");
			throw new NullCheckException("The Fields cannot be empty");
		} else {
			Employee employeeSaved = employeeRepository.save(employee);
			log.info("Inserted successfully");
			return employeeSaved;
		}
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(int employeeId) throws EmployeeNotFoundException {
		log.debug("calling getEmployee with id: " + employeeId);
		Employee employeeById = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("No Employee found for this id: " + employeeId));
		log.info("Got employeeById:" + employeeById);
		return employeeById;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployeeById(int employeeId) {
		employeeRepository.deleteById(employeeId);
	}

	@Override
	public List<Employee> addMultipleEmployees(List<Employee> employees)
			throws NullCheckException, NullPointerException {
		List<Employee> savedEmployees = new ArrayList<>();
		if (employees.isEmpty()) {
			log.error("NullCheckException occurred");
			throw new NullCheckException("The list or fields cannot be empty");
		} else {
			for (Employee e : employees) {
				if (e.getEmployeeName().isEmpty() || e.getDepartment().isEmpty() || e.getEmployeeAge() == 0
						|| e.getSalary() == 0) {
					log.error("NullCheckException occurred");
					throw new NullCheckException("The fields cannot be empty");
				}
			}
			savedEmployees = employeeRepository.saveAll(employees);
			log.info("Inserted successfully");
		}
		return savedEmployees;
	}

}
