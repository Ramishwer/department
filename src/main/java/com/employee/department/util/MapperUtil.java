package com.employee.department.util;

import com.employee.department.Dto.DepartmentDto;
import com.employee.department.Dto.EmployeeDto;
import com.employee.department.entity.Department;
import com.employee.department.entity.Employee;

public class MapperUtil {

    public static DepartmentDto toDto(Department d) {
        if(d==null) return null;

        return new DepartmentDto(d.getId(),d.getName());
    }

    public static EmployeeDto toDto(Employee e) {
        if(e==null) return null;

        EmployeeDto dto = new EmployeeDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setEmail(e.getEmail());
        dto.setSalary(e.getSalary());
        dto.setDepartment(toDto(e.getDepartment()));

        return dto;
    }
}
