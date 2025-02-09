package com.rohan.expense_tracker.service.impl;

import com.rohan.expense_tracker.dto.ExpenseDto;
import com.rohan.expense_tracker.entity.Expense;
import com.rohan.expense_tracker.repository.ExpenseRepository;
import com.rohan.expense_tracker.repository.UserRepository;
import com.rohan.expense_tracker.service.ExpenseService;
import com.rohan.expense_tracker.util.ApplicationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationHelper applicationHelper;

    @Override
    public List<ExpenseDto> getAllExpense(Integer userId) {

        List<Expense> expenseList = expenseRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Expenses Not Found!"));


        return expenseList.stream()
                .map(expense -> applicationHelper.convertToExpenseDto(expense))
                .toList();

    }

}
