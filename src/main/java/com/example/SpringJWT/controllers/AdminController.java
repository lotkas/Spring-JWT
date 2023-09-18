package com.example.SpringJWT.controllers;

import com.example.SpringJWT.models.Employee;
import com.example.SpringJWT.models.Ticket;
import com.example.SpringJWT.services.EmployeeService;
import com.example.SpringJWT.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminController {

    private final EmployeeService employeeService;

    private final TicketService ticketService;

    @Autowired
    public AdminController(EmployeeService employeeService, TicketService ticketService) {
        this.employeeService = employeeService;
        this.ticketService = ticketService;
    }

    @GetMapping("/getAll")
    public List<Employee> getAllEmployees() {

        return employeeService.getAll();
    }

    @GetMapping("/get/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {

        return employeeService.getById(id);
    }

    @PutMapping("/set/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updateEmployee) {

        return employeeService.updateEmployee(id, updateEmployee);
    }

    @GetMapping("/ticket/get")
    public List<Ticket> getTicketAll() {
        return ticketService.getTicketAll();
    }

    @DeleteMapping("/ticket/delete/{id}")
    public void deleteTicketById(@PathVariable Long id) {

        ticketService.deleteTicketById(id);
    }
}
