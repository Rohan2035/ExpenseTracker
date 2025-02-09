package com.rohan.expense_tracker.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.rohan.expense_tracker.dto.UserDto;

public interface UserService {
	
	public Boolean addUser(UserDto userDto);

}
