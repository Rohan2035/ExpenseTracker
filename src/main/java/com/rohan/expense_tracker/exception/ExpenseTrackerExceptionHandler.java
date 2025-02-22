package com.rohan.expense_tracker.exception;

import com.rohan.expense_tracker.dto.ApiResponse;
import com.rohan.expense_tracker.util.ApplicationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import static com.rohan.expense_tracker.util.Constants.*;

@ControllerAdvice
public class ExpenseTrackerExceptionHandler {

    @Autowired
    private ApplicationHelper applicationHelper;

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<ApiResponse> expenseNotFoundExceptionHandler(ExpenseNotFoundException ex) {

        return new ResponseEntity<>(applicationHelper.createApiResponse(NOT_FOUND ,EXPENSE_NOT_FOUND), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse> userNotFoundExceptionHandler(UserNotFoundException ex) {

        return new ResponseEntity<>(applicationHelper.createApiResponse(NOT_FOUND, USER_NOT_FOUND), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse> badCredentialsExceptionHandler(BadCredentialsException ex) {

        return new ResponseEntity<>(applicationHelper.createApiResponse(USER_NOT_FOUND, BAD_CREDENTIALS), HttpStatus.UNAUTHORIZED);

    }

}
