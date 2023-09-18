package com.example.SpringJWT.repositories;

import com.example.SpringJWT.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
