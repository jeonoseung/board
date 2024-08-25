package com.project.board;

import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

class SetBody {
    public String errorMessage = "에러가 발생했습니다.";
    public void SetErrorMessage(String message){
        this.errorMessage = message;
    }
}

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<SetBody> handleIllegalArgumentException(IllegalArgumentException ex) {

        String message = ex.getMessage();
        SetBody bodys = new SetBody();
        bodys.SetErrorMessage(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodys);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<SetBody> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        SetBody bodys = new SetBody();
        bodys.SetErrorMessage(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodys);
    }
}
