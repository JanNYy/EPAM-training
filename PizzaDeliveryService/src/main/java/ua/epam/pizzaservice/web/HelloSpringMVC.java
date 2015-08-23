package ua.epam.pizzaservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.epam.pizzaservice.service.PizzaService;

import java.util.Date;

/**
 * Created by Anna on 07.08.2015.
 */
//@Controller(value = "helloController")
public class HelloSpringMVC /*implements org.springframework.web.servlet.mvc.Controller*/ {

    @Autowired
    private PizzaService pizzaService;

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        return "Hello Spring MVC";
    }

//    @Override
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public ModelAndView handleDefaultRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
//        Map<String,Object> map= new HashMap<String,Object>();
//        map.put("msg1",hello());
//        map.put("msg2",new Date());
//        return new ModelAndView("hello",map);
//
//        return new ModelAndView("hello","msg",new Date());
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
     public String handleDefaultRequest(Model model) throws Exception {
        model.addAttribute("msg",new Date());
        return "hello"; // Имя view

//        return new ModelAndView("hello","msg",new Date());
    }

    @RequestMapping(value = "/pizzas", method = RequestMethod.GET)
    public String viewPizzas(Model model) {
        model.addAttribute("pizzas",pizzaService.getAllPizzas());
        return "pizzas";
    }
}
