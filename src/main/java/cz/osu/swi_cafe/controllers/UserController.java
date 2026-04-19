package cz.osu.swi_cafe.controllers;

import cz.osu.swi_cafe.repos.UserRepository;
import cz.osu.swi_cafe.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
    @RequestMapping("/api/users")
    public class UserController {
        @Autowired
        private UserRepository userRepository;

        @GetMapping
        public List<User> getAllUsers() {
            return userRepository.findAll();
        }
}
