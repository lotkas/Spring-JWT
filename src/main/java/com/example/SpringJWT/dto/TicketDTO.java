package com.example.SpringJWT.dto;

import com.example.SpringJWT.enums.TicketStatus;
import com.example.SpringJWT.models.Ticket;

import java.time.LocalDateTime;

public class TicketDTO {

    private Long id;

    private String message;

    private Long employee;

    private LocalDateTime createdAt;

    private TicketStatus status;

    public TicketDTO (Ticket ticket) {
        this.id = ticket.getId();
        this.message = ticket.getMessage();
        this.employee = ticket.getEmployee().getId();
        this.createdAt = ticket.getCreatedAt();
        this.status = ticket.getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getEmployee() {
        return employee;
    }

    public void setEmployee(Long employee) {
        this.employee = employee;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TicketDTO{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                ", status=" + status +
                '}';
    }
}
