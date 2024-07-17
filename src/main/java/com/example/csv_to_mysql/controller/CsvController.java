package com.example.csv_to_mysql.controller;

import com.example.csv_to_mysql.service.CsvService;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class CsvController {
    @Autowired
    private CsvService csvService;

    @PostMapping("/upload-csv")
    public String uploadCsv(@RequestParam("file") MultipartFile file) {
        try {
            // Save the file locally
            File tempFile = File.createTempFile("upload", ".csv");
            file.transferTo(tempFile);

            // Process the CSV
            csvService.processCsv(tempFile.getAbsolutePath());

            // Delete the temp file
            tempFile.delete();

            return "CSV processed successfully";
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            return "Error processing CSV: " + e.getMessage();
        }
    }
}
