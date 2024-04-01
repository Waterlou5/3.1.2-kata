package com.example.demo.controller;

import com.example.demo.models.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String showAll(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("AllUsers", users);
        return "users";
    }

    @GetMapping("/addNewUser")
    public String AddNewUser(Model model) {
        model.addAttribute("user", new User());
        return "addNewUser";
    }

    @PostMapping("/SaveUsers")
    public String SaveUser(@ModelAttribute("user") User user) {
        if (user.getId() != null) {
            User existingUser = userService.getUser(user.getId());
            if (existingUser != null) {
                user.setId(existingUser.getId());
            }
        }
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/updateInfo")
    public String updateUser(@RequestParam("userId") Long userId, Model model) {
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "addNewUser";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/users";
    }

}
