package com.muhtar.FindASpy.controller;

import com.muhtar.FindASpy.entity.User;
import com.muhtar.FindASpy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        System.err.println("In registration GetMapping");
        return "registration";
    }

    @PostMapping
    public String addUser(@ModelAttribute("userForm") User userForm,
                          Model model) {
        System.err.println("In registration PostMapping with: " + userForm.toString());
        if (!(isUsernameOk(userForm.getUsername()) && isPasswordOk(userForm.getPassword()))) {
            System.err.println("username or password has errors");
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Confirmation password is different from password");
            System.err.println("password confirmation has errors");
            return "registration";
        }
        if (!userService.saveUser(userForm)) {
            model.addAttribute("usernameError", "Username is already exists");
            System.err.println("username has errors");
            return "registration";
        }
        return "redirect:/";
    }

    private static boolean isUsernameOk(String username) {
        // username length -> 2-16 symbols
        // contains only   -> a-z, A-Z, 0-9, cannot start with integers
        if (username.length() < 2 || username.length() > 16) {
            return false;
        }
        if (username.charAt(0) >= 48 && username.charAt(0) <= 57) {
            return false;
        }
        for (int i = 0; i < username.length(); i++) {
            if (!(
                    (username.charAt(i) >= 65 && username.charAt(i) <= 90) ||
                            (username.charAt(i) >= 97 && username.charAt(i) <= 122) ||
                            (username.charAt(i) >= 48 && username.charAt(i) <= 57)
            )) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPasswordOk(String password) {
        // length -> greater than 4 symbols
        if (password.length() < 4) {
            return false;
        }
        return true;
    }

}
