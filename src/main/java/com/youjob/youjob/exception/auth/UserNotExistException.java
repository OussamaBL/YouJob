package com.youjob.youjob.exception.auth;

public class UserNotExistException extends RuntimeException{
    public UserNotExistException(String msg){
        super(msg);
    }
}
