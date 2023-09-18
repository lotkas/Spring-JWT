package com.example.SpringJWT.models;

import com.example.SpringJWT.enums.TicketStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employeeId;

    @Column
    private String message;

    @Column
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column
    private TicketStatus status;

    public Ticket(Employee employeeId, String message, LocalDateTime createdAt, TicketStatus status) {
        this.employeeId = employeeId;
        this.message = message;
        this.createdAt = createdAt;
        this.status = status;
    }

    public Ticket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        return "Ticket{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                ", status=" + status +
                '}';
    }
}
