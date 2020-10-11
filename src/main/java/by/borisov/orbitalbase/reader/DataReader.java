package by.borisov.orbitalbase.reader;

import by.borisov.orbitalbase.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DataReader {
    static Logger logger = LogManager.getLogger();

    public List<String> readData(String filePath) throws CustomException {
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(filePath)).getFile());

        List<String> lines;
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            lines = bufferedReader.lines().collect(Collectors.toList());
            logger.info("File was read.");
        } catch (IOException e) {
            logger.error("Cannot read file.", e);
            throw new CustomException("Cannot read file.", e);
        }
        return lines;
    }
}