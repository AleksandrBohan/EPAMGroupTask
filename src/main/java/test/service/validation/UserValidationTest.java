package test.service.validation;

import com.epam.jwd.service.validation.UserValidation;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserValidationTest {

    @Test
    void shouldValidateNameWhenTheNameIsValid() {
        String name = "Vasily";
        assertTrue(UserValidation.isValidName(name));
    }

    @Test
    void shouldNotValidateNameWhenTheNameIsShort() {
        String name = "";
        assertFalse(UserValidation.isValidName(name));
    }

    @Test
    void shouldValidateEmailWhenTheEmailIsValid() {
        String email = "jack@mail.ru";
        assertTrue(UserValidation.isEmail(email));
    }

    @Test
    void shouldNotValidateEmailWhenTheEmailIsInvalid() {
        List<String> notEmails = Arrays.asList(
                "",
                "aaaaaa",
                "aaa@aaa",
                "@",
                "@."
        );
        for (String invalidEmail : notEmails) {
            assertFalse(UserValidation.isEmail(invalidEmail));
        }
    }
}
