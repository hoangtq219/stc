package stc.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author : hoangtq
 * @since : 22:09 14/09/2020, Mon
 **/
public class FileHelper {

    public static File initPathToFile(String pathToFile) {
        Path path = Paths.get(pathToFile);
        if (!path.toFile().exists()) {
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path.toFile();
    }

    public static List<String> readLines(String pathToFile) {
        File file = initPathToFile(pathToFile);
        List<String> testCases = new ArrayList<>();
        // auto close with any exception
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String inLine;
            while ((inLine = reader.readLine()) != null) {
                if (inLine.length() > 0) {
                    testCases.add(inLine.trim());
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return testCases;
    }

}
