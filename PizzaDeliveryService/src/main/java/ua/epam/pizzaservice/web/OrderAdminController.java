package ua.epam.pizzaservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.epam.pizzaservice.service.OrderService;

/**
 * Created by Anna on 22.08.2015.
 */
@Controller
@RequestMapping("/adm/orders")
public class OrderAdminController extends AbstractController {

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewPizzas(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "ordersadm";
    }
}
