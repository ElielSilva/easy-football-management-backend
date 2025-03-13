package com.easyfootballmanagement.features.users;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;
import org.apache.commons.validator.routines.IntegerValidator;

public class CreateUserValidator {

    private static final EmailValidator emailValidator = EmailValidator.getInstance();
    private static final IntegerValidator intValidator = IntegerValidator.getInstance();

    public static void validate(CreateUserCommand command) throws IllegalArgumentException {
        validateFullName(command.getFullName());
        validateEmail(command.getEmail());
        validatePhone(command.getPhone());
    }

    private static void validateFullName(String fullName) {
        if (fullName == null || fullName.length() < 3 || fullName.length() > 50) {
            throw new IllegalArgumentException("Name must be between 3 and 50 characters long.");
        }
    }

    private static void validateEmail(String email) {
        if (email == null || !emailValidator.isValid(email)) {
            throw new IllegalArgumentException("Email should be valid.");
        }
    }

    private static void validatePhone(String phone) {
        if (phone == null || phone.length() < 11 || phone.length() > 20) {
            throw new IllegalArgumentException("Phone number must be between 11 and 20 characters long.");
        }
    }
}
