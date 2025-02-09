package com.rohan.expense_tracker.controller;

import com.rohan.expense_tracker.dto.ApiResponse;
import com.rohan.expense_tracker.dto.CredentialsDto;
import com.rohan.expense_tracker.dto.JwtResponse;
import com.rohan.expense_tracker.util.ApplicationHelper;
import com.rohan.expense_tracker.util.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.rohan.expense_tracker.util.Constants.*;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtility jwtUtility;

	@Autowired
	private ApplicationHelper applicationHelper;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> userLogin(@RequestBody CredentialsDto credentialsDto) {

		ApiResponse apiResponse;
		JwtResponse jwtResponse;
		
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentialsDto.getUsername(), 
					credentialsDto.getPassword()));
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(credentialsDto.getUsername());
			
			String jwtToken = jwtUtility.generateToken(userDetails.getUsername());

			apiResponse = applicationHelper.createApiResponse(STATUS_SUCCESS, JWT_SUCCESS_DESC);

			jwtResponse = new JwtResponse();
			jwtResponse.setToken(jwtToken);
			jwtResponse.setApiResponse(apiResponse);

			return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
			
		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
		
	}

}
