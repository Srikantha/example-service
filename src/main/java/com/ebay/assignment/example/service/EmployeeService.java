package com.ebay.assignment.example.service;

import com.ebay.assignment.example.exception.ResourceNotFoundException;
import com.ebay.assignment.example.model.Employee;
import com.ebay.assignment.example.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeEventService employeeEventService;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return employee;
    }

    public Employee createEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);

        employeeEventService.announceEmployeeEvent(savedEmployee, "CREATED");
        LOGGER.debug("Employee, % has been created successfully.", employee.getId());

        return savedEmployee;
    }

    public Employee updateEmployee(Long employeeId, Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeRepository.save(employee);

        employeeEventService.announceEmployeeEvent(updatedEmployee,"UPDATED");
        LOGGER.debug("Employee, % has been updated successfully.", employee.getId());
        return updatedEmployee;
    }

    public Map<String, Boolean> deleteEmployee(Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        employeeEventService.announceEmployeeEvent(employee,"DELETED");
        LOGGER.debug("Employee, % has been deleted successfully.", employee.getId());
        return response;
    }
}
