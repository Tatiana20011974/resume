package com.example.demo.services;


import com.example.demo.SpringDataRepository.EmployeeRepository;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.ProjectDto;
import com.example.demo.mappers.EmployeeMapper;
import com.example.demo.model.Employee;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServices {
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    public EmployeeDto getEmployeeById(Long id){
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        return mapper.toDto(employee);
    }
    public List<EmployeeDto> getAllEmployee(){
        List<Employee> employees = repository.findAll();
        employees.forEach(System.out::println);
        return mapper.toDto(employees);
    }
    public EmployeeDto getEmployeeByName(String name){
        Employee employee = repository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        return mapper.toDto(employee);
    }
    public EmployeeDto getEmployeeByEmail(String mail) {
        Employee employee = repository.findByMail(mail)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        return mapper.toDto(employee);
    }
}
