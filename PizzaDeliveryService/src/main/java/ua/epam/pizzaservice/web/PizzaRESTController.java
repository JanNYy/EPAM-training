package ua.epam.pizzaservice.web;

import org.springframework.security.access.annotation.Secured;
import ua.epam.pizzaservice.domain.Pizza;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by Anna on 12.08.2015.
 */
@RestController
public class PizzaRESTController extends AbstractController {

    @RequestMapping(method = RequestMethod.GET, value = "hello")
    public ResponseEntity<String> hello() {
        //return "Hello from RESTController";
        return new ResponseEntity<>("Hello from RESTController",
                HttpStatus.I_AM_A_TEAPOT);
    }

    @Secured("ROLE_USER")
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/pizza/{pizzaId}")
    public ResponseEntity<Pizza> getPizzaById(
            @PathVariable("pizzaId") Pizza pizza) {
        if (pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println(pizza);
        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/pizza",
            headers = "Content-Type=application/json")
    public ResponseEntity<?> createNewPizza(
            @RequestBody Pizza pizza,
            UriComponentsBuilder builder) {
        System.out.println(pizza);
        pizzaService.savePizza(pizza);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/pizza/{id}")
                        .buildAndExpand(pizza.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
