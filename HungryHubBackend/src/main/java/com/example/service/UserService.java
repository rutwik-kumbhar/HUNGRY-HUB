package com.example.service;

import com.example.entity.User;
import com.example.exception.UserException;
import com.example.paylod.SignInRequest;
import com.example.response.AuthSignInResponse;

public interface UserService {
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public User createUser(User user) throws UserException;
	
	
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public  AuthSignInResponse userLogin(SignInRequest request);
		

}
