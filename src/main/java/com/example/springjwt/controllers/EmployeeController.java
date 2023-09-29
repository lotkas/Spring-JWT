package com.example.springjwt.controllers;

import com.example.springjwt.configurations.ApiException;
import com.example.springjwt.dto.EmployeeDTO;
import com.example.springjwt.dto.TicketDTO;
import com.example.springjwt.models.Employee;
import com.example.springjwt.models.Ticket;
import com.example.springjwt.services.EmployeeService;
import com.example.springjwt.services.TicketService;
import com.example.springjwt.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private static final String GET_EMPLOYEE = "/get";

    private static final String UPDATE_EMPLOYEE = "/set";

    private static final String POST_NEW_TICKET = "/send";

    private final EmployeeService employeeService;

    private final TicketService ticketService;

    private final UserService userService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, TicketService ticketService, UserService userService) {
        this.employeeService = employeeService;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @GetMapping(GET_EMPLOYEE)
    public ResponseEntity<EmployeeDTO> getEmployee(Authentication authentication) {
        logger.info("/get start");
        try {
            String username = authentication.getName();
            Long employeeId = userService.findEmployeeIdByUsername(username);

            EmployeeDTO employeeDTO = new EmployeeDTO(employeeService.getById(employeeId));

            return ResponseEntity.ok(employeeDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(UPDATE_EMPLOYEE)
    public ResponseEntity<EmployeeDTO> updateEmployeeByLoggedInUser(@RequestBody Employee updateEmployee, Authentication authentication) {
        logger.info("/set start");
        try {
            String username = authentication.getName();
            Long employeeId = userService.findEmployeeIdByUsername(username);
            Employee employeeUpdated = employeeService.updateEmployee(employeeId, updateEmployee);

            EmployeeDTO employeeDTO = new EmployeeDTO(employeeUpdated);

            return ResponseEntity.ok(employeeDTO);
        } catch (Exception e) {
            logger.error("/set error");
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(POST_NEW_TICKET)
    @Transactional
    public ResponseEntity<TicketDTO> saveTicket(@RequestBody Ticket ticketRequest, Authentication authentication) {
        logger.info("/send start");
        try {
            String username = authentication.getName();
            Long employeeId = userService.findEmployeeIdByUsername(username);
            Employee employee = employeeService.getById(employeeId);
            Ticket ticket = ticketService.saveTicket(ticketRequest, employee);

            TicketDTO ticketDTO = new TicketDTO(ticket);

            logger.info("/send end");
            return ResponseEntity.ok(ticketDTO);
        } catch (Exception e) {
            logger.error("/send error");
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }
    }
}
