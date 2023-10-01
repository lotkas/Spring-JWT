package com.example.springjwt.services;

import com.example.springjwt.configurations.ApiException;
import com.example.springjwt.dto.employeedto.EmployeeDTO;
import com.example.springjwt.dto.employeedto.EmployeeUpdateByAdminDTO;
import com.example.springjwt.dto.employeedto.EmployeeUpdateByUserDTO;
import com.example.springjwt.models.Employee;
import com.example.springjwt.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeUpdateByUserDTO employeeUpdate) {
        logger.info("Update employee with id: {}, updating form: {}", id, employeeUpdate);
        Employee employee = employeeRepository.getById(id);

        employee.setFirstName(employeeUpdate.getFirstName());
        employee.setLastName(employeeUpdate.getLastName());
        employee.setEmail(employeeUpdate.getEmail());
        employee.setAge(employeeUpdate.getAge());
        employeeRepository.save(employee);

        return new EmployeeDTO(employee.getId(), employee.getFirstName(),
                employee.getLastName(), employee.getEmail(),
                employee.getPositionId().getId(),
                employee.getAge(), employee.getSalary(), employee.isInJob());
    }

    public void updateEmployeeByAdmin(Long id, EmployeeUpdateByAdminDTO updatedEmployee) {
        logger.info("Update employee by admin with id: {}, updating form: {}", id, updatedEmployee);
        Employee employee = employeeRepository.getById(id);

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setAge(updatedEmployee.getAge());
        employee.setSalary(updatedEmployee.getSalary());
        employee.setPositionId(updatedEmployee.getPositionId());
        employee.setInJob(updatedEmployee.isInJob());

        employeeRepository.save(employee);
        logger.info("Employee with id: {} updated", id);
    }

    public List<EmployeeDTO> getAll() {
        logger.info("Getting all employees");
        return employeeRepository.findAll()
                .stream()
                .map(employee -> new EmployeeDTO(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getEmail(),
                        employee.getPositionId().getId(),
                        employee.getAge(),
                        employee.getSalary(),
                        employee.isInJob()
                ))
                .toList();
    }

    public Employee getById(Long id) {
        logger.info("Getting employee by id: {}", id);
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST));
    }
}
