package com.hutian8.rest.rest.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hutian8.rest.rest.user.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
