package by.borisov.orbitalbase.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Pier {
    static Logger logger = LogManager.getLogger();
    private static final int LOAD_TIME = 100;

    private int id;
    private int pierCapacity = 100;
    private int pierCargoWeight = 50;

    public Pier() {
        logger.info("Pier is created");
    }

    public Pier(int id) {
        logger.info(String.format("Pier #%d is created", id));
        this.id = id;
    }

    public Pier(int id, int pierCapacity, int pierCargoWeight) {
        logger.info(String.format("Pier #%d is created", id));
        this.id = id;
        this.pierCapacity = pierCapacity;
        this.pierCargoWeight = pierCargoWeight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPierCapacity() {
        return pierCapacity;
    }

    public void setPierCapacity(int pierCapacity) {
        this.pierCapacity = pierCapacity;
    }

    public int getPierCargoWeight() {
        return pierCargoWeight;
    }

    public void setPierCargoWeight(int pierCargoWeight) {
        this.pierCargoWeight = pierCargoWeight;
    }

    public void loadShip(Ship ship) {
        logger.info(String.format("Pier #%d : Loading method starts : ship #%d capacity %d ton",
                this.id, ship.getShipId(), ship.getCapacity()));

        try {
            TimeUnit.MILLISECONDS.sleep(ship.getCapacity() * LOAD_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (pierCargoWeight == 0) {
            logger.info(String.format("No cargo in pier #%d. Generate cargo.", this.id));
            fillPier();
        }

        if (pierCargoWeight < ship.getCapacity()) {
            logger.info(String.format("Not enough cargo in pier #%d to load ship full. Generate cargo.", this.id));
            pierCargoWeight += ship.getCapacity() - pierCargoWeight;
        }

        pierCargoWeight -= ship.getCapacity();
        ship.setShipCargoWeight(ship.getCapacity());
    }

    public void unloadShip(Ship ship) {
        logger.info(String.format("Pier #%d : Unloading method starts : ship #%d cargo %d ton",
                this.id, ship.getShipId(), ship.getShipCargoWeight()));

        try {
            TimeUnit.MILLISECONDS.sleep(ship.getShipCargoWeight() * LOAD_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int freeSpace = pierCapacity - pierCargoWeight;
        if (freeSpace <= ship.getShipCargoWeight()) {
            emptyPier();
        }
        pierCargoWeight += ship.getShipCargoWeight();
        ship.setShipCargoWeight(0);
    }

    private void fillPier() {
        pierCargoWeight = pierCapacity / 2;
        logger.info(String.format("Pier #%d was filled to %d ton", this.id, pierCapacity / 2));
    }

    private void emptyPier() {
        pierCargoWeight = 0;
        logger.info(String.format("Pier #%d was emptied.", this.id));
    }
}
