package com.rohan.expense_tracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.rohan.expense_tracker.dto.UserDto;
import com.rohan.expense_tracker.entity.User;
import com.rohan.expense_tracker.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		User user = userRepository.findByUsername(username)
						.orElseThrow(() -> new RuntimeException("User not Found!"));
		
		return org.springframework.security.core.userdetails.User
				.builder()
				.username(username)
				.password(user.getPassword())
				.roles(user.getRoles().split(","))
				.build();
	
	}
	

}
