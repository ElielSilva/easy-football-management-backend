package com.easyfootballmanagement.application.common.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDate timestamp;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDate.now();
    }
}
