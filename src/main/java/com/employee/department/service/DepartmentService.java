package com.employee.department.service;

import com.employee.department.Dto.CreateDepartmentDto;
import com.employee.department.Dto.DepartmentDto;
import com.employee.department.Repo.DepartmentRepo;
import com.employee.department.entity.Department;
import com.employee.department.exception.ResourceNotFoundException;
import com.employee.department.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepo departmentRepo;

    public DepartmentDto create(CreateDepartmentDto dto) {
        Department d= new Department(dto.getName());

        Department saved = departmentRepo.save(d);

        return MapperUtil.toDto(saved);
    }

    public List<DepartmentDto> listAll() {
        return departmentRepo.findAll().stream().map(MapperUtil::toDto).collect(Collectors.toList());
    }

    public DepartmentDto update(Long id,CreateDepartmentDto dto) {
        Department d= departmentRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Department not found with id"+ id));
        d.setName(dto.getName());

        return MapperUtil.toDto(departmentRepo.save(d));
    }

    public void delete(Long id) {
        Department d= departmentRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Department not found with id"+id));
    }

    public Department getEntityById(Long id) {
        return departmentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department not found with id"+id));

    }
}
