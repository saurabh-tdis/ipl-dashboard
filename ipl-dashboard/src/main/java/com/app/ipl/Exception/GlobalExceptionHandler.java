package com.app.ipl.Exception;

import com.app.ipl.dto.AppResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author saurabh vaish
 * @Date 08-07-2022
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = TeamNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppResponse<?>> handleTeamNotFound(TeamNotFoundException ex){
        return ResponseEntity.badRequest().body(new AppResponse<>(HttpStatus.BAD_REQUEST,"Invalid details",ex.getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppResponse<?>> handleException(Exception ex){
        ex.printStackTrace();
        return ResponseEntity.badRequest().body(new AppResponse<>(HttpStatus.BAD_REQUEST,"Something went wrong",ex.getMessage()));
    }


}
