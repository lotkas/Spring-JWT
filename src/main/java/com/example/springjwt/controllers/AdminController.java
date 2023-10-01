package com.example.springjwt.controllers;

import com.example.springjwt.configurations.ApiException;
import com.example.springjwt.dto.employeedto.EmployeeDTO;
import com.example.springjwt.dto.employeedto.EmployeeUpdateByAdminDTO;
import com.example.springjwt.dto.ticketdto.TicketDTO;

import com.example.springjwt.models.Employee;
import com.example.springjwt.services.EmployeeService;
import com.example.springjwt.services.TicketService;
import com.example.springjwt.services.UserService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private static final String GET_ALL_EMPLOYEES = "/get/all";

    private static final String GET_EMPLOYEE_BY_ID = "/get";

    private static final String UPDATE_EMPLOYEE = "/set/employee";

    private static final String SET_USER_EMPLOYEE_ID = "/set/user/employee-id/";

    private static final String GET_ALL_TICKETS = "/ticket/get";

    private static final String UPDATE_TICKET_STATUS_BY_ID = "/ticket/delete/";

    private final EmployeeService employeeService;

    private final UserService userService;

    private final TicketService ticketService;

    public AdminController(EmployeeService employeeService,
                           UserService userService,
                           TicketService ticketService) {
        this.employeeService = employeeService;
        this.userService = userService;
        this.ticketService = ticketService;
    }

    @GetMapping(GET_ALL_EMPLOYEES)
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        logger.info("AdminController. getAll() start");
        final List<EmployeeDTO> employees = employeeService.getAll();

        return ResponseEntity.ok(employees);
    }

    @PostMapping(GET_EMPLOYEE_BY_ID)
    public ResponseEntity<EmployeeDTO> getEmployeeById(@RequestParam Long id) {
        logger.info("AdminController. getEmployeeById({}) start", id);
        Employee employee = employeeService.getById(id);
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
    }

    @Transactional
    @PutMapping(UPDATE_EMPLOYEE)
    public void updateEmployee(@NotNull @RequestBody EmployeeUpdateByAdminDTO updateEmployee) {
        logger.info("AdminController. updateEmployee({}, {}) start", updateEmployee.getId(), updateEmployee);
        try {
            employeeService.updateEmployeeByAdmin(updateEmployee.getId(), updateEmployee);
            ResponseEntity.status(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("AdminController. updateEmployee({}, {}) error {}", updateEmployee.getId(), updateEmployee, e.getMessage());
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(SET_USER_EMPLOYEE_ID)
    public void setUserEmployeeId(@NotNull @RequestParam Long id,
                                  @NotNull @RequestParam Long employeeId) {
        logger.info("AdminController. setUserEmployeeId(userId: {}, employeeId: {}) start", id, employeeId);
        try {
            userService.updateUserEmployeeId(id, employeeId);
            ResponseEntity.status(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("AdminController. setUserEmployeeId(userId: {}, employeeId: {}) error {}", id, employeeId, e.getMessage());
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(GET_ALL_TICKETS)
    public ResponseEntity<List<TicketDTO>> getTicketAll() {
        logger.info("AdminController. getTicketAll() start");
        final List<TicketDTO> tickets = ticketService.getTicketAll();

        return ResponseEntity.ok(tickets);
    }

    @DeleteMapping(UPDATE_TICKET_STATUS_BY_ID)
    public void deleteTicketById(@NotNull @RequestParam Long id) {
        logger.info("AdminController. deleteTicketById({}) start", id);
        try {
            ticketService.deleteTicketById(id);
            ResponseEntity.status(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("AdminController. deleteTicketById({}) error {}", id, e.getMessage());
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }
    }
}
