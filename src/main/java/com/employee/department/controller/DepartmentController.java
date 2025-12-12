package com.employee.department.controller;

import com.employee.department.Dto.CreateDepartmentDto;
import com.employee.department.Dto.DepartmentDto;
import com.employee.department.entity.Department;
import com.employee.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<DepartmentDto> create(@RequestBody CreateDepartmentDto dto) {
        DepartmentDto created = departmentService.create(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public Department get(@PathVariable Long id) {
        return  departmentService.getEntityById(id);
    }

    @GetMapping("/list/departments")
    public ResponseEntity<List<DepartmentDto>> listAllDepartment() {
        return ResponseEntity.ok(departmentService.listAll());
    }

    @PutMapping("/update/{id}")
    public DepartmentDto update(@PathVariable Long id,@RequestBody CreateDepartmentDto dto){
        return departmentService.update(id,dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
         departmentService.delete(id);

         return ResponseEntity.noContent().build();
    }
}
