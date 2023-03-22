package com.hutian8.rest.rest.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hutian8.rest.rest.jpa.PostRepo;
import com.hutian8.rest.rest.jpa.UserRepo;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	
	private PostRepo postRepository;
	private UserRepo repository;

	public UserJpaResource(PostRepo postRepository, UserRepo repository) {
		super();
		this.postRepository = postRepository;
		this.repository = repository;
	}
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		return repository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public User retrieveUser(@PathVariable int id){
		Optional<User> user = repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id" + id);
		}
		return user.get();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id){
		repository.deleteById(id);
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = repository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id){
		Optional<User> user = repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("no user");
		}
		return user.get().getPosts();
	}
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post){
		Optional<User> user = repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("no user");
		}
		post.setUser(user.get());
		Post savedPost = postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
}
