package com.example.springjwt.dto.ticketdto;

import org.jetbrains.annotations.NotNull;

public class TicketSaveDTO {

    private String message;

    public TicketSaveDTO(@NotNull String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
