package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player extends Board {
    private String name;
    private String currentTile;
    private boolean jailed;
    private int money;
    private boolean bankrupt;
    private int getOutOfJailFreeCards;
    private List<OwnedProperty> properties;
    private int debt;
    private int jailSittingCounter;

    public Player(String name) {
        this.name = name;
        this.currentTile = "Go";
        this.jailed = false;
        this.money = 1500;
        this.bankrupt = false;
        this.getOutOfJailFreeCards = 0;
        this.properties = new ArrayList<>();
        this.jailSittingCounter = 0;
    }

    public void putOnTile(String tileName) {
        this.currentTile = tileName;
    }

    public void movePosition(int amountToMoveBy) {
        int currentPosition = findPosition(this.currentTile);
        currentPosition += amountToMoveBy;

        if (currentPosition > 39) {
            addMoney(200);
            currentPosition -= 39;
        }

        this.currentTile = findTileName(currentPosition);
    }

    public void putInJail() {
        this.jailed = true;
    }

    public void addMoney(int amount) {
        this.money += amount;
    }

    public void withdrawMoney(int amount) {
        this.money -= amount;
    }

    public void makeBankrupt() {
        this.bankrupt = true;
        this.properties = Collections.emptyList();
        this.money = 0;
    }

    public void addGetOutOfJailFreeCard() {
        getOutOfJailFreeCards += 1;
    }

    public void addDebt(int amount) {
        this.debt += amount;
    }

    public void removeDebt(int amount) {
        this.debt -= amount;
    }

    public void addProperty(String propertyName) {
        this.properties.add(new OwnedProperty(propertyName));
    }

    public void buildHouse(String propertyName) {
        OwnedProperty theProperty = findProperty(propertyName);
        if (theProperty != null) {
            theProperty.addHouse();
            if (!allOtherOfThisColorAreEqualEnough(theProperty)) {
                theProperty.removeHouse();
                throw new IllegalMonopolyActionException("player must build evenly");
            }
            withdrawMoney(super.findStreetTile(propertyName).getHousePrice());
        } else {
            throw new IllegalMonopolyActionException("player must first own this property");
        }
    }

    public void removeHouse(String propertyName) {
        OwnedProperty theProperty = findProperty(propertyName);
        if (theProperty != null) {
            theProperty.removeHouse();
            if (!allOtherOfThisColorAreEqualEnough(theProperty)) {
                theProperty.addHouse();
                throw new IllegalMonopolyActionException("player must build evenly");
            }
            addMoney(super.findStreetTile(propertyName).getHousePrice()/2);
        } else {
            throw new IllegalMonopolyActionException("player must first own this property");
        }
    }

    public boolean allOtherOfThisColorAreEqualEnough(OwnedProperty property) {
        String propertyName = property.getProperty();
        List<String> groupOfStreetNames = findStreetsOfSameColor(propertyName);
        List<String> ownedPropertyNames = findListOfOwnedPropertyNames();
        if (ownedPropertyNames.containsAll(groupOfStreetNames)) {
            return allOtherHousesOfThisColorAreEqualEnough(groupOfStreetNames);
        }
        return false;
    }


    public boolean allOtherHousesOfThisColorAreEqualEnough(List<String> groupOfStreetNames) {
        List<OwnedProperty> theGroupOfOwnedProperties = returnListOfTheOwnedPropertyObjects(groupOfStreetNames);
        int previousHouseCount = -500;
        int currentHouseCount = 0;
        for (int i = 0; i < theGroupOfOwnedProperties.size(); i = i + 1) {
            if (previousHouseCount != -500) {
                currentHouseCount = checkForHotel(theGroupOfOwnedProperties, i);
                if ((currentHouseCount-previousHouseCount) < -1 || (currentHouseCount-previousHouseCount) > 1) {
                    return false;
                }
                previousHouseCount = currentHouseCount;
            } else {
                previousHouseCount = checkForHotel(theGroupOfOwnedProperties, i);
            }
        }
        return true;
    }

    private int checkForHotel(List<OwnedProperty> theGroupOfOwnedProperties, int index) {
        if (theGroupOfOwnedProperties.get(index).getHotelCount() == 1) {
            return 5;
        } else {
            return theGroupOfOwnedProperties.get(index).getHouseCount();
        }
    }

    public List<String> findListOfOwnedPropertyNames() {
        List<String> listOfOwnedPropertyNames = new ArrayList<>();
        if (!properties.isEmpty()) {
            for (int i = 0; i < properties.size(); i = i + 1) {
                listOfOwnedPropertyNames.add(properties.get(i).getProperty());
            }
            return listOfOwnedPropertyNames;
        }
        return Collections.emptyList();
    }

    public List<OwnedProperty> returnListOfTheOwnedPropertyObjects(List<String> listOfPropertyNames) {
        List<OwnedProperty> result = new ArrayList<>();
        for (int i = 0; i < properties.size(); i = i + 1) {
            for (int i2 = 0; i2 < listOfPropertyNames.size(); i2 = i2 + 1) {
                if (listOfPropertyNames.get(i2).equals(properties.get(i).getProperty())) {
                    result.add(properties.get(i));
                }
            }
        }
        return result;
    }

    public void buyOutOfJail(){
        if (isJailed()){
            money -= 50;
            jailed = false;
        } else {
            throw new IllegalMonopolyActionException("player is not in jail");
        }
    }

    public void putOutOfJail(){
        jailed = false;
    }


    public void putPropertyInMortgage(String propertyName) {
        findProperty(propertyName).putInMortgage();
    }

    public void removeProperty(String propertyName) {
        int propertyIndexInList = findPropertyIndex(propertyName);
        properties.remove(propertyIndexInList);
    }

    public int findPropertyIndex(String propertyName) {
        for (int i = 0; i < this.properties.size(); i = i + 1) {
            if (this.properties.get(i).getProperty().equals(propertyName)) {
                return i;
            }
        }
        return -1;
    }

    public OwnedProperty findProperty(String propertyName) {
        for (int i = 0; i < this.properties.size(); i = i + 1) {
            if (this.properties.get(i).getProperty().equals(propertyName)) {
                return this.properties.get(i);
            }
        }
        return null;
    }

    public void setJailSittingCounter(int jailSittingCounter) {
        this.jailSittingCounter = jailSittingCounter;
    }

    public void addJailSittingCount() {
        this.jailSittingCounter += 1;
    }

    public int getJailSittingCounter() {
        return jailSittingCounter;
    }

    public String getName() {
        return name;
    }

    public String getCurrentTile() {
        return currentTile;
    }

    public boolean isJailed() {
        return jailed;
    }

    public int getMoney() {
        return money;
    }

    public boolean isBankrupt() {
        return bankrupt;
    }

    public int getGetOutOfJailFreeCards() {
        return getOutOfJailFreeCards;
    }

    public List<OwnedProperty> getProperties() {
        return properties;
    }

    public int getDebt() {
        return debt;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", currentTile='" + currentTile + '\'' +
                ", jailed=" + jailed +
                ", money=" + money +
                ", bankrupt=" + bankrupt +
                ", getOutOfJailFreeCards=" + getOutOfJailFreeCards +
                ", properties=" + properties +
                ", debt=" + debt +
                '}';
    }
}
