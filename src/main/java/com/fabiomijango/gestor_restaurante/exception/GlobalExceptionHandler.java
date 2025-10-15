package com.fabiomijango.gestor_restaurante.exception;


import com.fabiomijango.gestor_restaurante.util.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

import static com.fabiomijango.gestor_restaurante.util.GenericResponse.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse> handleMethodArgumentTypeMismatchException(MethodArgumentNotValidException exception, HttpServletRequest request) {

        ErrorResponse errorResponse = buildErrorResponse(request);
        errorResponse.setErrors(
                exception.getBindingResult().getFieldErrors().stream()
                .collect(
                        Collectors.toMap(
                                FieldError::getField,
                                FieldError::getDefaultMessage,
                                (existingValue, newValue) -> existingValue + ". " + newValue
                        )
                ));

        return GenericResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Validation error")
                .data(errorResponse)
                .build().buildResponse();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GenericResponse> handleConstraintViolationException(ConstraintViolationException exception, HttpServletRequest request){

        ErrorResponse errorResponse = buildErrorResponse(request);
        errorResponse.setErrors(
                exception.getConstraintViolations().stream().collect(
                        Collectors.toMap(
                                violation -> violation.getPropertyPath().toString().split("\\.")[1],
                                violation -> violation.getMessage(),
                                (existingValue, newValue) -> existingValue + ". " + newValue
                        )
                )
        );

        return GenericResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Validation error")
                .data(errorResponse)
                .build().buildResponse();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<GenericResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception, HttpServletRequest request) {

        String msgError = "";
        if(exception.getMostSpecificCause().getMessage().contains("Required request body is missing")){
            msgError = "Request body is missing";
        }else {
            msgError = exception.getMostSpecificCause().getMessage().split("\n")[0];
        }
        ErrorResponse errorResponse = buildErrorResponse(request);
        errorResponse.setErrors(
                Map.of(
                        "cause",
                        msgError
                )
        );

        return GenericResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Malformed JSON request")
                .data(errorResponse)
                .build().buildResponse();
    }

    private ErrorResponse buildErrorResponse(HttpServletRequest request){
        return ErrorResponse.builder()
                .path(request.getRequestURI())
                .method(request.getMethod())
                .build().buildResponse();
    }

}
