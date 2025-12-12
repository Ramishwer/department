package com.employee.department.Dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeeDto {

    private Long id;
    private String name;
    private String email;
    private BigDecimal salary;
    private DepartmentDto department;
}
