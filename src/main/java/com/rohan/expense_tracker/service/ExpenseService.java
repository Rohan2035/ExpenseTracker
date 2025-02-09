package com.rohan.expense_tracker.service;

import com.rohan.expense_tracker.dto.ExpenseDto;

import java.util.List;

public interface ExpenseService {

    List<ExpenseDto> getAllExpense(Integer userId);

}
