package com.rohan.expense_tracker.exception;

public class ExpenseNotFoundException extends RuntimeException {

    public ExpenseNotFoundException(String msg) {
        super(msg);
    }

}
