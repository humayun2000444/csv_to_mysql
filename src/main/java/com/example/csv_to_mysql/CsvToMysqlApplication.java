package com.example.csv_to_mysql;

import com.example.csv_to_mysql.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;

@SpringBootApplication
public class CsvToMysqlApplication implements CommandLineRunner {

    @Autowired
    private CsvService csvService;

    public static void main(String[] args) {
        SpringApplication.run(CsvToMysqlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String filePath = "C:/Users/Humayun/Desktop/2024-07-14.csv";
        try {
            csvService.processCsv(filePath);
            System.out.println("CSV file processed successfully.");
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            System.out.println("Failed to process CSV file: " + e.getMessage());
        }
    }
}
