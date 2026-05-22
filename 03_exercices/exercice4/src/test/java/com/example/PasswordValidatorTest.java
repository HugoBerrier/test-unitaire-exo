package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordValidatorTest {

    private final PasswordValidator validator = new PasswordValidator();

    @Test
    void isValid_validPassword_shouldReturnTrue() {
        assertTrue(validator.isValid("Password1!"));
        assertEquals("Password is valid", validator.getErrorMessage("Password1!"));
    }

    @Test
    void isValid_nullPassword_shouldReturnFalse() {
        assertFalse(validator.isValid(null));
        assertEquals("Password must not be null", validator.getErrorMessage(null));
    }

    @Test
    void isValid_passwordWithoutDigit_shouldReturnFalse() {
        assertFalse(validator.isValid("Password!"));
        assertEquals("Password must contain at least one digit", validator.getErrorMessage("Password!"));
    }

    @ParameterizedTest
    @CsvSource({
            "Password1!, true, Password is valid",
            "Admin2024@, true, Password is valid",
            "short1!, false, Password must contain at least 8 characters",
            "PASSWORD1!, false, Password must contain at least one lowercase letter",
            "password1!, false, Password must contain at least one uppercase letter",
            "Password!, false, Password must contain at least one digit",
            "Password1, false, Password must contain at least one special character"
    })
    void passwordValidation_fromCsvSource(String password, boolean expectedValid, String expectedMessage) {
        assertEquals(expectedValid, validator.isValid(password));
        assertEquals(expectedMessage, validator.getErrorMessage(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Password1!", "Admin2024@"})
    void validPasswords_fromValueSource_shouldBeValid(String password) {
        assertTrue(validator.isValid(password));
        assertEquals("Password is valid", validator.getErrorMessage(password));
    }

    @ParameterizedTest
    @MethodSource("invalidPasswordsWithMessages")
    void invalidPasswords_fromMethodSource_shouldReturnExpectedMessage(String password, String expectedMessage) {
        assertFalse(validator.isValid(password));
        assertEquals(expectedMessage, validator.getErrorMessage(password));
    }

    static Stream<Arguments> invalidPasswordsWithMessages() {
        return Stream.of(
                Arguments.of("short1!", "Password must contain at least 8 characters"),
                Arguments.of("PASSWORD1!", "Password must contain at least one lowercase letter"),
                Arguments.of("password1!", "Password must contain at least one uppercase letter"),
                Arguments.of("Password!", "Password must contain at least one digit"),
                Arguments.of("Password1", "Password must contain at least one special character")
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void nullOrEmpty_fromNullAndEmptySource_shouldBeInvalid(String password) {
        assertFalse(validator.isValid(password));

        if (password == null) {
            assertEquals("Password must not be null", validator.getErrorMessage(password));
        } else {
            assertEquals("Password must contain at least 8 characters", validator.getErrorMessage(password));
        }
    }
}
