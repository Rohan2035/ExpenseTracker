package com.rohan.expense_tracker.util;

import com.rohan.expense_tracker.dto.ApiResponse;
import com.rohan.expense_tracker.dto.ExpenseDto;
import com.rohan.expense_tracker.entity.Expense;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationHelper {

    @Autowired
    private ModelMapper modelMapper;

    public ApiResponse createApiResponse(String status, String desc) {

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setStatus(status);
        apiResponse.setStatusDesc(desc);

        return apiResponse;

    }

    public ExpenseDto convertToExpenseDto(Expense expense) {

        return modelMapper.map(expense, ExpenseDto.class);

    }

    public Expense convertToExpenseEntity(ExpenseDto expenseDto) {

        return modelMapper.map(expenseDto, Expense.class);

    }

}
