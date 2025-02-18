package com.youjob.youjob.web.vm.error;

import com.youjob.youjob.exception.ForgetPass.TokenInvalidException;
import com.youjob.youjob.exception.NullVarException;
import com.youjob.youjob.exception.annonce.AnnonceNotExistException;
import com.youjob.youjob.exception.annonce.InvalidAnnonceException;
import com.youjob.youjob.exception.auth.StatusInvalidException;
import com.youjob.youjob.exception.auth.UserAlreadyExistException;
import com.youjob.youjob.exception.auth.UserNotExistException;
import com.youjob.youjob.exception.consultation.ConsultationInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /*Handle the error of @valid*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException exceptions){
        Map<String,String> errors=new HashMap<>();
        exceptions.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(),error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentials(BadCredentialsException ex) {
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<Map<String,String>> handleUserNotExistException(UserNotExistException exception){
        Map<String,String> error=new HashMap<>();
        error.put("error",exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Map<String,String>> handleUserAlreadyExistException(UserAlreadyExistException exception){
        Map<String,String> error=new HashMap<>();
        error.put("error",exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    @ExceptionHandler(TokenInvalidException.class)
    public ResponseEntity<Map<String,String>> handleTokenInvalidException(TokenInvalidException exception){
        Map<String,String> error=new HashMap<>();
        error.put("error",exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    @ExceptionHandler(InvalidAnnonceException.class)
    public ResponseEntity<Map<String,String>> handleInvalidAnnonceException(InvalidAnnonceException exception){
        Map<String,String> error=new HashMap<>();
        error.put("error",exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    @ExceptionHandler(AnnonceNotExistException.class)
    public ResponseEntity<Map<String,String>> handleAnnonceNotExistException(AnnonceNotExistException exception){
        Map<String,String> error=new HashMap<>();
        error.put("error",exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    @ExceptionHandler(NullVarException.class)
    public ResponseEntity<Map<String,String>> handleNullVarException(NullVarException exception){
        Map<String,String> error=new HashMap<>();
        error.put("error",exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    @ExceptionHandler(StatusInvalidException.class)
    public ResponseEntity<Map<String,String>> handleStatusInvalidException(StatusInvalidException exception){
        Map<String,String> error=new HashMap<>();
        error.put("error",exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    @ExceptionHandler(ConsultationInvalidException.class)
    public ResponseEntity<Map<String,String>> handleConsultationInvalidException(ConsultationInvalidException exception){
        Map<String,String> error=new HashMap<>();
        error.put("error",exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

}
