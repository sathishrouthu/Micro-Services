package com.sathish.departmentservice.exception;

public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
