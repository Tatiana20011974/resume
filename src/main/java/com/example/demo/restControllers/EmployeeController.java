package com.example.demo.restControllers;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.model.Employee;
import com.example.demo.model.FormatFiles;
import com.example.demo.request.MailRequest;
import com.example.demo.services.EmployeeServices;
import com.example.demo.services.MailSenderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeServices service;
    private final MailSenderService mailSenderService;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        try {
            EmployeeDto Employee = service.getEmployeeById(id);
            return ResponseEntity.ok(Employee);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee() {
        List<EmployeeDto> employee = service.getAllEmployee();
        if (employee.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(employee);
        }
    }
    @GetMapping("/by-name")
    public ResponseEntity<EmployeeDto> getEmployeeByName(@RequestParam String name){
        try {
            EmployeeDto Employee = service.getEmployeeByName(name);
            return ResponseEntity.ok(Employee);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-email")
    public ResponseEntity<EmployeeDto> getEmployeeByEmail(@RequestParam String email){
//        if (email == null || email.trim().isEmpty() || !isValidEmail(email)) {
//            return ResponseEntity.badRequest().build();
//        }
        try {
            EmployeeDto Employee = service.getEmployeeByEmail(email);
            return ResponseEntity.ok(Employee);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/mail")
    public ResponseEntity<String> sayHello() {
        mailSenderService.send(
                "glucharik@mail.ru",
                "Hello my friend",
                "Ку-ку!"
        );
        return ResponseEntity.ok("Хочет работать");
    }

    @PostMapping("/{id}/xls")
    public ResponseEntity<String> createXLSFile(@PathVariable Long id){
        EmployeeDto employee = service.getEmployeeById(id);
        if (employee != null) {
            mailSenderService.createXLSFile(employee);
            return ResponseEntity.ok("File" + employee.getId() + " created");
            }else{
                return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/doc")
    public ResponseEntity<String> createDOCFile(@PathVariable Long id){
        EmployeeDto employee = service.getEmployeeById(id);
        if (employee != null) {
            mailSenderService.createDOCFile(employee);
            return ResponseEntity.ok("File" + employee.getId() + " created");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/attachment")
    public ResponseEntity<String> sendEmailWithAttachment(@PathVariable Long id, @RequestBody @Valid MailRequest mailRequest){
        EmployeeDto employee = service.getEmployeeById(id);

            String file = switch (mailRequest.getFormatFiles()){
                case DOCX -> mailSenderService.createDOCFile(employee);
                case XLSX -> mailSenderService.createXLSFile(employee);
            };
            mailSenderService.sendMailWithAttachment(
                    mailRequest.getMailAddress(),
                    "Hello Hello Hello",
                    "Ля-Ля-Ля!",
                    file
            );
            return ResponseEntity.ok("File" + employee.getId() + " created");
    }

//    public ResponseEntity<Employee> isValidEmail(@RequestParam String email){
//        try {
//            EmployeeDto Employee = service.getEmployeeByEmail(String email);
//            return ResponseEntity.ok(Employee);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
}
