package com.rohan.expense_tracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rohan.expense_tracker.dto.UserDto;
import com.rohan.expense_tracker.entity.User;
import com.rohan.expense_tracker.repository.UserRepository;
import com.rohan.expense_tracker.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Boolean addUser(UserDto userDto) {
		
		User user = mapToUser(userDto);
		
		userRepository.save(user);
		
		return true;
	
	}
	
	private User mapToUser(UserDto userDto) {
		
		User user = new User();
		
		user.setUsername(userDto.getUsername());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setRoles(userDto.getRoles());
		user.setIncome(userDto.getIncome());
		
		return user;
		
	}

}
