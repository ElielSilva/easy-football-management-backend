package br.edu.ifpe.easy_football_management_backend.application.commom.exceptions;

public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}
