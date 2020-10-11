package by.borisov.orbitalbase.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DataParser {
    static Logger logger = LogManager.getLogger();
    private static final String PARAM_DELIMITER = "\\s+";

    public DataParser() {
    }

    public List<List<Integer>> parseData(List<String> lines) {
        if (lines.isEmpty()) {
            logger.error("There is not data for parsing.");
        }

        List<List<Integer>> parametersList = new ArrayList<>();

        for (String line : lines) {
            List<Integer> parameters = parseLineToInteger(line);
            logger.info("Line is parsed.");
            parametersList.add(parameters);
        }
        return parametersList;
    }

    private List<Integer> parseLineToInteger(String line) {
        line = line.strip();
        String[] valuesStr = line.split(PARAM_DELIMITER);
        List<Integer> values = new ArrayList<>();
        for (String valueStr : valuesStr) {
            int value = Integer.parseInt(valueStr);
            values.add(value);
        }
        return values;
    }
}
