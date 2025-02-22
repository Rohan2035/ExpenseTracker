package com.rohan.expense_tracker.service.impl;

import com.rohan.expense_tracker.dto.ExpenseDto;
import com.rohan.expense_tracker.entity.Expense;
import com.rohan.expense_tracker.entity.User;
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
    public List<ExpenseDto> getAllExpense(String username) {

        List<Expense> expenseList = expenseRepository.findExpenseByUsername(username)
                .orElseThrow(() -> new RuntimeException("Expenses Not Found!"));


        return expenseList.stream()
                .map(expense -> applicationHelper.convertToExpenseDto(expense))
                .toList();

    }

    @Override
    public Boolean addExepnse(ExpenseDto expenseDto, String username) {

        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new RuntimeException("User not found"));

        Expense expense = applicationHelper.convertToExpenseEntity(expenseDto);
        expense.setUser(user);

        expenseRepository.save(expense);

        return true;

    }

}
