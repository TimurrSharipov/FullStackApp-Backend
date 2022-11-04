package com.example.FullStack.App.repository;

import com.example.FullStack.App.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
