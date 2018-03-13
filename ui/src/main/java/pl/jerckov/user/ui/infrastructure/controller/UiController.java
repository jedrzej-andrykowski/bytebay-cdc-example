package pl.jerckov.user.ui.infrastructure.controller;

import pl.jerckov.user.ui.application.User;
import pl.jerckov.user.ui.application.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UiController {

    private final UserService userService;

    public UiController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(final User user, final Model model) {
        return "home";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("user") @Valid final User user, final ModelMap model) {
        userService.addUser(user);
        model.clear();
        return "redirect:/";
    }
}
