package com.example.demo.dto;

import lombok.*;

@Builder
@Data
//@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private String image;

    private String name;

    private long telephon;

    private String mail;
}
