package com.microfocus.interApp.controllers;

import com.microfocus.interApp.domain.User;
import com.microfocus.interApp.repositories.UserRepository;
import com.microfocus.interApp.services.DataService;
import com.microfocus.interApp.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/registeredUsers")
    public String getUsers(Model model) {

        HashMap<String, List<String>> registeredUsers = new HashMap<>();
        Iterable<User> users = userRepository.findAll();
        users.forEach(user -> {
            List<String> emails = new ArrayList<>();
            user.getMailAccounts().forEach(mailAccount -> {
                emails.add(mailAccount.getFullName());});
            registeredUsers.put(user.getName(),emails);
        });
        model.addAttribute("registeredUsers",registeredUsers);

        return "users/registeredUsers";
    }

    @GetMapping(value = "/createUser")
    public String createUserForm(Model model) {
        model.addAttribute("user",new User());

        return "users/userRegistration";
    }

    @PostMapping("addUser")
    public String validateAndAddUser(@ModelAttribute("user")User userCandidate, Model model) {
        DataService<User> userService = new UserService(userRepository);
        model.addAttribute("message", userService.insertToDb(userCandidate) );
        return "result";
    }


}
