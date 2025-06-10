package com.example.login.service;



import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.login.contract.KycResponse;
import com.example.login.contract.LoginResponse;
import com.example.login.contract.RegisterRequest;
import com.example.login.contract.RegisterResponse;
import com.example.login.model.User;
import com.example.login.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;  
    private ModelMapper modelMapper;
    

    public RegisterResponse registerUser(RegisterRequest request) {
        
        boolean isUser = Boolean.TRUE.equals(request.getCountryType());
        String identifier= isUser ? request.getPhoneNumber() : request.getEmail();
 
        // Check for existing user
        Optional<User> existingUser = isUser
            ? userRepository.findByPhoneNumber(request.getPhoneNumber())
            : userRepository.findByEmail(request.getEmail());
 
        if (existingUser.isPresent()) {
         return new RegisterResponse("User already registered",identifier);
        }
 
         // Validate OTP
        if (!"123456".equals(request.getOtp())) {
            return new RegisterResponse("Invalid OTP. Access denied.",null);
        }
 
        // Create and save new user
        User user = modelMapper.map(request, User.class);
        //user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        userRepository.save(user);
 
        return new RegisterResponse("User Account Created",identifier);

    }


    public KycResponse verifyAadhaar(RegisterRequest request) {
        //Optional<User> user = userRepository.findByPhoneNumberAndEmail(request.getPhoneNumber(), request.getEmail());
        // if (user.isEmpty()) {
        //     return new KycResponse("User not found. Pls register", null);        
        // }
         String userId = request.getPhoneNumber() != null ? request.getPhoneNumber() : request.getEmail();
         // Check if input is email or phone
             Optional<User> user;
        if (userId.contains("@")) {
            user = userRepository.findByEmail(userId);
        } else {
         user = userRepository.findByPhoneNumber(userId);
        }
        
        if (user.isEmpty()) {
            return new KycResponse("User not found. Pls register",null);
            }         

              // Validate OTP
         if (!"1234".equals(request.getAadhaarOtp())) {
            return new KycResponse("Invalid OTP. Access denied.", null);
         }
       
            User existingUser = user.get();
            
            existingUser.setIsKkycVerified(true);
            existingUser.setIsActive(true);
            existingUser.setAadhaarNo(request.getAadhaarNo());
            existingUser.setDob(request.getDob());
            existingUser.setGender(request.getGender());
            existingUser.setName(request.getName());
            //existingUser.setPhoneNumber(request.getPhoneNumber());
           // existingUser.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));            
            userRepository.save(existingUser);
            return new KycResponse("KYC verified successfully",request.getAadhaarNo());
    }    
    

    public LoginResponse checkLogin(RegisterRequest request) {
        System.out.println("Received login request:");
        System.out.println("Phone: " + request.getPhoneNumber());
        System.out.println("Email: " + request.getEmail());
        System.out.println("OTP: " + request.getOtp());
       // Optional<User> user = userRepository.findByPhoneNumberOrEmail(request.getPhoneNumber(), request.getEmail());
         String userId = request.getPhoneNumber() != null ? request.getPhoneNumber() : request.getEmail();
         // Check if input is email or phone
        Optional<User> user;
        if (userId.contains("@")) {
        user = userRepository.findByEmail(userId);
        } else {
         user = userRepository.findByPhoneNumber(userId);
        }
        
        if (user.isEmpty()) {
            return new LoginResponse("User not found. Pls register",null);
                       
        }

           // Validate OTP
        if (request.getOtp() == null || request.getOtp().isBlank()) {
        // sendOTP(user.get()); // Simulate OTP sent
        return new LoginResponse("OTP sent to your registered contact.",userId);
        }

        if (!"1234".equals(request.getOtp())) {
            return new LoginResponse("Invalid OTP. Access denied.",null);
        
        } 
        
        if(Boolean.TRUE.equals(user.get().getIsKkycVerified())) {
            return new LoginResponse("Login successful", userId);
        } else {
            return new LoginResponse("KYC not verified. Please verify your KYC.",userId);
        }
    }   
        
 }      
