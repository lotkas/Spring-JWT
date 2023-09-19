package com.example.SpringJWT.controllers;

import com.example.SpringJWT.models.Employee;
import com.example.SpringJWT.models.Ticket;
import com.example.SpringJWT.services.EmployeeService;
import com.example.SpringJWT.services.TicketService;
import com.example.SpringJWT.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee/")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    private final TicketService ticketService;

    private final UserService userService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, TicketService ticketService, UserService userService) {
        this.employeeService = employeeService;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @GetMapping("/get")
    public Employee getEmployee(Authentication authentication) {
        logger.info("/get start");
        String username = authentication.getName();
        Long employeeId = userService.findEmployeeIdByUsername(username);

        return employeeService.getEmployeeById(employeeId);
    }

    @PutMapping("/set")
    public Employee updateEmployeeByLoggedInUser(@RequestBody Employee updateEmployee, Authentication authentication) {
        logger.info("/set start");
        String username = authentication.getName();
        Long employeeId = userService.findEmployeeIdByUsername(username);

        return employeeService.updateEmployee(employeeId, updateEmployee);
    }

    @PostMapping("/send")
    public Ticket saveTicket(@RequestBody Ticket ticket, Authentication authentication) {
        logger.info("/send start");
        String username = authentication.getName();
        Long employeeId = userService.findEmployeeIdByUsername(username);

        return ticketService.saveTicket(ticket, employeeId);
    }
}
