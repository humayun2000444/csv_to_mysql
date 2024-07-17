package com.example.csv_to_mysql.service;

import com.example.csv_to_mysql.entity.TbMnp;
import com.example.csv_to_mysql.repository.TbMnpRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

@Service
public class CsvService {
    @Autowired
    private TbMnpRepository repository;

    @Transactional
    public void processCsv(String filePath) throws IOException, CsvValidationException {
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] values;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while ((values = csvReader.readNext()) != null) {
                String number = values[0];
                String portedDateStr = values[1];
                int recipientRC = Integer.parseInt(values[2]);
                int donerRC = Integer.parseInt(values[3]);
                int nrhRC = Integer.parseInt(values[4]);
                String numberType = values[5];
                String action = values[6].trim().toUpperCase();

                TbMnp tbMnp = new TbMnp();
                tbMnp.setNumber(number);
                tbMnp.setPortedDate(sdf.parse(portedDateStr));
                tbMnp.setRecipientRC(recipientRC);
                tbMnp.setDonerRC(donerRC);
                tbMnp.setNrhRC(nrhRC);
                tbMnp.setNumberType(numberType);
                tbMnp.setPortedAction(action);

                switch (action) {
                    case "INSERT":
                        repository.save(tbMnp);
                        break;
                    case "UPDATE":
                        repository.save(tbMnp);
                        break;
                    case "DELETE":
                        repository.deleteByNumber(number);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown action: " + action);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
