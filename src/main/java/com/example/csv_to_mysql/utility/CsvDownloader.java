package com.example.csv_to_mysql.utility;

import org.apache.hc.client5.http.HttpResponseException;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.FileOutputStream;
import java.io.IOException;

public class CsvDownloader {

    public static void downloadCsv(String api, String date, String filePath) throws IOException {
        String url = "http://sipix.bdix.net/mnp/";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(url);
            post.addHeader("Content-Type", "application/x-www-form-urlencoded");

            StringEntity entity = new StringEntity("api=" + api + "&date=" + date + "&type=csv");
            post.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(post)) {
                if (response.getCode() != 200) {
                    throw new HttpResponseException(response.getCode(), "Failed to download CSV");
                }

                byte[] content = EntityUtils.toByteArray(response.getEntity());
                try (FileOutputStream fos = new FileOutputStream(filePath)) {
                    fos.write(content);
                }
            }
        }
    }
}
