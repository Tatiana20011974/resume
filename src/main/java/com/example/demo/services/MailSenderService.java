package com.example.demo.services;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.model.Employee;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MailSenderService {
    public final String FOLDER_PATH = "D:\\IDEA";
    @Value("${spring.mail.username}")
    private String from;
    private final MailSender mailSender;
    private final JavaMailSender javaMailSender;

    public void send(String to, String subject, String body){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        mailMessage.setFrom(from);
        mailSender.send(mailMessage);
    }
    public String createXLSFile(EmployeeDto employee){
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("sheet1");
        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("Resume");

        Row row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue(employee.getName());

        Row row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("Phone");
        row2.createCell(1).setCellValue(employee.getTelephon());

        Row row3 = sheet.createRow(3);
        row3.createCell(0).setCellValue("Telegram");
        row3.createCell(1).setCellValue(employee.getTelephon());

        try {
            String file = "D:\\IDEA.xlsx";
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String createDOCFile(EmployeeDto employee) {
        XWPFDocument doc = new XWPFDocument();
        XWPFParagraph titleParagraph = doc.createParagraph();
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun titleRun = titleParagraph.createRun();
        titleRun.setText("Resume");
        titleRun.addBreak();
        titleRun.setText(employee.getName());

        titleRun.setBold(true);  // Жирный
        titleRun.setFontFamily("Times New Roman");
        titleRun.setFontSize(14);
        titleRun.setColor("0070C0"); // Цвет текста (синий)
        titleRun.addBreak(); // Переход на новую строку после запроса

        // Добавление основного текста
        XWPFParagraph paragraph1 = doc.createParagraph();
        XWPFRun run1 = paragraph1.createRun();
        run1.setText("Email: " + employee.getName());
        run1.addBreak();
        run1.setText("Telegram: " + employee.getTelephon());
        run1.setFontFamily("Times New Roman");
        run1.setFontSize(12);
        run1.addBreak(); // Добавляем разрыв строки
        XWPFRun run2 = paragraph1.createRun();
        run2.setText(employee.getName());

        try {
            String file = "D:\\IDEA.docx";
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            doc.write(fileOutputStream);
            fileOutputStream.close();
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendMailWithAttachment(String to, String subject, String text, String attachmentPath){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            // Используем помощник для создания сообщения
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            helper.setFrom(from);

            // Добавляем вложения
            FileSystemResource file = new FileSystemResource(new File(attachmentPath));
            helper.addAttachment(file.getFilename(), file);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
