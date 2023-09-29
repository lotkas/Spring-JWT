package com.example.springjwt.dto.ticketdto;

public class TicketSaveDTO {

    private String message;

    public TicketSaveDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
