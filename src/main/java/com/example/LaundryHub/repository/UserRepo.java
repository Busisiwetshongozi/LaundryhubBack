package com.example.LaundryHub.repository;

import com.example.LaundryHub.enums.Role;
import com.example.LaundryHub.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);


}
