package com.RegistrationAndPaymentSystem.exception;

public class UserAlreadyExistsException extends  RuntimeException{
    public UserAlreadyExistsException(String massage){
        super(massage);
    }
}
