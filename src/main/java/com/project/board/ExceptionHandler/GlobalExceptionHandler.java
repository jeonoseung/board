package com.project.board.ExceptionHandler;

import com.project.board.DTO.Response.ExceptionHandlerBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionHandlerBody> UnauthorizedException(UnauthorizedException ex){
        String message = ex.getMessage();
        ExceptionHandlerBody bodys = new ExceptionHandlerBody();
        bodys.SetErrorMessage(message);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(bodys);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionHandlerBody> handleIllegalArgumentException(IllegalArgumentException ex) {

        String message = ex.getMessage();
        ExceptionHandlerBody bodys = new ExceptionHandlerBody();
        bodys.SetErrorMessage(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodys);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionHandlerBody> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        ExceptionHandlerBody bodys = new ExceptionHandlerBody();
        bodys.SetErrorMessage(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodys);
    }
}
