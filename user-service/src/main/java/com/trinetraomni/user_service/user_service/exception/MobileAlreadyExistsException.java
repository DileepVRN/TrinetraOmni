package com.trinetraomni.user_service.user_service.exception;

public class MobileAlreadyExistsException extends RuntimeException {
    public MobileAlreadyExistsException(String mobile) {
        super("Mobile number already exists: " + mobile);
    }
}
