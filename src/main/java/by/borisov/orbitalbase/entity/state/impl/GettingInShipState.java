package by.borisov.orbitalbase.entity.state.impl;

import by.borisov.orbitalbase.entity.Ship;
import by.borisov.orbitalbase.entity.state.ShipState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GettingInShipState implements ShipState {
    static Logger logger = LogManager.getLogger();

    @Override
    public void doAction(Ship ship) {
        logger.info(String.format("%s is getting in port.", ship));
        if (ship.hasCargo()) {
            ship.setShipState(new UnloadingShipState());
        } else {
            ship.setShipState(new LoadingShipState());
        }
    }
}
