package com.example.SpringJWT.services;

import com.example.SpringJWT.enums.TicketStatus;
import com.example.SpringJWT.models.Employee;
import com.example.SpringJWT.models.Ticket;
import com.example.SpringJWT.repositories.EmployeeRepository;
import com.example.SpringJWT.repositories.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);

    private final TicketRepository ticketRepository;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, EmployeeRepository employeeRepository) {
        this.ticketRepository = ticketRepository;
        this.employeeRepository = employeeRepository;
    }

    public Ticket saveTicket(Ticket ticket, Long employeeId) {
        logger.info("saveTicket() start");
        Employee employee = employeeRepository.getById(employeeId);

        ticket.setEmployeeId(employee);
        ticket.setStatus(TicketStatus.WAIT);
        ticket.setCreatedAt(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    public List<Ticket> getTicketAll() {
        logger.info("getTicketAll() start");
        return ticketRepository.findAll();
    }

    public void deleteTicketById(Long id) {
        logger.info("deleteTicketById() start");
        ticketRepository.deleteById(id);
        logger.info("deleteTicketById() end");
    }
}
