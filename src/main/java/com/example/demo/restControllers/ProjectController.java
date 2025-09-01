package com.example.demo.restControllers;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.ProjectDto;
import com.example.demo.request.CreateProjectRequest;
import com.example.demo.services.ProjectServices;
import com.example.demo.services.rabbitMQ.AmqpProducerService1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Проекты")
@RestController
@Controller
@AllArgsConstructor
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectServices service;
    private final AmqpProducerService1 amqpProducerService1;

    @Operation(summary = "Получить имя по иднетификвтору",
            description = "Достает из базы данных и преобразует в ДТО данные об employee")

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        try {
            ProjectDto project = service.getProjectById(id);
            return ResponseEntity.ok(project);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> projects = service.getAllProject();
        if (projects.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(projects);
        }
    }
    @PostMapping
    public ResponseEntity<ProjectDto> createProject(
            @Parameter(description = "Данные нового проекта", required = true)
            @RequestBody  @Valid CreateProjectRequest request
    ) {
        try {
            ProjectDto createProject = service.createProject(request);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createProject.getId())
                    .toUri();
            return ResponseEntity.created(location).body(createProject);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/{id}/rabbit-email")
    public ResponseEntity<String> sendEmailToRabbit(@PathVariable Long id){
        ProjectDto projectDto = service.getProjectById(id);
        amqpProducerService1.sendMessage(projectDto);
        return ResponseEntity.ok("Email about " + projectDto);
    }
}
