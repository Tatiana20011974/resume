package com.example.demo.services;

import com.example.demo.SpringDataRepository.EmployeeRepository;
import com.example.demo.SpringDataRepository.ProjectRepository;
import com.example.demo.dto.ProjectDto;
import com.example.demo.mappers.ProjectMapper;
import com.example.demo.model.Employee;
import com.example.demo.model.Project;
import com.example.demo.request.CreateProjectRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServices {
    private final ProjectRepository repository;
    private final ProjectMapper mapper;
    private final EmployeeRepository employeeRepository;

    public ProjectDto getProjectById(Long id){
        Project project = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        return mapper.toDto(project);
    }
    public List<ProjectDto> getAllProject(){
        List<Project> projects = repository.findAll();
        projects.forEach(System.out::println);
        return mapper.toDto(projects);
    }
    @Transactional
    public ProjectDto createProject(CreateProjectRequest request){
        Employee employee= employeeRepository.findByName(request.getDeveloperName())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        Project project = Project.builder()
                .name(request.getName())
                .description(request.getDescription())
                .employee(employee)
                .build();
        var saved=repository.save(project);
        return mapper.toDto(saved);

    }

}
