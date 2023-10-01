package com.example.springjwt.controllers;

import com.example.springjwt.configurations.ApiException;
import com.example.springjwt.dto.employeedto.EmployeeDTO;
import com.example.springjwt.dto.employeedto.EmployeeUpdateByUserDTO;
import com.example.springjwt.dto.ticketdto.TicketDTO;
import com.example.springjwt.dto.ticketdto.TicketSaveDTO;
import com.example.springjwt.models.Employee;
import com.example.springjwt.models.Ticket;
import com.example.springjwt.services.EmployeeService;
import com.example.springjwt.services.TicketService;
import com.example.springjwt.services.UserService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public EmployeeController(EmployeeService employeeService,
                              TicketService ticketService,
                              UserService userService) {
        this.employeeService = employeeService;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @GetMapping(GET_EMPLOYEE)
    public ResponseEntity<EmployeeDTO> getEmployee(@NotNull Authentication authentication) {
        logger.info("EmployeeController. getEmployee() start");
        try {
            String username = authentication.getName();
            Long employeeId = userService.findEmployeeIdByUsername(username);
            Employee employee = employeeService.getById(employeeId);

            EmployeeDTO employeeDTO = new EmployeeDTO(
                    employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getEmail(),
                    employee.getPositionId().getId(),
                    employee.getAge(),
                    employee.getSalary(),
                    employee.isInJob()
            );
            return ResponseEntity.ok(employeeDTO);
        } catch (Exception e) {
            logger.error("EmployeeController. getEmployee() error {}", e.getMessage());
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(UPDATE_EMPLOYEE)
    public ResponseEntity<EmployeeDTO> updateEmployeeByLoggedInUser(@NotNull @RequestBody EmployeeUpdateByUserDTO updateEmployee,
                                                                    @NotNull Authentication authentication) {
        logger.info("EmployeeController. updateEmployeeByLoggedInUser() start");
        try {
            String username = authentication.getName();
            Long employeeId = userService.findEmployeeIdByUsername(username);
            EmployeeDTO employeeUpdated = employeeService.updateEmployee(employeeId, updateEmployee);

            return ResponseEntity.ok(employeeUpdated);
        } catch (Exception e) {
            logger.error("EmployeeController. updateEmployeeByLoggedInUser() error {}", e.getMessage());
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(POST_NEW_TICKET)
    @Transactional
    public ResponseEntity<TicketDTO> saveTicket(@RequestBody TicketSaveDTO ticketRequest, Authentication authentication) {
        logger.info("EmployeeController. saveTicket() start");
        try {
            String username = authentication.getName();
            Long employeeId = userService.findEmployeeIdByUsername(username);
            Employee employee = employeeService.getById(employeeId);
            Ticket ticket = ticketService.saveTicket(ticketRequest, employee);

            TicketDTO ticketDTO = new TicketDTO(ticket);

            return ResponseEntity.ok(ticketDTO);
        } catch (Exception e) {
            logger.error("EmployeeController. saveTicket() error {}", e.getMessage());
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }
    }
}