package com.RegistrationAndPaymentSystem.exception;

public class ValidationException extends  RuntimeException{
    public  ValidationException (String massage){
        super(massage);
    }
}
