package com.example.csv_to_mysql.service;

import com.example.csv_to_mysql.entity.TbMnp;
import com.example.csv_to_mysql.repository.TbMnpRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

@Service
public class CsvService {

    private static final Logger logger = Logger.getLogger(CsvService.class.getName());

    @Autowired
    private TbMnpRepository repository;

    @Transactional(rollbackFor = Exception.class)
    public void processCsv(String filePath) throws IOException, CsvValidationException {
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] values;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

            // Skip the header row
            csvReader.readNext();

            while ((values = csvReader.readNext()) != null) {
                String number = values[0];
                String portedDateStr = values[1];
                int recipientRC = Integer.parseInt(values[2]);
                int donorRC = Integer.parseInt(values[3]);
                int nrhRC = Integer.parseInt(values[4]);
                String numberType = values[5];
                String action = values[6].trim().toUpperCase();

                TbMnp tbMnp = new TbMnp();
                tbMnp.setNumber(number);
                try {
                    tbMnp.setPortedDate(sdf.parse(portedDateStr));
                } catch (ParseException e) {
                    logger.severe("Failed to parse date for number " + number + ": " + e.getMessage());
                    continue; // Skip this record and move to the next one
                }
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
                            logger.warning("Cannot update, record does not exist for number: " + number);
                        }
                        break;
                    case "DELETE":
                        if (repository.existsById(number)) {
                            repository.deleteByNumber(number);
                        } else {
                            logger.warning("Cannot delete, record does not exist for number: " + number);
                        }
                        break;
                    default:
                        logger.warning("Unknown action: " + action);
                }
            }
        } catch (IOException | CsvValidationException e) {
            logger.severe("Error processing CSV file: " + e.getMessage());
            throw e; // Rethrow the exception to be caught by the controller or upper layer
        }
    }
}
