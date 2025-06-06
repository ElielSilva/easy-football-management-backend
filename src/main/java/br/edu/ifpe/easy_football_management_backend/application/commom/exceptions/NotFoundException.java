package br.edu.ifpe.easy_football_management_backend.application.commom.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
