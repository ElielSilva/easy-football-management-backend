package br.edu.ifpe.easy_football_management_backend.application.commom.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
