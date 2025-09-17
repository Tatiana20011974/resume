package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
public class ProjectDto {
    private Long id;
    private String name;
    private String description;
}
