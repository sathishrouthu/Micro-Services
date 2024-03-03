package com.sathish.departmentservice.service;

import com.sathish.departmentservice.dto.DepartmentDto;
import com.sathish.departmentservice.entity.Department;
import com.sathish.departmentservice.exception.DepartmentNotFoundException;
import com.sathish.departmentservice.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.beans.DefaultPersistenceDelegate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements  DepartmentService{
    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto,Department.class);
        Department savedDepartment = departmentRepository.save(department);
        DepartmentDto savedDepartmentDto = modelMapper.map(savedDepartment,DepartmentDto.class);
        return  savedDepartmentDto;
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departmentList = departmentRepository.findAll();
        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        for(Department dept : departmentList){
            DepartmentDto departmentDto=modelMapper.map(dept,DepartmentDto.class);
            departmentDtoList.add(departmentDto);
        }
        return departmentDtoList;
    }

    @Override
    public DepartmentDto getDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(()->new DepartmentNotFoundException("Department with id: "+id+" is Not Found."));
        DepartmentDto departmentDto = modelMapper.map(department,DepartmentDto.class);
        return  departmentDto;
    }
}
