package com.example.csv_to_mysql.service;

import com.example.csv_to_mysql.entity.TbMnp;
import com.example.csv_to_mysql.repository.TbMnpRepository;
import com.example.csv_to_mysql.utility.CsvDownloader;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

@Service
public class CsvService {

    @Autowired
    private TbMnpRepository repository;

    public void downloadAndProcessCsv(String api, String date, String filePath) throws IOException, CsvValidationException, ParseException {
        // Download the CSV file using CsvDownloader utility
        CsvDownloader.downloadCsv(api, date, filePath);

        // Process the downloaded CSV file
        processCsv(filePath);
    }

    @Transactional
    public void processCsv(String filePath) throws IOException, CsvValidationException, ParseException {
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] values;

            // Skip the header row
            csvReader.readNext();

            while ((values = csvReader.readNext()) != null) {
                String number = String.format("%.0f", Double.parseDouble(values[0]));
                int recipientRC = Integer.parseInt(values[2]);
                int donorRC = Integer.parseInt(values[3]);
                int nrhRC = Integer.parseInt(values[4]);
                String numberType = values[5];
                String action = values[6].trim().toUpperCase();

                TbMnp tbMnp = new TbMnp();
                tbMnp.setNumber(number);
                tbMnp.setRecipientRC(recipientRC);
                tbMnp.setDonerRC(donorRC);
                tbMnp.setNrhRC(nrhRC);
                tbMnp.setNumberType(numberType);
                tbMnp.setPortedAction(action);

                switch (action) {
                    case "INSERT":
                        repository.save(tbMnp);
                        break;
                    case "UPDATE":
                        if (repository.existsById(number)) {
                            repository.save(tbMnp);
                        } else {
                            System.out.println("Cannot update, record does not exist for number: " + number);
                        }
                        break;
                    case "DELETE":
                        if (repository.existsById(number)) {
                            repository.deleteByNumber(number);
                        } else {
                            System.out.println("Cannot delete, record does not exist for number: " + number);
                        }
                        break;
                    default:
                        System.out.println("Unknown action: " + action);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // Rethrow the exception to be caught by the controller
        }
    }
}
