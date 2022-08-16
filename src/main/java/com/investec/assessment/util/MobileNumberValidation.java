package com.investec.assessment.util;

import org.springframework.stereotype.Component;

@Component
public class MobileNumberValidation {
    public boolean validateMobileNumber(String mobileNumber){
        if(mobileNumber.length() != 10 || !mobileNumber.startsWith("0")){
            return false;
        } else{
            return true;
        }
    }
}
