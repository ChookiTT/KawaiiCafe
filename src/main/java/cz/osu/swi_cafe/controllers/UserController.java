package cz.osu.swi_cafe.controllers;

import cz.osu.swi_cafe.repos.UserRepository;
import cz.osu.swi_cafe.services.UserService;
import cz.osu.swi_cafe.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
    @RequestMapping("/api/users")
    @CrossOrigin(origins = "*" ,allowedHeaders= "*" )
    public class UserController {
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private UserService userService;

        @GetMapping
            public List<User> getAllUsers() {
                return userRepository.findAll();
            }
        @PostMapping("/register")
        public User registerUser(@RequestBody User user) {
        return userService.RegisterUser(user);
        }
        @PostMapping("/login")
        public User loginUser(@RequestBody User loginData) {
            return userService.login(loginData.getUsername(), loginData.getPassword());
        }
        @PutMapping("/{username}")
        public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User user) {
            User updated = userService.updateUser(username, user);
            return ResponseEntity.ok(updated);
        }
}
