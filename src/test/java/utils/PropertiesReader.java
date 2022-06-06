package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    public Properties getConfigurationProperties(String file) {

        // Deklaracje objektu z propertisami
        Properties properties = new Properties();
        // Deklaracja inputStream
        InputStream inputStream = null;
        try {
            // Wczytanie pliku i przekazanie do inputstream
            inputStream = new FileInputStream(file);
            // Przekazanie inputStrem do propertis
            properties.load(inputStream);
        } catch (IOException e) {
            // Lapanie wyjątku
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    // zamukanie inputstream i pliku
                    inputStream.close();
                } catch (IOException e) {
                    // Lapanie wyjątku
                    e.printStackTrace();
                }
            }
        }
        // Zwracanie propertisów
        return properties;
    }
}