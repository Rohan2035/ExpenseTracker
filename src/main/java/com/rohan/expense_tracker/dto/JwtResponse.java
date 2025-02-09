package com.rohan.expense_tracker.dto;

import lombok.Data;

@Data
public class JwtResponse {

    private ApiResponse apiResponse;
    private String token;

}
