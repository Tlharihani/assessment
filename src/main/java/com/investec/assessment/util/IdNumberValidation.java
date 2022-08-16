package com.investec.assessment.util;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class IdNumberValidation {
        public boolean isValidIdNumber(String identity){
            int sumOdd = 0, sumEven = 0, _doubled;
            int summation;
            try {
                if(identity.length() == 13){

                int length = identity.length() - 1;
                char[] chars = identity.toCharArray();
                String check = identity.substring(12);
                for(int x  = 0; x < length; x++){
                    if(x % 2 == 0 ){
                        String numString = String.valueOf(chars[x]);
                        int numbers = Integer.valueOf(numString);
                        sumOdd += numbers;
                    }else if(x % 2 != 0){
                        String numString = String.valueOf(chars[x]);
                        int numbers = Integer.valueOf(numString);
                        int doubled = numbers * 2;
                        if(doubled > 9){
                            _doubled = doubled - 9;
                        }else{
                            _doubled = doubled;
                        }
                        sumEven += _doubled;
                    }
                }
                summation = sumOdd + sumEven;
                int checksum = Integer.valueOf(check);
                if((summation * 9) % 10 == checksum)
                    return true;
                else
                    return false;
                }else{
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

}
