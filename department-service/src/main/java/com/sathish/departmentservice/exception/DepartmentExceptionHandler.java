package com.sathish.departmentservice.exception;

import com.sathish.departmentservice.dto.DepartmentErrorResponse;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class DepartmentExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<DepartmentErrorResponse> handleDepartmentException(DepartmentNotFoundException exception){
        DepartmentErrorResponse errorResponse = new DepartmentErrorResponse(LocalDate.now().toString(),exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
