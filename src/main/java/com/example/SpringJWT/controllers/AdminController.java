package com.example.SpringJWT.controllers;

import com.example.SpringJWT.configurations.ApiException;
import com.example.SpringJWT.dto.EmployeeDTO;
import com.example.SpringJWT.dto.TicketDTO;
import com.example.SpringJWT.models.Employee;
import com.example.SpringJWT.models.Ticket;
import com.example.SpringJWT.services.EmployeeService;
import com.example.SpringJWT.services.TicketService;
import com.example.SpringJWT.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private static final String GET_ALL_EMPLOYEES = "/getAll";

    private static final String GET_EMPLOYEE_BY_ID = "/get/{id}";

    private static final String UPDATE_EMPLOYEE = "/set/employee/{id}";

    private static final String SET_USER_EMPLOYEE_ID = "/set/user/employeeId/{id}";

    private static final String GET_ALL_TICKETS = "/ticket/get";

    private static final String DELETE_TICKET_BY_ID = "/ticket/delete/{id}";

    private final EmployeeService employeeService;

    private final UserService userService;

    private final TicketService ticketService;

    @Autowired
    public AdminController(EmployeeService employeeService, UserService userService, TicketService ticketService) {
        this.employeeService = employeeService;
        this.userService = userService;
        this.ticketService = ticketService;
    }

    @GetMapping(GET_ALL_EMPLOYEES)
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        logger.info("/getAll start");
        List<EmployeeDTO> employees = new ArrayList<>();
        for (Employee employee : employeeService.getAll()) {
            EmployeeDTO employeeDTO = new EmployeeDTO(employee);
            employees.add(employeeDTO);
        }
        return ResponseEntity.ok(employees);
    }

    @GetMapping(GET_EMPLOYEE_BY_ID)
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        logger.info("/get/{} start", id);
        EmployeeDTO employeeDTO = new EmployeeDTO(employeeService.getById((id)));
        return ResponseEntity.ok(employeeDTO);
    }

    @PutMapping(UPDATE_EMPLOYEE)
    @Transactional
    public void updateEmployee(@PathVariable Long id, @RequestBody Employee updateEmployee) {
        logger.info("/set/{} start", id);
        try {
            employeeService.updateEmployee(id, updateEmployee);
            ResponseEntity.status(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(SET_USER_EMPLOYEE_ID)
    public void setUserEmployeeId(@PathVariable Long id, Long employeeId) {
        logger.info("/set/user/employeeId/{} start", id);
        try {
            userService.updateUserEmployeeId(id, employeeId);
            ResponseEntity.status(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(GET_ALL_TICKETS)
    public ResponseEntity<List<TicketDTO>> getTicketAll() {
        logger.info("/ticket/get start");
        List<TicketDTO> tickets = new ArrayList<>();
        for (Ticket ticket : ticketService.getTicketAll()) {
            TicketDTO ticketDTO = new TicketDTO(ticket);
            tickets.add(ticketDTO);
        }
        return ResponseEntity.ok(tickets);
    }

    @DeleteMapping(DELETE_TICKET_BY_ID)
    public void deleteTicketById(@PathVariable Long id) {
        logger.info("/ticket/delete/{} start", id);
        try {
            ticketService.deleteTicketById(id);
            ResponseEntity.status(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }
    }
}
