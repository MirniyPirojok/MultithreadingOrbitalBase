package by.borisov.orbitalbase.entity;

import by.borisov.orbitalbase.entity.state.ShipState;
import by.borisov.orbitalbase.entity.state.impl.GettingInShipState;
import by.borisov.orbitalbase.entity.state.impl.GettingOutShipState;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Ship extends Thread implements Comparable<Ship> {

    private int shipId;
    private int capacity = 10;
    private int shipCargoWeight = 10;

    private ShipState state = new GettingInShipState();

    public Ship() {
    }

    public Ship(int shipId) {
        this.shipId = shipId;
    }

    public Ship(int shipId, int capacity, int shipCargoWeight) {
        this.shipId = shipId;
        this.capacity = capacity;
        this.shipCargoWeight = shipCargoWeight;
    }

    public long getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getShipCargoWeight() {
        return shipCargoWeight;
    }

    public void setShipCargoWeight(int shipCargoWeight) {
        this.shipCargoWeight = shipCargoWeight;
    }

    public ShipState getShipState() {
        return state;
    }

    public void setShipState(ShipState state) {
        this.state = state;
    }

    public boolean hasCargo() {
        return shipCargoWeight > 0;
    }

    @Override
    public void run() {
        do {
            state.doAction(this);
        } while (state.getClass() != GettingOutShipState.class);

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ship{");
        sb.append("id=").append(shipId);
        sb.append(", capacity=").append(capacity);
        sb.append(", cargo weight=").append(shipCargoWeight);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        return new EqualsBuilder()
                .append(shipId, ship.shipId)
                .append(capacity, ship.capacity)
                .append(shipCargoWeight, ship.shipCargoWeight)
                .append(state, ship.state)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(shipId)
                .append(capacity)
                .append(shipCargoWeight)
                .append(state)
                .toHashCode();
    }

    @Override
    public int compareTo(Ship o) {
        return Integer.compare(this.shipId, o.shipId);
    }
}
