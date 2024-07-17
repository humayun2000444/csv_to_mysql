package com.example.csv_to_mysql.controller;

import com.example.csv_to_mysql.service.CsvService;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

@RestController
public class CsvController {

    @Autowired
    private CsvService csvService;

    @GetMapping("/process-csv")
    public String processCsvFile() {
        String filePath = "C:/Users/Humayun/Desktop/2024-07-11.csv";
        try {
            csvService.processCsv(filePath);
            return "CSV file processed successfully.";
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            return "Failed to process CSV file: " + e.getMessage();
        }
    }
}
