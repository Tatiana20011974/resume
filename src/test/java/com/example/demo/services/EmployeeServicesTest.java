package com.example.demo.services;
import com.example.demo.SpringDataRepository.EmployeeRepository;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.mappers.EmployeeMapper;
import com.example.demo.model.Employee;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServicesTest {
    @Mock
    private EmployeeRepository repository;
    @Mock
    private EmployeeMapper mapper;
    private final Long EMPLOYEE_ID = 1L;
    private final Employee EMPLOYEE = new Employee();
    private final EmployeeDto employeeDto = EmployeeDto.builder()
            .id(1L)
            .image("/images/image.jpg")
            .name("Tanya")
            .telephon(3742934435L)
            .mail("rsfhgg@mail.ru")
            .build();
    private final String EMPLOYEE_EMAIL = "Tata";
    @InjectMocks
    private EmployeeServices employeeServices;//(1,'Tanya','/images/image.jpg',3742934435,'rsfhgg@mail.ru' ),

    @Test
    public void getEmployeeByIdTest() {
       // EmployeeServices employeeServices = new EmployeeServices(repository, mapper);
        Mockito.when(repository.findById(EMPLOYEE_ID)).thenReturn(java.util.Optional.of(EMPLOYEE));
        Mockito.when(mapper.toDto(EMPLOYEE)).thenReturn(employeeDto);
        var rez=employeeServices.getEmployeeById(EMPLOYEE_ID);
        assertEquals(employeeDto, rez);
    }
    @Test
    public void getEmployeeByEmailTest() {
      //  EmployeeServices employeeServices = new EmployeeServices(repository, mapper);
        Mockito.when(repository.findByMail(EMPLOYEE_EMAIL)).thenReturn(java.util.Optional.of(EMPLOYEE));
        Mockito.when(mapper.toDto(EMPLOYEE)).thenReturn(employeeDto);
        var rez=employeeServices.getEmployeeByEmail(EMPLOYEE_EMAIL);
        assertEquals(employeeDto, rez);
    }
}