package com.example.demoApi.exception;


import com.example.demoApi.dto.ErrorDTO;
import com.example.demoApi.enums.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ErrorDTO> duplicateResource(ProductException e, WebRequest request) {
        return ResponseEntity.status(e.getStatus())
                .body(new ErrorDTO(e.getDescription(), e.getReasons()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        List<String> reasons = new ArrayList<>();
        for(FieldError error : ex.getBindingResult().getFieldErrors()) {
            reasons.add(String.format("%s - %s", error.getField(), error.getDefaultMessage().concat(error.getField())));
        }
        return ResponseEntity.status(ApiError.VALIDATION_ERROR.getHttpStatus())
                .body(new ErrorDTO(ApiError.VALIDATION_ERROR.getMessage(), reasons));
    }

}
