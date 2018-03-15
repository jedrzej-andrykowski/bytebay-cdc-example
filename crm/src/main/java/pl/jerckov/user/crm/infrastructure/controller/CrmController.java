package pl.jerckov.user.crm.infrastructure.controller;

import pl.jerckov.user.crm.application.User;
import pl.jerckov.user.crm.application.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.UUID;

@Controller
public class CrmController {

    private final UserService userService;

    public CrmController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String user(@PathVariable final UUID id, final Model model) {
        final User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user";
    }

    @ModelAttribute("users")
    public List<User> users() {
        return userService.getAllIUsers();
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public String userNotFound(final HttpClientErrorException exeption) {
        return "error";
    }
}
