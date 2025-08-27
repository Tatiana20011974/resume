package com.example.demo.services.fileFabrica;

import com.example.demo.model.FormatFiles;

public class FileGenerateFactory {
    public static FileGenerator getFileGenerator(FormatFiles formatFiles) {
        return switch (formatFiles) {
            case XLSX -> new XlsxFileGenerator();
            case DOCX -> new DocxFileGenerator();
        };
    }
}
