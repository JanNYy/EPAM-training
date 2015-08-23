package ua.epam.pizzaservice.web;

import ua.epam.pizzaservice.domain.Pizza;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Anna on 10.08.2015.
 */
@Controller
@RequestMapping("/adm/pizzas")
@Secured("ROLE_ADMIN")
public class PizzaAdminController extends AbstractController {

//    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewPizzas(Model model) {
        model.addAttribute("pizzas", pizzaService.getAllPizzas());
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        model.addAttribute("name", auth.getName());
//        model.addAttribute("role", auth.getAuthorities());

//        ThreadLocal thl = new ThreadLocal();

        return "pizzasadm";
    }

//    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreatePizza(Model model) {
        model.addAttribute("types", pizzaService.getAllPizzaTypes());
        return "pizzaeditcreate";
    }

//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public String createPizza(@RequestParam(value = "name") String pizzaName,
//                              @RequestParam(value = "type") TypePizza pizzaType,
//                              @RequestParam(value = "price") Double pizzaPrice) {
//        Pizza pizza = new Pizza();
//        pizza.setName(pizzaName);
//        pizza.setType(pizzaType);
//        pizza.setPrice(pizzaPrice);
//        pizzaService.savePizza(pizza);
//        return "redirect:";
//    }

//    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addPizza(@ModelAttribute Pizza pizza) {
//        pizza.setType(pizzaService.getPizzaTypeByEnum(typeEnum));
        pizzaService.savePizza(pizza);
        return "redirect:";
    }

//    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String showUpdatePizza(@RequestParam("id") Integer id,
                                  Model model) {
        Pizza pizza = getPizzaByID(id);
//        if (pizza == null)
//            throw new NotFoundPizzaException("pizza not found");
        model.addAttribute("pizza", pizza);
        model.addAttribute("types", pizzaService.getAllPizzaTypes());
        return "pizzaeditcreate";
    }


//    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePizza(@RequestParam("id") Integer id) {
        pizzaService.deletePizza(id);
        return "redirect:";
    }

//    @ExceptionHandler(NotFoundPizzaException.class)
//    public ModelAndView ExceptionHandler(Exception exception, HttpServletRequest request) {
//        ModelAndView model = new ModelAndView();
//        model.addObject("ex", exception);
//        model.addObject("url", request.getRequestURL());
//        return model;
//    }
}
