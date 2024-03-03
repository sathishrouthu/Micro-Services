package com.sathish.employeeservice.service;

import com.sathish.employeeservice.dto.APIResponseDto;
import com.sathish.employeeservice.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> getAllEmployees();
    APIResponseDto getEmployee(Long id);
}
