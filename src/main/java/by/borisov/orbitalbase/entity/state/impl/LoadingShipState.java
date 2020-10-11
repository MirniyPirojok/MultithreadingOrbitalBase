package by.borisov.orbitalbase.entity.state.impl;

import by.borisov.orbitalbase.entity.Pier;
import by.borisov.orbitalbase.entity.Port;
import by.borisov.orbitalbase.entity.Ship;
import by.borisov.orbitalbase.entity.state.ShipState;
import by.borisov.orbitalbase.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class LoadingShipState implements ShipState {
    static Logger logger = LogManager.getLogger();

    @Override
    public void doAction(Ship ship) {
        Port port = Port.getInstance();
        Pier pier = null;
        try {
            pier = port.getPier();
            logger.info(String.format("Ship #%d got pier #%d.", ship.getShipId(), pier.getId()));

            pier.loadShip(ship);
            logger.info(String.format("Ship #%d is loaded. New ship cargo weight is %d.",
                    ship.getShipId(), ship.getShipCargoWeight()));

        } catch (CustomException e) {
            logger.error(e);
        } finally {
            if (pier != null) {
                port.returnPier(pier);
                logger.info(String.format(
                        "Pier #%d is released by ship #%d. New pier cargo weight is %d.",
                        pier.getId(), ship.getShipId(), ship.getShipCargoWeight()));
            } else {
                logger.error(String.format("Pier is null for ship #%d", ship.getShipId()));
            }
        }
        ship.setShipState(new GettingOutShipState());
    }
}
