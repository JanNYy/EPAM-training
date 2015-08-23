package ua.epam.pizzaservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.epam.pizzaservice.domain.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anna on 22.08.2015.
 */
@Controller
//@SessionAttributes("order")
@Secured("ROLE_USER")
@RequestMapping("/pizzas")
public class PizzaUserController extends AbstractController {

//    @ModelAttribute("order")
    public Order createNewOrder() {
        Order order = new Order();

        Map<Pizza, Integer> pizzas = new HashMap<Pizza, Integer>();
        order.setPizzas(pizzas);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        order.setCustomer(customerService.getCustomerByUserName(auth.getName()));

        return order;
    }

    @Secured({"ROLE_ANONYMOUS","ROLE_USER"})
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewPizzasForOrder(Model model,  HttpServletRequest request) {
        model.addAttribute("pizzas", pizzaService.getAllPizzas());
        HttpSession session = request.getSession();
        Order order = (Order)session.getAttribute("order");
        if (order != null)
        {
            Map<Pizza, Integer> pizzas = order.getPizzas();
            int count = 0;
            for (Map.Entry<Pizza, Integer> p : pizzas.entrySet())
            {
                count += p.getValue();
            }
            model.addAttribute("number_of_pizzas", count);
        }
        return "choosepizzas";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String addPizzaToOrder(Model model,
//                                  @ModelAttribute Order order,
                                  HttpServletRequest request,
                                  @RequestParam("id") Integer id) {
        Pizza pizza = getPizzaByID(id);

        HttpSession session = request.getSession();
        Order order = (Order)session.getAttribute("order");
        if (order == null)
            order = createNewOrder();

        order.addPizza(pizza);
        session.setAttribute("order",order);

        return "redirect:";
    }

    @RequestMapping(value = "/viewcart", method = RequestMethod.GET)
    public String viewCart(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Order order = (Order)session.getAttribute("order");
        if (order != null)
        {
            model.addAttribute("pizzas_in_order", order.getPizzas());
            TotalOrderCostCalculator calculator = new TotalOrderCostCalculator();

            AccumulativeCard accumulativeCard = order.getCustomer().getAccumulativeCard();

            Double sumOrder = calculator.calculateTotalOrderPriceWithAccumulativeCardSum(
                    order.getPizzas(), accumulativeCard.getSum());
            order.setSum(sumOrder);
            model.addAttribute("order_price",sumOrder);
        }
        return "cartuser";
    }

    @RequestMapping(value = "/submitorder", method = RequestMethod.POST)
    public String submitOrder(Model model) {
        model.addAttribute("cities", cityService.getAllCities());
        return "makeorder";
    }

    @RequestMapping(value = "/clearcart", method = RequestMethod.POST)
    public String clearCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Order order = (Order)session.getAttribute("order");
        session.setAttribute("order",null);
        return "redirect:";
    }

    @RequestMapping(value = "/confirmorder", method = RequestMethod.POST)
    public String confirmOrder(Model model,
                               HttpServletRequest request,
                               /*@ModelAttribute Address address*/
                               @RequestParam("city") City city,
                               @RequestParam("street") Street street,
                               @RequestParam("building") String building,
                               @RequestParam("apartment") String apartment
                               /*@RequestParam("dateTime") Date dateTime*/) {
        HttpSession session = request.getSession();
        Order order = (Order)session.getAttribute("order");

        order.setAddress(new Address(city, street, building, apartment));
//        order.setAddress(address);
        order.setDateTime(new Date());
//        order.setDateTime(dateTime);

//        TotalOrderCostCalculator calculator = new TotalOrderCostCalculator();
//
        AccumulativeCard accumulativeCard = order.getCustomer().getAccumulativeCard();
//
//        Double sumOrder = calculator.calculateTotalOrderPriceWithAccumulativeCardSum(
//                order.getPizzas(), accumulativeCard.getSum());
//        order.setSum(sumOrder);

//        accumulativeCard.addSum(sumOrder);
        accumulativeCard.addSum(order.getSum());
        accumulativeCardService.saveAccumulativeCard(accumulativeCard);

        orderService.saveOrder(order);

        session.setAttribute("order", null);
        return "redirect:/pages/";
    }
}
