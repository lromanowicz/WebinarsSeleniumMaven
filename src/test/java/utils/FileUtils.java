package utils;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class FileUtils {

    public static String getResourceFilePath(String fileName) {
        // Utworzenie obiektu file
        File file = null;
        try {
            // Przypisanie do obiektu file pliku z katalogu resources
            file = Paths.get(FileUtils.class.getClassLoader().getResource(fileName).toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        // Zwrócenie ścieżki pliku
        return file.getAbsolutePath();
    }
}
