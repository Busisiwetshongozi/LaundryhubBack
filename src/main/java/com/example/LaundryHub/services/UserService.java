package com.example.LaundryHub.services;

import com.example.LaundryHub.dto.UpdateDTO;
import com.example.LaundryHub.repository.UserRepo;
import com.example.LaundryHub.user.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepo.findAll().forEach(users::add);

        return users;
    }





    // Method to find user by username
    public User findByEmail(String email) {
        return userRepo.findByEmail( email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }

    public User getCurrentUser() {
        User user = getLoggedInUser(); // Fetch the user object
        return user; // Return the user object directly
    }
    public User updateUserDetails(UpdateDTO UpdateDTO) {
        User currentUser = getLoggedInUser();
        if (currentUser != null) {
            currentUser.setFirstName(UpdateDTO.getFirstName());
            currentUser.setLastName(UpdateDTO.getLastName());
            currentUser.setAddress(UpdateDTO.getAddress());
            currentUser.setNumber(UpdateDTO.getNumber());

            // Save updated user details
            userRepo.save(currentUser);

            // Optionally update the authentication context
            Authentication newAuth = new UsernamePasswordAuthenticationToken(currentUser, currentUser.getPassword(), currentUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuth);

            return currentUser;
        }
        return null;
    }
}
