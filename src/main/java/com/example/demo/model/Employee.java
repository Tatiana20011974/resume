package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Builder

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee", schema = "public")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String image;

    private String name;

    private long telephon;

    private String mail;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<com.example.demo.model.Education> education;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Project> project;

//    @ElementCollection
//    @CollectionTable(name = "skills", joinColumns = @JoinColumn(name = "id_employee"))
//    private List<Skill> skill;

//    @Column(name = "english_level")
//    @Enumerated(EnumType.ORDINAL)
//    private EnglishLevel englishlevel;
}