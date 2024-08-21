package com.example.LaundryHub.controller;

import com.example.LaundryHub.dto.UpdateDTO;
import com.example.LaundryHub.entity.Orders;
import com.example.LaundryHub.services.UserService;
import com.example.LaundryHub.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }
    @GetMapping("/user")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String username = authentication.getName();
        User user = userService.findByEmail(username);
        return ResponseEntity.ok(user);
    }



    @GetMapping("/")
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.allUsers();

        return ResponseEntity.ok(users);

    }

    @PutMapping("/update")
    public User updateUserDetails(@RequestBody UpdateDTO UpdateDTO) {
        return userService.updateUserDetails(UpdateDTO);
    }

}