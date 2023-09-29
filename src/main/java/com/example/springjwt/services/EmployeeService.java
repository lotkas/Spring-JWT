package com.example.springjwt.services;

import com.example.springjwt.models.Employee;
import com.example.springjwt.repositories.EmployeeRepository;
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

    public Employee updateEmployee(Long id, Employee employeeUpdate) {
        logger.info("updateEmployee() start");
        Employee employee = employeeRepository.getById(id);

        employee.setFirstName(employeeUpdate.getFirstName());
        employee.setLastName(employeeUpdate.getLastName());
        employee.setEmail(employeeUpdate.getEmail());
        employee.setPositionId(employeeUpdate.getPositionId());
        employee.setAge(employeeUpdate.getAge());
        employee.setSalary(employeeUpdate.getSalary());
        employee.setInJob(employeeUpdate.isInJob());

        return employeeRepository.save(employee);
    }

    public List<Employee> getAll() {
        logger.info("getAll() start");
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        logger.info("getById() start");
        return employeeRepository.findById(id)
                .orElse(null);
    }
}
