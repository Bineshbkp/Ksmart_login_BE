package com.example.login.contract;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class RegisterRequest {
  
    private String phoneNumber;
     @NotNull(message = "Email is required")
    private String email;  
    private Boolean countryType;
    private Boolean isKkycVerified = false;
    private String otp; 
    private String aadhaarNo; 
    private String aadhaarOtp; 
    private String name;
    private String dob;
    private String gender;

        
}
