package by.borisov.orbitalbase.creator;

import by.borisov.orbitalbase.entity.Ship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class ShipCreator {
    static Logger logger = LogManager.getLogger();

    public Ship create(List<Integer> values, int id) {
        int capacity = values.get(0);
        int shipCargoWeight = values.get(1);
        Ship ship = new Ship(id, capacity, shipCargoWeight);
        logger.info(ship);
        return ship;
    }

    public Queue<Ship> createShipQueue(List<List<Integer>> valuesList) {
        Queue<Ship> ships = new PriorityQueue<>();

        for (int i = 0; i < valuesList.size(); i++) {
            Ship ship = create(valuesList.get(i), i+1);
            ships.offer(ship);
        }

        if (ships.isEmpty()) {
            logger.error("Ship list is not created.");
        } else {
            logger.info("Ship list created.");
        }
        return ships;
    }
}
