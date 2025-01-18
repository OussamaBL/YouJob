package com.youjob.youjob.exception.auth;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(String msg){
        super(msg);
    }
}
