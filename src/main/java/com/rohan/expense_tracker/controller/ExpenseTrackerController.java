package com.rohan.expense_tracker.controller;

import com.rohan.expense_tracker.dto.ApiResponse;
import com.rohan.expense_tracker.service.ExpenseService;
import com.rohan.expense_tracker.util.ApplicationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rohan.expense_tracker.dto.ExpenseDto;
import static com.rohan.expense_tracker.util.Constants.*;

import java.util.List;

@RestController
@RequestMapping("/expense-tracker")
public class ExpenseTrackerController {

	public static final Logger LOGGER = LoggerFactory.getLogger(ExpenseTrackerController.class);

	@Autowired
	private ExpenseService expenseService;

	@Autowired
	private ApplicationHelper applicationHelper;
	
	@GetMapping("/fetch-expenses/{username}")
	public ResponseEntity<List<ExpenseDto>> fetchExpenses(@PathVariable("username") String username) {

		List<ExpenseDto> expenseDtoList = expenseService.getAllExpense(username.toLowerCase());

		LOGGER.info("{} Expenses successfully fetched", expenseDtoList.size());

		return new ResponseEntity<>(expenseDtoList, HttpStatus.OK);
		
	}

	@PostMapping("/add-expense/{username}")
	public ResponseEntity<ApiResponse> addExpense(@RequestBody ExpenseDto expenseDto,
												  @PathVariable("username") String username) {

		if(expenseService.addExepnse(expenseDto, username.toLowerCase())) {

			return new ResponseEntity<>(applicationHelper.createApiResponse(STATUS_SUCCESS
					,EXPENSE_ADDED_SUCCESSFULLY),
					HttpStatus.OK);

		}

		return new ResponseEntity<>(applicationHelper.createApiResponse(STATUS_FAIL, EXPENSE_CANNOT_BE_ADDED),
				HttpStatus.OK);

	}
	

}