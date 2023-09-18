package com.example.SpringJWT.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
@RestController
@RequestMapping("/api/v1/employee/")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final TicketService ticketService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, TicketService ticketService) {
        this.employeeService = employeeService;
        this.ticketService = ticketService;
    }

    @GetMapping("/get/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @PutMapping("/set/{id}")
    public Employee updateEmployeeById(@PathVariable Long id, @RequestBody Employee updateEmployee) {
        return employeeService.updateEmployee(id, updateEmployee);
    }

    @PostMapping("/send")
    public Ticket saveTicket(@RequestBody Ticket ticket) {
        return ticketService.saveTicket(ticket);
    }
}*/
