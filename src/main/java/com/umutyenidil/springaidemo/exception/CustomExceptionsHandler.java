package com.umutyenidil.springaidemo.exception;

import com.umutyenidil.springaidemo.dto.response.ErrorDetail;
import com.umutyenidil.springaidemo.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class CustomExceptionsHandler {

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerException(
            InternalServerException ex,
            HttpServletRequest request) {

        ErrorResponse errorResponse = ErrorResponse.of(
                ErrorDetail.builder()
                        .type(ErrorDetail.ErrorType.SERVER)
                        .subject(ErrorDetail.ErrorSubject.SERVER)
                        .message("Something went wrong on the server, please try again.")
                        .build()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
