package ua.epam.pizzaservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Anna on 10.08.2015.
 */
@ResponseStatus(
        value = HttpStatus.NOT_FOUND,
        reason = "Pizza not found")
public class NotFoundPizzaException extends RuntimeException {

    public NotFoundPizzaException(String message) {
        super(message);
    }
}
