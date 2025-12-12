package com.employee.department.controller;

import com.employee.department.Dto.CreateEmployeeDto;
import com.employee.department.Dto.EmployeeDto;
import com.employee.department.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> create(@RequestBody CreateEmployeeDto dto) {
         EmployeeDto created= employeeService.create(dto);
         return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @GetMapping("/list/employees")
    public Page<EmployeeDto> EmployeeList(@RequestParam(defaultValue = "10") int page, @RequestParam(defaultValue = "10")
                                          int size, @RequestParam(required = false) Long departmentId){
        return employeeService.listAll(page,size,departmentId);
    }

    @PutMapping("/update/{id}")
    public EmployeeDto update(@PathVariable Long id,@RequestBody CreateEmployeeDto dto) {
        return employeeService.update(id,dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
