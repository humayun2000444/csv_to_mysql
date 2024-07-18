package com.example.csv_to_mysql;

import com.example.csv_to_mysql.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@EnableScheduling
public class CsvToMysqlApplication implements CommandLineRunner {

    @Autowired
    private CsvService csvService;

    private final String api = "b6r54srkfe51liw542fdvkgb7syoh9w0z";

    public static void main(String[] args) {
        SpringApplication.run(CsvToMysqlApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // Initial run logic if needed
        String date = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String filePath = "C:/Users/Humayun/Desktop/" + date + ".csv";
        processCsvFile(api, date, filePath);
    }

    @Scheduled(fixedRate = 3600) // Run every hour (3600000 ms)
    public void scheduledCsvProcessing() {
        String date = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String filePath = "C:/Users/Humayun/Desktop/" + date + ".csv";
        processCsvFile(api, date, filePath);
    }

    private void processCsvFile(String api, String date, String filePath) {
        try {
            csvService.downloadAndProcessCsv(api, date, filePath);
            System.out.println("CSV file processed successfully.");
        } catch (IOException | CsvValidationException | ParseException e) {
            e.printStackTrace();
            System.out.println("Failed to process CSV file: " + e.getMessage());
        }
    }
}
