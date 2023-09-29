package com.example.springjwt.controllers;

import com.example.springjwt.configurations.ApiException;
import com.example.springjwt.dto.employeedto.EmployeeDTO;
import com.example.springjwt.dto.employeedto.EmployeeUpdateByAdminDTO;
import com.example.springjwt.dto.ticketdto.TicketDTO;
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
        logger.info("AdminController. getAll() start");
        List<EmployeeDTO> employees = new ArrayList<>();
        for (Employee employee : employeeService.getAll()) {
            EmployeeDTO employeeDTO = new EmployeeDTO(employee);
            employees.add(employeeDTO);
        }
        return ResponseEntity.ok(employees);
    }

    @GetMapping(GET_EMPLOYEE_BY_ID)
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        logger.info("AdminController. getEmployeeById({}) start", id);
        EmployeeDTO employeeDTO = new EmployeeDTO(employeeService.getById((id)));
        return ResponseEntity.ok(employeeDTO);
    }

    @PutMapping(UPDATE_EMPLOYEE)
    @Transactional
    public void updateEmployee(@PathVariable Long id, @RequestBody EmployeeUpdateByAdminDTO updateEmployee) {
        logger.info("AdminController. updateEmployee({}, {}) start", id, updateEmployee);
        try {
            employeeService.updateEmployeeByAdmin(id, updateEmployee);
            ResponseEntity.status(HttpStatus.OK);
        } catch (Exception e) {
            logger.debug("AdminController. updateEmployee({}, {}) error {}", id, updateEmployee, e.getMessage());
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(SET_USER_EMPLOYEE_ID)
    public void setUserEmployeeId(@PathVariable Long id, Long employeeId) {
        logger.info("AdminController. setUserEmployeeId(userId: {}, employeeId: {}) start", id, employeeId);
        try {
            userService.updateUserEmployeeId(id, employeeId);
            ResponseEntity.status(HttpStatus.OK);
        } catch (Exception e) {
            logger.debug("AdminController. setUserEmployeeId(userId: {}, employeeId: {}) error {}", id, employeeId, e.getMessage());
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(GET_ALL_TICKETS)
    public ResponseEntity<List<TicketDTO>> getTicketAll() {
        logger.info("AdminController. getTicketAll() start");
        List<TicketDTO> tickets = new ArrayList<>();
        for (Ticket ticket : ticketService.getTicketAll()) {
            TicketDTO ticketDTO = new TicketDTO(ticket);
            tickets.add(ticketDTO);
        }
        return ResponseEntity.ok(tickets);
    }

    @DeleteMapping(DELETE_TICKET_BY_ID)
    public void deleteTicketById(@PathVariable Long id) {
        logger.info("AdminController. deleteTicketById({}) start", id);
        try {
            ticketService.deleteTicketById(id);
            ResponseEntity.status(HttpStatus.OK);
        } catch (Exception e) {
            logger.debug("AdminController. deleteTicketById({}) error {}", id, e.getMessage());
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }
    }
}
