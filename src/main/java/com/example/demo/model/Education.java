package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="education", schema="public")

public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (name= "yearstart")
    private int yearStart;
    @Column (name= "yearend")
    private int yearEnd;
    @Column (name= "nameeducation")
    private String nameEducation;
    private String degree;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee")
    private Employee employee;

}