package com.rohan.expense_tracker.controller;

import com.rohan.expense_tracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rohan.expense_tracker.dto.ExpenseDto;

import java.util.List;

@RestController
@RequestMapping("/expense-tracker")
public class ExpenseTrackerController {

	@Autowired
	private ExpenseService expenseService;
	
	@GetMapping("/fetch-expenses")
	public ResponseEntity<List<ExpenseDto>> fetchExpenses(@RequestParam Integer userId) {

		List<ExpenseDto> expenseDtoList = expenseService.getAllExpense(userId);

		return new ResponseEntity<>(expenseDtoList, HttpStatus.OK);
		
	}
	

}