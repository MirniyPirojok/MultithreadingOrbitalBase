package by.borisov.orbitalbase.demo;

import by.borisov.orbitalbase.creator.ShipCreator;
import by.borisov.orbitalbase.entity.Ship;
import by.borisov.orbitalbase.exception.CustomException;
import by.borisov.orbitalbase.parser.DataParser;
import by.borisov.orbitalbase.reader.DataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Queue;

public class Demo {
    static Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws CustomException {

        DataReader dataReader = new DataReader();
        List<String> lines = dataReader.readData("data/shipdata.txt");

        DataParser dataParser = new DataParser();
        List<List<Integer>> valuesList = dataParser.parseData(lines);

        ShipCreator shipCreator = new ShipCreator();
        Queue<Ship> ships = shipCreator.createShipQueue(valuesList);
        for (Ship ship : ships) {
            logger.info(ship);
        }

        for (Ship ship : ships) {
            ship.start();
        }
    }
}
