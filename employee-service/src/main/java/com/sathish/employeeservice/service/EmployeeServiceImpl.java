package com.sathish.employeeservice.service;

import com.sathish.employeeservice.dto.APIResponseDto;
import com.sathish.employeeservice.dto.DepartmentDto;
import com.sathish.employeeservice.dto.EmployeeDto;
import com.sathish.employeeservice.entity.Employee;
import com.sathish.employeeservice.exception.EmployeeNotFoundException;
import com.sathish.employeeservice.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
//    private RestTemplate restTemplate;
//    private WebClient webClient;
    private  APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto,Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = modelMapper.map(savedEmployee,EmployeeDto.class);
        return  savedEmployeeDto;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for(Employee dept : employeeList){
            EmployeeDto employeeDto=modelMapper.map(dept,EmployeeDto.class);
            employeeDtoList.add(employeeDto);
        }
        return employeeDtoList;
    }

    @Override
    public APIResponseDto getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new EmployeeNotFoundException("Employee with id: "+id+" Not Found."));
//        ResponseEntity<DepartmentDto> responseEntity =  restTemplate.getForEntity("http://localhost:8080/api/departments/"+employee.getDepartmentId(), DepartmentDto.class);
//        DepartmentDto departmentDto = responseEntity.getBody();
//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/departments/"+employee.getDepartmentId())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();
        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentId());
        EmployeeDto employeeDto = modelMapper.map(employee,EmployeeDto.class);
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        return  apiResponseDto;
    }
}
