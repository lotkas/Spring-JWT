package com.example.springjwt.services;

import com.example.springjwt.dto.employeedto.EmployeeUpdateByAdminDTO;
import com.example.springjwt.dto.employeedto.EmployeeUpdateByUserDTO;
import com.example.springjwt.models.Employee;
import com.example.springjwt.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private static final  Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee updateEmployee(Long id, EmployeeUpdateByUserDTO employeeUpdate) {
        logger.info("Update employee with id: {}, updating form: {}", id, employeeUpdate);
        Employee employee = employeeRepository.getById(id);

        employee.setFirstName(employeeUpdate.getFirstName());
        employee.setLastName(employeeUpdate.getLastName());
        employee.setEmail(employeeUpdate.getEmail());
        employee.setAge(employeeUpdate.getAge());

        return employeeRepository.save(employee);
    }

    public void updateEmployeeByAdmin(Long id, EmployeeUpdateByAdminDTO employeeUpdate) {
        logger.info("Update employee by admin with id: {}, updating form: {}", id, employeeUpdate);
        Employee employee = employeeRepository.getById(id);

        employee.setFirstName(employeeUpdate.getFirstName());
        employee.setLastName(employeeUpdate.getLastName());
        employee.setEmail(employeeUpdate.getEmail());
        employee.setAge(employeeUpdate.getAge());
        employee.setSalary(employeeUpdate.getSalary());
        employee.setPositionId(employeeUpdate.getPositionId());
        employee.setInJob(employeeUpdate.isInJob());

        employeeRepository.save(employee);
        logger.info("Employee with id: {} updated", id);
    }

    public List<Employee> getAll() {
        logger.info("Getting all employees");
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        logger.info("Getting employee by id: {}", id);
        return employeeRepository.findById(id)
                .orElse(null);
    }
}
