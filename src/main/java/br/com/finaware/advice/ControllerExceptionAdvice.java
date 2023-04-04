package br.com.finaware.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import  org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> erroNotFound(EntityNotFoundException ex){
        Map<String,Object> response = new LinkedHashMap<>();
        response.put("error", ex.getMessage());
        response.put("timestamp", new Date());
        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );
    }

}
