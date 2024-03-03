package com.sathish.departmentservice.controller;

import com.sathish.departmentservice.dto.DepartmentDto;
import com.sathish.departmentservice.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartmentDtoResponseEntity(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
        List<DepartmentDto> departmentDtoList=departmentService.getAllDepartments();
        return new ResponseEntity<>(departmentDtoList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("id") Long id){
        DepartmentDto departmentDto = departmentService.getDepartment(id);
        return new ResponseEntity<>(departmentDto,HttpStatus.OK);
    }
}
