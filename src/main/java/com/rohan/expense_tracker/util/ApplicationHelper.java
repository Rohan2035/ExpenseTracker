package com.rohan.expense_tracker.util;

import com.rohan.expense_tracker.dto.ApiResponse;
import com.rohan.expense_tracker.dto.ExpenseDto;
import com.rohan.expense_tracker.dto.UserDto;
import com.rohan.expense_tracker.entity.Expense;
import com.rohan.expense_tracker.entity.User;
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

    public User convertToUser(UserDto userDto) {

        return modelMapper.map(userDto, User.class);

    }

    public UserDto convertToUser(User user) {

        return modelMapper.map(user, UserDto.class);

    }

}
