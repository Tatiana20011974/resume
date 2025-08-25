package com.example.demo.request;

import com.example.demo.model.FormatFiles;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MailRequest {
    @NotBlank
    @Email
    @Schema(description = "Email адрес для отправки файла",
            example = "glucharik@mail.ru",
            defaultValue = "glucharik@mail.ru")
    private String mailAddress = "glucharik@mail.ru";

    @Schema(description = "Формат файла",
            example = "DOCX",
            defaultValue = "DOCX")
    private FormatFiles formatFiles = FormatFiles.DOCX;
}
