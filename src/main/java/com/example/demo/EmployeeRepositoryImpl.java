package com.example.demo;

import com.example.demo.model.Education;
import com.example.demo.model.Employee;
import com.example.demo.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.cfg.Configuration;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class EmployeeRepositoryImpl implements RepositoryPort {

    private SessionFactory sessionFactory;

    EmployeeRepositoryImpl() {

        try {
            sessionFactory = new Configuration()
                    .configure()
                    .addAnnotatedClass(Employee.class)
                    .addAnnotatedClass(Project.class)
                    .addAnnotatedClass(Education.class)
                    .buildSessionFactory();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public List<Employee> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT e FROM Employee e", Employee.class).getResultList(); // Запрос на получение всех записей
        }
    }

    @Override
    public Optional<Employee> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Employee.class, id)); // Получаем запись по ID
        }
    }

    @Override
    public Employee save(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction(); // Начинаем транзакцию
            session.merge(employee); // Сохраняем или обновляем запись
            transaction.commit(); // Завершаем транзакцию
            return employee;
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            System.out.println("*******delete********");
            session.beginTransaction();
            Employee employee = session.get(Employee.class, id); // Находим запись по ID
            if (employee != null) {
                session.remove(employee); // Удаляем запись
            }
            session.getTransaction().commit();
        }
    }
}

