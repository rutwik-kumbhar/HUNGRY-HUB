package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.exception.UserException;
import com.example.paylod.SignInRequest;
import com.example.response.AuthSignInResponse;
import com.example.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
     
	
	private UserService userService;
	
	@Autowired
	public AuthController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/signup/user")
	public ResponseEntity<User> createUserHandler(@RequestBody User user){
		user.setRole("ROLE_USER");
		return new ResponseEntity<User>(userService.createUser(user),HttpStatus.CREATED);		 
	}
	
	
	@PostMapping("/signup/admin")
	public ResponseEntity<User> createAdminHandler(@RequestBody User user) {
		user.setRole("ROLE_ADMIN");
		return new ResponseEntity<User>(userService.createUser(user),HttpStatus.CREATED);		 
	}
	
	
//	Restaurants
	
	@PostMapping("/signup/restaurants/admin")
	public ResponseEntity<User> createRestaurantAdminHandler(@RequestBody User user) {
		user.setRole("ROLE_RESTAURANTADMIN");
		return new ResponseEntity<User>(userService.createUser(user),HttpStatus.CREATED);		 
	}
	
	
	@PostMapping("/signin")
	public ResponseEntity<AuthSignInResponse> loginUserHandler(@RequestBody SignInRequest logingRequest){
		return new ResponseEntity<AuthSignInResponse>(userService.userLogin(logingRequest),HttpStatus.OK );
	}
}
