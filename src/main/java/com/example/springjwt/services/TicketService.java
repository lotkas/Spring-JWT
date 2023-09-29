package com.example.springjwt.services;

import com.example.springjwt.enums.TicketStatus;
import com.example.springjwt.models.Employee;
import com.example.springjwt.models.Ticket;
import com.example.springjwt.repositories.TicketRepository;
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

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket saveTicket(Ticket ticket, Employee employee) {
        logger.info("saveTicket() start");

        ticket.setEmployee(employee);
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
