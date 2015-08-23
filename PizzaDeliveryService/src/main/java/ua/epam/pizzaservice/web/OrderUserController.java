package ua.epam.pizzaservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.epam.pizzaservice.domain.Customer;
import ua.epam.pizzaservice.service.CustomerService;
import ua.epam.pizzaservice.service.OrderService;

/**
 * Created by Anna on 22.08.2015.
 */
@Controller
@RequestMapping("/orders")
public class OrderUserController extends AbstractController {

    @Secured("ROLE_USER")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewPizzas(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        Customer customer = customerService.getCustomerByUserName(userName);

        model.addAttribute("orders", orderService.getAllOrdersByCustomerID(customer.getId()));
        return "ordersuser";
    }
}
