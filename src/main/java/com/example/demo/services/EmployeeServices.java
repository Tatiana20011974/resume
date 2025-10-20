package com.example.demo.services;


import com.example.demo.SpringDataRepository.EmployeeRepository;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.mappers.EmployeeMapper;
import com.example.demo.model.Employee;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServices {
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    @Cacheable(value = "employees", key = "#id")
    public EmployeeDto getEmployeeById(Long id){
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        return mapper.toDto(employee);
    }

    @Cacheable(value = "allEmployees")
    public List<EmployeeDto> getAllEmployee(){
        List<Employee> employees = repository.findAll();
        employees.forEach(System.out::println);
        return mapper.toDto(employees);
    }

    @Cacheable(value = "employees", key = "#name")
    public EmployeeDto getEmployeeByName(String name){
        Employee employee = repository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        return mapper.toDto(employee);
    }

    @Cacheable(value = "employees", key = "#email")
    public EmployeeDto getEmployeeByEmail(String mail) {
        Employee employee = repository.findByMail(mail)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        return mapper.toDto(employee);
    }
    @Transactional
    @CacheEvict(value = "allEmployees", allEntries = true)
    public EmployeeDto save(EmployeeDto dto){
        var saved = repository.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Caching(evict = {
            @CacheEvict(value = "allEmployees", allEntries = true)
    })
    public void deleteAll(){
        repository.deleteAll();
    }
}
