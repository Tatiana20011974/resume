package com.example.demo;

import com.example.demo.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ResumeRepository implements RepositoryPort {
    List<Employee> repository = new ArrayList();
    {
        Employee employee1 = Employee
                .builder()
                .id(1L)
                .name("Aleksandr")
                .telephon(325297773355L)
                .mail("aleks1@mail.ru")
                .build();
        repository.add(employee1);
        Employee employee2 = Employee
                .builder()
                .id(2L)
                .name("Jone")
                .telephon(325297773344L)
                .mail("jone2@mail.ru")
                .build();
        repository.add(employee2);
        Employee employee3 = Employee
                .builder()
                .id(3L)
                .name("Oleg")
                .telephon(325297772233L)
                .mail("oleg3@mail.ru")
                .build();
        repository.add(employee3);
    }
    @Override
    public List<Employee> findAll() {
        return repository;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return repository.stream().filter(e->e.getId() == id).findFirst();
    }

    @Override
    public Employee save(Employee t) {
        repository.remove( repository.stream().filter(e->e.getId() == t.getId()).findFirst());
        repository.add(t);
        return t;
    }

    @Override
    public void deleteById(Long id) {
        repository.remove(repository.stream().filter(e->e.getId() == id).findFirst());
    }
}
