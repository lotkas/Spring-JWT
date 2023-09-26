package com.example.SpringJWT.controllers;

import com.example.SpringJWT.enums.UserRole;
import com.example.SpringJWT.models.*;
import com.example.SpringJWT.services.EmployeeService;
import com.example.SpringJWT.services.TicketService;
import com.example.SpringJWT.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
class AdminControllerTest {

    @Mock
    EmployeeService employeeService;

    @Mock
    UserService userService;

    @Mock
    TicketService ticketService;

    @InjectMocks
    AdminController adminController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> employees = Arrays.asList(new Employee(1L,
                        "TestName",
                        "TestName",
                        "TestEmail",
                        new Position(1L, "Manager"),
                        35,
                        BigDecimal.valueOf(65000),
                        true),
                new Employee(2L,
                        "TestName",
                        "TestName",
                        "TestEmail",
                        new Position(2L, "Manager"),
                        27,
                        BigDecimal.valueOf(45000),
                        true),
                new Employee(3L,
                        "TestName",
                        "TestName",
                        "TestEmail",
                        new Position(3L, "Manager"),
                        25,
                        BigDecimal.valueOf(25000),
                        true));
        when(employeeService.getAll()).thenReturn(employees);

        List<Employee> result = adminController.getAllEmployees();

        assertEquals(employees, result);
        verify(employeeService).getAll();
    }

    @Test
    void testGetEmployeeById() {
        Employee employee = new Employee(3L,
                "TestName",
                "TestName",
                "TestEmail",
                new Position(3L,"Manager"),
                25,
                BigDecimal.valueOf(25000),
                true);
        when(employeeService.getById(employee.getId())).thenReturn(employee);

        Employee result = adminController.getEmployeeById(3L);

        assertEquals(employee, result);
        verify(employeeService).getById(3L);
    }

    @Test
    void testUpdateEmployee() {
        Long id = 2L;
        Employee updateEmployee = new Employee(2L,
                "TestName",
                "TestName",
                "TestEmail",
                new Position(3L,"Manager"),
                25,
                BigDecimal.valueOf(25000),
                true);
        Employee updatedEmployee = new Employee(2L,
                "TestNameUpdate",
                "TestNameUpdate",
                "TestEmailUpdate",
                new Position(3L,"ManagerUpdate"),
                45,
                BigDecimal.valueOf(22000),
                true);
        when(employeeService.updateEmployee(id, updateEmployee)).thenReturn(updatedEmployee);

        Employee result = adminController.updateEmployee(id, updateEmployee);

        assertEquals(updatedEmployee, result);
        verify(employeeService).updateEmployee(id, updateEmployee);
    }

    @Test
    void testSetUserEmployeeId() {
        Long id = 1L;
        Long employeeId = 2L;
        User user = new User("TestUsername",
                "TestPassword",
                25L,
                new Role(UserRole.ADMIN));
        when(userService.updateUserEmployeeId(id, employeeId)).thenReturn(user);

        User result = adminController.setUserEmployeeId(id, employeeId);

        assertEquals(user, result);
        verify(userService).updateUserEmployeeId(id, employeeId);
    }

    @Test
    void testGetTicketAll() {
        List<Ticket> tickets = Arrays.asList(new Ticket(), new Ticket());
        when(ticketService.getTicketAll()).thenReturn(tickets);

        List<Ticket> result = adminController.getTicketAll();

        assertEquals(tickets, result);
        verify(ticketService).getTicketAll();
    }

    @Test
    void testDeleteTicketById() {
        Long id = 1L;

        adminController.deleteTicketById(id);

        verify(ticketService).deleteTicketById(id);
    }
}
*/
