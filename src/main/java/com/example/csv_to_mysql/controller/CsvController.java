package com.example.csv_to_mysql.controller;

import com.example.csv_to_mysql.service.CsvService;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

@RestController
public class CsvController {

    @Autowired
    private CsvService csvService;

    @PostMapping("/upload-csv")
    public String uploadCSV(@RequestParam("file") String filePath) {
        try {
            csvService.processCsv(filePath);
            return "CSV processed successfully.";
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            return "Failed to process CSV: " + e.getMessage();
        }
    }
}
