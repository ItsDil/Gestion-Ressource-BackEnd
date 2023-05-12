package com.adilbou.securityjwt.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.IM_USED)
public class BlackListException extends RuntimeException{

    public BlackListException(String message){
        super(message);
    }

}
