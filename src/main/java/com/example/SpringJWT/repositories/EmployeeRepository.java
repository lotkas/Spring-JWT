package com.example.SpringJWT.repositories;

import com.example.SpringJWT.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
