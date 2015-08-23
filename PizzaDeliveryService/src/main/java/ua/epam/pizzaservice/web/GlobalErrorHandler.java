package ua.epam.pizzaservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Anna on 10.08.2015.
 */
@ControllerAdvice
public class GlobalErrorHandler {

//    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundPizzaException.class)
    public ModelAndView ExceptionHandler(Exception exception, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("ex", exception);
        model.addObject("url", request.getRequestURL());
        return model;
    }

}
