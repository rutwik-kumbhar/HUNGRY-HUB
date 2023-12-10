package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;

@Service
public class CustomeUserServiceImpl implements UserDetailsService {
	private UserRepository userRepository;

	@Autowired
	public CustomeUserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> optinal = userRepository.findByEmail(username);

		User user = optinal.orElseThrow(() -> new UsernameNotFoundException("user not found with email " + username));
		List<GrantedAuthority> authorities = new ArrayList<>();

//		return new User(user.getEmail(), user.getPassword(), authorities);
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
	}
}
