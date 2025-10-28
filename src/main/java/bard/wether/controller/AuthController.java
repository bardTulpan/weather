package bard.wether.controller;

import bard.wether.entity.Session;
import bard.wether.entity.User;
import bard.wether.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/auth")
@RestController
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String register() {
        return "register";
    }

//    @GetMapping("/login")
//    public List<User> login() {
//        User user = userRepository.createTestUser();
//        Session session = userRepository.createTestSession(user);
//        userRepository.saveSession(session);
//        return userRepository.findAll();
//    }

    @GetMapping("/login")
    public List<User> login() {
        try {
            // 1. Создаем и сохраняем пользователя
            User user = userRepository.createTestUser();
            System.out.println("User created with ID: " + user.getId());

            // 2. Создаем сессию для сохраненного пользователя
            Session session = userRepository.createTestSession(user);
            System.out.println("Session created with ID: " + session.getId());

            return userRepository.findAll();
        } catch (Exception e) {
            System.out.println("Error in login: " + e.getMessage());
            return userRepository.findAll();
        }
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }
}
