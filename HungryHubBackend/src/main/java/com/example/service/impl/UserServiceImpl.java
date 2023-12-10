package com.example.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.exception.UserException;
import com.example.paylod.SignInRequest;
import com.example.repository.UserRepository;
import com.example.response.AuthSignInResponse;
import com.example.securityconfig.JwtProvider;
import com.example.service.CustomeUserServiceImpl;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	private PasswordEncoder passwordEncoder;
	private CustomeUserServiceImpl customeUserServiceImpl;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder,
			CustomeUserServiceImpl customeUserServiceImpl) {
		super();
		this.userRepository = userRepository;
		this.jwtProvider = jwtProvider;
		this.passwordEncoder = passwordEncoder;
		this.customeUserServiceImpl = customeUserServiceImpl;
	}

	@Override
	public User createUser(User user) throws UserException {
		// TODO Auto-generated method stub
		Optional<User> optional = userRepository.findByEmail(user.getEmail());

		if (optional.isPresent())
			throw new UserException("Email is already used try with another email");

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepository.save(user);

	}

	@Override
	public AuthSignInResponse userLogin(SignInRequest request) {
		// TODO Auto-generated method stub
		Authentication authentication = authenticate(request.getEmail(), request.getPassword());

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.genrateTokan(authentication);

		Optional<User> optional = userRepository.findByEmail(request.getEmail());

		User user = optional.orElseThrow(() -> new UserException("User Not Resister"));
		AuthSignInResponse authResponse = new AuthSignInResponse();

		authResponse.getUser().put("firstName", user.getFirstName());
		authResponse.getUser().put("lastName", user.getLastName());
		authResponse.getUser().put("email", user.getEmail());
		authResponse.getUser().put("role", user.getRole());

		authResponse.setJwt(token);
		authResponse.setMessage("Sign In Success");

	

		return authResponse;
	}

	private Authentication authenticate(String username, String password) {
		// TODO Auto-generated method stub
		UserDetails userDetails = customeUserServiceImpl.loadUserByUsername(username);
		if (userDetails == null)
			throw new BadCredentialsException("Invalid Username");
		if (!passwordEncoder.matches(password, userDetails.getPassword()))

			throw new BadCredentialsException("Invalid Password");
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
