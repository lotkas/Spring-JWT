package com.example.springjwt.services;

import com.example.springjwt.dto.ticketdto.TicketDTO;
import com.example.springjwt.dto.ticketdto.TicketSaveDTO;
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

    public Ticket saveTicket(TicketSaveDTO ticketDTO, Employee employee) {
        logger.info("Save ticket with this message: {}", ticketDTO.getMessage());
        Ticket ticket = new Ticket();

        ticket.setMessage(ticketDTO.getMessage());
        ticket.setEmployee(employee);
        ticket.setStatus(TicketStatus.WAIT);
        ticket.setCreatedAt(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    public List<TicketDTO> getTicketAll() {
        logger.info("Getting all tickets");
        return ticketRepository.findAll()
                .stream()
                .map(TicketDTO::new)
                .toList();
    }

    public void deleteTicketById(Long id) {
        logger.info("Delete ticket with id: {}", id);
        ticketRepository.deleteById(id);
        logger.info("Deleting ticket with id: {} done", id);
    }
}
