package com.easyfootballmanagement.features.users;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.IntegerValidator;

public class UpdateUserValidator {
    private static final EmailValidator emailValidator = EmailValidator.getInstance();
    private static final IntegerValidator intValidator = IntegerValidator.getInstance();

    public static void validate(UpdateUserCommand command) throws IllegalArgumentException {
        validateFullName(command.getFullName());
        validatePhone(command.getPhone());
    }

    private static void validateFullName(String fullName) {
        if (fullName == null || fullName.length() < 3 || fullName.length() > 50) {
            throw new IllegalArgumentException("Name must be between 3 and 50 characters long.");
        }
    }


    private static void validatePhone(String phone) {
        if (phone == null || phone.length() < 11 || phone.length() > 20) {
            throw new IllegalArgumentException("Phone number must be between 11 and 20 characters long.");
        }
    }
}
