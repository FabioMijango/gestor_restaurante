package com.fabiomijango.gestor_restaurante.exception;

import com.fabiomijango.gestor_restaurante.exception.EntityException.EntityNotValidException;
import com.fabiomijango.gestor_restaurante.util.GenericResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

import static com.fabiomijango.gestor_restaurante.util.GenericResponse.ErrorResponse;

@RestControllerAdvice
public class EntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<GenericResponse> handleEntityNotFoundException(EntityNotFoundException exception, HttpServletRequest request) {
        ErrorResponse errorResponse = buildErrorResponse(request);
        errorResponse.setErrors(
                Map.of(
                        "error", exception.getMessage()
                )
        );

        return GenericResponse.builder()
                .message("Entity not found")
                .status(HttpStatus.NOT_FOUND)
                .data(errorResponse)
                .build().buildResponse();
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<GenericResponse> handleEntityExistsException(EntityExistsException exception, HttpServletRequest request) {
        ErrorResponse errorResponse = buildErrorResponse(request);
        errorResponse.setErrors(
                Map.of(
                        "error", exception.getMessage()
                )
        );

        return GenericResponse.builder()
                .message("Entity already exists")
                .status(HttpStatus.CONFLICT)
                .data(errorResponse)
                .build().buildResponse();
    }

    @ExceptionHandler(EntityNotValidException.class)
    public ResponseEntity<GenericResponse> handleEntityNotValidException(EntityNotValidException exception, HttpServletRequest request){
        ErrorResponse errorResponse = buildErrorResponse(request);
        errorResponse.setErrors(
                Map.of(
                        "error", exception.getMessage()
                )
        );

        return GenericResponse.builder()
                .message("Entity not valid")
                .status(HttpStatus.BAD_REQUEST)
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
