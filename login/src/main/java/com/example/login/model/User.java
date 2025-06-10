package com.example.login.model;


import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "k_smart_user")
@Getter
@Setter

public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;
    private String phoneNumber;
    private Integer tenantId;
    private String name;
    private Boolean isActive;
    private Boolean isKkycVerified;
    private String aadhaarNo;
    private String isFirstLogin;
    private String email;  
    private String whatsappNumber;
    private String userType;
    private Timestamp createdAt;
    private Timestamp updatedAt;       
    private String dob;
    private String gender;
    private String countryCode; 
    private Boolean countryType;
   
    
}
   
    
    

    
   
  
    
   
   
    
