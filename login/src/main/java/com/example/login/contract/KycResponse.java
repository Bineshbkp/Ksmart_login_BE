package com.example.login.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class KycResponse {
    private String message;
    private String aadhaarNo;
   
}
