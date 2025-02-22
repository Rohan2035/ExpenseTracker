package com.rohan.expense_tracker.service.impl;

import com.rohan.expense_tracker.util.ApplicationHelper;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
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

	@Autowired
	private ApplicationHelper applicationHelper;

	
	@Override
	@Transactional
	public Boolean addUser(UserDto userDto) {

		if(userDto.getRoles() == null || userDto.getRoles().isEmpty() || userDto.getRoles().isBlank()) {

			userDto.setRoles("USER");

		}

		String usernameLowerCase = userDto.getUsername().toLowerCase();
		String encodedPassword = passwordEncoder.encode(userDto.getPassword());

		userDto.setPassword(encodedPassword);
		userDto.setUsername(usernameLowerCase);

		User user = applicationHelper.convertToUser(userDto);

		userRepository.save(user);

		return true;

	}

}
