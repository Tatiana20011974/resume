package com.example.demo.restControllers;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.model.Employee;
import com.example.demo.services.EmployeeServices;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeServices service;
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        try {
            EmployeeDto Employee = service.getEmployeeById(id);
            return ResponseEntity.ok(Employee);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee() {
        List<EmployeeDto> employee = service.getAllEmployee();
        if (employee.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(employee);
        }
    }
    @GetMapping("/by-name")
    public ResponseEntity<EmployeeDto> getEmployeeByName(@RequestParam String name){
        try {
            EmployeeDto Employee = service.getEmployeeByName(name);
            return ResponseEntity.ok(Employee);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-email")
    public ResponseEntity<EmployeeDto> getEmployeeByEmail(@RequestParam String email){
//        if (email == null || email.trim().isEmpty() || !isValidEmail(email)) {
//            return ResponseEntity.badRequest().build();
//        }
        try {
            EmployeeDto Employee = service.getEmployeeByEmail(email);
            return ResponseEntity.ok(Employee);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

//    public ResponseEntity<Employee> isValidEmail(@RequestParam String email){
//        try {
//            EmployeeDto Employee = service.getEmployeeByEmail(String email);
//            return ResponseEntity.ok(Employee);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
}
