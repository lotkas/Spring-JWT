package com.example.SpringJWT.controllers;

import com.example.SpringJWT.models.Employee;
import com.example.SpringJWT.models.Ticket;
import com.example.SpringJWT.models.User;
import com.example.SpringJWT.services.EmployeeService;
import com.example.SpringJWT.services.TicketService;
import com.example.SpringJWT.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private final EmployeeService employeeService;

    private final UserService userService;

    private final TicketService ticketService;

    @Autowired
    public AdminController(EmployeeService employeeService, UserService userService, TicketService ticketService) {
        this.employeeService = employeeService;
        this.userService = userService;
        this.ticketService = ticketService;
    }

    @GetMapping("/getAll")
    public List<Employee> getAllEmployees() {
        logger.info("/getAll start");
        return employeeService.getAll();
    }

    @GetMapping("/get/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        logger.info("/get/{} start", id);
        return employeeService.getById(id);
    }

    @PutMapping("/set/employee/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updateEmployee) {
        logger.info("/set/{} start", id);
        return employeeService.updateEmployee(id, updateEmployee);
    }

    @PutMapping("/set/user/employeeId/{id}")
    public User setUserEmployeeId(@PathVariable Long id, Long employeeId) {
        logger.info("/set/user/employeeId/{} start", id);
        return userService.updateUserEmployeeId(id, employeeId);
    }

    @GetMapping("/ticket/get")
    public List<Ticket> getTicketAll() {
        logger.info("/ticket/get start");
        return ticketService.getTicketAll();
    }

    @DeleteMapping("/ticket/delete/{id}")
    public void deleteTicketById(@PathVariable Long id) {
        logger.info("/ticket/delete/{} start", id);
        ticketService.deleteTicketById(id);
    }
}
