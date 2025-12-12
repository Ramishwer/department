package com.employee.department.Dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class DepartmentDto {
    private Long id;
    private String name;

    public DepartmentDto(Long id, String name) {
    }
}
