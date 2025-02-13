package com.youjob.youjob.exception.annonce;

public class AnnonceNotExistException extends RuntimeException{
    public AnnonceNotExistException(String msg){
        super(msg);
    }
}
