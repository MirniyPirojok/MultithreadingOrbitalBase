package by.borisov.orbitalbase.entity.state.impl;

import by.borisov.orbitalbase.entity.Ship;
import by.borisov.orbitalbase.entity.state.ShipState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GettingOutShipState implements ShipState {
    static Logger logger = LogManager.getLogger();

    @Override
    public void doAction(Ship ship) {
        logger.info(String.format("Ship #%d has left the port.", ship.getShipId()));
    }
}
