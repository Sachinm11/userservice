package com.mircodemo.userservice.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mircodemo.userservice.userservice.Entities.User;

public interface UserRepo extends JpaRepository<User,String> {
    
}
