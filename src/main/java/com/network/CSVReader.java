package com.network;

import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public String getUserInfo(int index) throws IOException, CsvValidationException {

        com.opencsv.CSVReader reader = new CSVReaderBuilder(new FileReader("src/main/resources/login.csv")).build();
        String[] nextLine;

        String username = null;
        String password = null;

        while ((nextLine = reader.readNext()) != null) {
            username = nextLine[0];
            password = nextLine[1];
        }

        return index == 0 ? username : password;
    }
}