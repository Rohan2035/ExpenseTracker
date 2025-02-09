package com.rohan.expense_tracker.dto;

import lombok.Data;

@Data
public class UserDto {
	
	private String username;
	
	private String password;
	
	private String roles;
	
	private String firstName;
	
	private String lastName;
	
	private String income;
	
}
