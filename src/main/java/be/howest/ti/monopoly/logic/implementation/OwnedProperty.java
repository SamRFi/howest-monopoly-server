package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;

import java.security.SecureRandom;
import java.util.Random;

public class OwnedProperty extends Board {
    private String property;
    private boolean mortgage;
    private int houseCount;
    private int hotelCount;

    private Random random = new SecureRandom();

    public OwnedProperty(String propertyName) {
        this.property = propertyName;
        this.mortgage = false;
        this.houseCount = 0;
        this.hotelCount = 0;
    }

    public void putInMortgage() {
        this.mortgage = true;
    }
    public void putOutOfMorgage() {
        this.mortgage = false;
    }

    public void addHouse() {
        if (this.hotelCount == 1) {
            throw new IllegalMonopolyActionException("Property is already fully improved");
        }
        if (this.houseCount == 4) {
            this.hotelCount = 1;
            this.houseCount = 0;
        } else {
            this.houseCount += 1;
        }
    }

    public void removeHouse() {
        if (this.houseCount == 0 && this.hotelCount == 0) {
            throw new IllegalMonopolyActionException("player has no houses to sell");
        }
        if (this.hotelCount == 1) {
            this.hotelCount = 0;
            this.houseCount = 4;
        } else {
            this.houseCount -= 1;
        }
    }



    public int calculateRentCost(int diceRoll, String propertyName, int houseCount, int hotelCount) {
        if (findPropertyTile(this.property).getType().equals("utility")) {
            int rand = random.nextInt(2);
            if (rand == 0) {
                return diceRoll * 4;
            } else {
                return diceRoll * 10;
            }
        } else if (findPropertyTile(this.property).getType().equals("street")) {
            if (hotelCount == 1) {
                return findStreetTile(propertyName).getRentWithHotel();
            }
            return findStreetTile(propertyName).rentWithHouses(houseCount);
        } else {
            return Integer.parseInt(findPropertyTile(this.property).getRent());
        }
    }

    public String getProperty() {
        return property;
    }

    public boolean isMortgage() {
        return mortgage;
    }

    public int getHouseCount() {
        return houseCount;
    }

    public int getHotelCount() {
        return hotelCount;
    }

    @Override
    public String toString() {
        return "OwnedProperty{" +
                "property='" + property + '\'' +
                ", mortgage=" + mortgage +
                ", houseCount=" + houseCount +
                ", hotelCount=" + hotelCount +
                '}';
    }
}
