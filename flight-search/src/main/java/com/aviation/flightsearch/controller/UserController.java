package com.aviation.flightsearch.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aviation.flightsearch.dto.UserDTO;
import com.aviation.flightsearch.dto.request.RegisterRequest;
import com.aviation.flightsearch.service.UserService;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public List<UserDTO> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping
	public UserDTO createUser(@RequestBody RegisterRequest newUser) {
		return userService.createUser(newUser);
	}
	
	@GetMapping("/{userId}")
	public UserDTO getOneUser(@PathVariable Long userId) {
		return userService.getUserById(userId);
	}
	
	@PutMapping("/{userId}")
	public UserDTO updateUser(@PathVariable Long userId, @RequestBody RegisterRequest newUser) {
		return userService.updateUser(userId, newUser);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable Long userId) {
		userService.deleteUserById(userId);
	}
}
