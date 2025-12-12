package com.employee.department.service;

import com.employee.department.Dto.CreateDepartmentDto;
import com.employee.department.Dto.CreateEmployeeDto;
import com.employee.department.Dto.EmployeeDto;
import com.employee.department.Repo.EmployeeRepo;
import com.employee.department.entity.Department;
import com.employee.department.entity.Employee;
import com.employee.department.exception.ResourceNotFoundException;
import com.employee.department.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {


    private final EmployeeRepo employeeRepo;

    private final DepartmentService departmentService;


    public EmployeeDto create(CreateEmployeeDto dto) {
        Department dept=null;

        if(dto.getDepartmentId()!=null) {
            dept= departmentService.getEntityById(dto.getDepartmentId());
        }

        Employee e=new Employee(dto.getName(),dto.getEmail(),dto.getSalary());
        e.setDepartment(dept);

        Employee saved = employeeRepo.save(e);

        return MapperUtil.toDto(saved);


    }

    public EmployeeDto getById(Long id) {
        Employee e= employeeRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found with id"+id));
        return MapperUtil.toDto(e);
    }

    public Page<EmployeeDto> listAll(int page,int size,Long departmentId) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("id").ascending());

        Page<Employee> p;

        if(departmentId!=null) {
            p=employeeRepo.findByDepartmentId(departmentId,pageable);
        }else {
            p= employeeRepo.findAll(pageable);
        }

        return p.map(MapperUtil::toDto);
    }

    public EmployeeDto update(Long id,CreateEmployeeDto dto) {
        Employee e = employeeRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found with id"+id));

        e.setName(dto.getName());
        e.setEmail(dto.getEmail());
        e.setSalary(dto.getSalary());

        if(dto.getDepartmentId()!=null) {
            Department d= departmentService.getEntityById(dto.getDepartmentId());
            e.setDepartment(d);
        }else {
            e.setDepartment(null);
        }

        return MapperUtil.toDto(employeeRepo.save(e));
    }

    public void delete(Long id) {
        Employee e= employeeRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found with id"+id));

        employeeRepo.delete(e);
    }
}
