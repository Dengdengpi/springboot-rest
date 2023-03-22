package com.hutian8.rest.rest.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hutian8.rest.rest.user.Post;

public interface PostRepo extends JpaRepository<Post, Integer>{

}
