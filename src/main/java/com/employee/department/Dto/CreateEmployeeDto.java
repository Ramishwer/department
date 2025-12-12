package com.employee.department.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateEmployeeDto {

    @NotBlank @Email (message = "Email required")
    private String email;

    @NotBlank(message = "name required")
    private String name;

    @PositiveOrZero(message = "salary must be >=0")
    private BigDecimal salary;

    private Long departmentId;

}
