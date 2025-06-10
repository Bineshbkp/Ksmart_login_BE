package com.example.login.controller;


import org.springframework.web.bind.annotation.RestController;

import com.example.login.contract.KycResponse;
import com.example.login.contract.LoginResponse;
import com.example.login.contract.RegisterRequest;
import com.example.login.contract.RegisterResponse;
import com.example.login.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") 
public class LoginController  {   
    private UserService userService; 

    @PostMapping("/register")
    public RegisterResponse userRegister(@Valid @RequestBody RegisterRequest request) {     
        
        return userService.registerUser(request);
    }  
   
   
   
    @PostMapping("/login")
        public LoginResponse userLogin(@RequestBody RegisterRequest request) {   
       
      return userService.checkLogin(request);       
   }

   @PostMapping("/checkkyc")
        public KycResponse verifyKyc(@RequestBody RegisterRequest request) {   
       
      return userService.verifyAadhaar(request);
       
   }
   
}
