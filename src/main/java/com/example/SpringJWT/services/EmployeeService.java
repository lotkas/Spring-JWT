package com.example.SpringJWT.services;

import com.example.SpringJWT.models.Employee;
import com.example.SpringJWT.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final static Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee updateEmployee(Long id, Employee employee) {
        logger.info("updateEmployee() start");
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        logger.info("getEmployeeById() start");
        return employeeRepository.findById(id)
                .orElse(null);
    }

    public List<Employee> getAll() {
        logger.info("getAll() start");
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        logger.info("getById() start");
        return employeeRepository.getById(id);
    }
}
