package ua.epam.pizzaservice.web;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Anna on 22.08.2015.
 */
@Controller
@RequestMapping("/adm")
@Secured("ROLE_ADMIN")
public class MainPageAdminController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String returnMainPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("name", auth.getName());
        model.addAttribute("role", auth.getAuthorities());
        return "adminmain";
    }
}
