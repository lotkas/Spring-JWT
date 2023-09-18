package com.example.SpringJWT.controllers;

import com.example.SpringJWT.models.Employee;
import com.example.SpringJWT.models.Ticket;
import com.example.SpringJWT.services.EmployeeService;
import com.example.SpringJWT.services.TicketService;
import com.example.SpringJWT.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee/")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final TicketService ticketService;

    private final UserService userService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, TicketService ticketService, UserService userService) {
        this.employeeService = employeeService;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    /**
     * Retrieves an Employee object based on the authenticated user's information.
     */
    @GetMapping("/get")
    public Employee getEmployee(Authentication authentication) {
        String username = authentication.getName();
        Long employeeId = userService.findEmployeeIdByUsername(username);

        return employeeService.getEmployeeById(employeeId);
    }

    /**
     * Updates an employee record by the currently logged-in user.
     */
    @PutMapping("/set")
    public Employee updateEmployeeByLoggedInUser(@RequestBody Employee updateEmployee, Authentication authentication) {
        String username = authentication.getName();
        Long employeeId = userService.findEmployeeIdByUsername(username);

        return employeeService.updateEmployee(employeeId, updateEmployee);
    }

    /**
     * Save a ticket using the provided ticket object and authenticated user.
     *
     * @param  ticket           the ticket object to be saved
     * @param  authentication   the authentication object representing the authenticated user
     * @return                  the saved ticket object
     */
    @PostMapping("/send")
    public Ticket saveTicket(@RequestBody Ticket ticket, Authentication authentication) {
        String username = authentication.getName();
        Long employeeId = userService.findEmployeeIdByUsername(username);

        return ticketService.saveTicket(ticket, employeeId);
    }
}
