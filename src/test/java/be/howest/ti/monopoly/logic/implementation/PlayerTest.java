package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void addProperty() {
        Player player = new Player("Bob");
        player.addProperty("Oriental");
        player.addProperty("Texas");

        assertEquals(2, player.getProperties().size());
    }

    @Test
    void putPropertyInMortgage() {
        Player player = new Player("Bob");
        player.addProperty("Oriental");
        player.addProperty("Texas");
        player.putPropertyInMortgage("Oriental");

        assertEquals("[OwnedProperty{property='Oriental', mortgage=true" +
                ", houseCount=0, hotelCount=0}, OwnedProperty{property='Texas'" +
                ", mortgage=false, houseCount=0, hotelCount=0}]",player.getProperties().toString());

    }

    @Test
    void removeProperty() {
        Player player = new Player("Bob");
        player.addProperty("Oriental");
        player.addProperty("Texas");
        player.removeProperty("Oriental");
        assertEquals(1, player.getProperties().size());

        player.removeProperty("Texas");
        assertEquals(0, player.getProperties().size());
    }

    @Test
    void addMoney() {
        Player player = new Player("Bob");
        player.addMoney(500);
        assertEquals(2000, player.getMoney());
    }

    @Test
    void withdrawMoney() {
        Player player = new Player("Bob");
        player.withdrawMoney(500);
        assertEquals(1000, player.getMoney());
    }

    @Test
    void makeBankrupt() {
        Player player = new Player("Bob");
        assertEquals(false, player.isBankrupt());

        player.makeBankrupt();
        assertEquals(true, player.isBankrupt());
    }

    @Test
    void movePosition() {
        Player player = new Player("Bob");

        player.movePosition(35);
        assertEquals("Short Line RR", player.getCurrentTile());

        player.movePosition(12);
        assertEquals("Vermont", player.getCurrentTile());


    }

    @Test
    void buyOutOfJail() {
        Player player = new Player("Bob");
        player.putInJail();
        player.buyOutOfJail();
        assertEquals(false, player.isJailed());
        assertEquals(1450, player.getMoney());
    }

    @Test
    void allOtherOfThisColorAreEqual() {
        Player player = new Player("Bob");
        player.addProperty("Park Place");
        assertEquals(false, player.allOtherOfThisColorAreEqualEnough(player.findProperty("Park Place")));
        player.addProperty("Baltic");
        assertEquals(false, player.allOtherOfThisColorAreEqualEnough(player.findProperty("Park Place")));
        player.addProperty("Virginia");
        assertEquals(false, player.allOtherOfThisColorAreEqualEnough(player.findProperty("Park Place")));
        player.addProperty("Boardwalk");
        assertEquals(true, player.allOtherOfThisColorAreEqualEnough(player.findProperty("Park Place")));
    }

    @Test
    void returnListOfTheOwnedPropertyObjects() {
        Player player = new Player("Bob");
        player.addProperty("Park Place");
        player.addProperty("Baltic");
        player.addProperty("Virginia");
        player.addProperty("Boardwalk");

        assertEquals("[OwnedProperty{property='Park Place', mortgage=false, houseCount=0, hotelCount=0}" +
                        ", OwnedProperty{property='Boardwalk', mortgage=false, houseCount=0, hotelCount=0}]"
                , player.returnListOfTheOwnedPropertyObjects(player.findStreetsOfSameColor("Boardwalk")).toString());
    }

    @Test
    void allOtherOfThisColorAreEqualEnough() {
        Player player = new Player("Bob");
        player.addProperty("Park Place");
        player.addProperty("Baltic");
        player.addProperty("Virginia");
        player.addProperty("Boardwalk");

        assertEquals(true ,player.allOtherOfThisColorAreEqualEnough(player.findProperty("Park Place")));
        player.getProperties().get(0).addHouse();
        assertEquals(true ,player.allOtherOfThisColorAreEqualEnough(player.findProperty("Park Place")));
        player.getProperties().get(0).addHouse();
        assertEquals(false ,player.allOtherOfThisColorAreEqualEnough(player.findProperty("Park Place")));
        player.getProperties().get(0).addHouse();
        player.getProperties().get(0).addHouse();
        assertEquals(false ,player.allOtherOfThisColorAreEqualEnough(player.findProperty("Park Place")));
        player.getProperties().get(0).addHouse();
        assertEquals(false ,player.allOtherOfThisColorAreEqualEnough(player.findProperty("Park Place")));
        player.getProperties().get(3).addHouse();
        player.getProperties().get(3).addHouse();
        player.getProperties().get(3).addHouse();
        assertEquals(false ,player.allOtherOfThisColorAreEqualEnough(player.findProperty("Park Place")));
        player.getProperties().get(3).addHouse();
        assertEquals(true ,player.allOtherOfThisColorAreEqualEnough(player.findProperty("Park Place")));
    }

    @Test
    void buildHouse() {
        Player player = new Player("Bob");
        player.addProperty("Park Place");
        player.addProperty("Baltic");
        player.addProperty("Virginia");
        player.addProperty("Boardwalk");

        player.buildHouse("Park Place");
        assertEquals(1, player.getProperties().get(0).getHouseCount());
        player.buildHouse("Boardwalk");
        player.buildHouse("Boardwalk");
        assertEquals(1, player.getProperties().get(0).getHouseCount());
        assertEquals(2, player.getProperties().get(3).getHouseCount());
        player.buildHouse("Park Place");
        player.buildHouse("Boardwalk");
        assertEquals(2, player.getProperties().get(0).getHouseCount());
        player.buildHouse("Park Place");
        player.buildHouse("Boardwalk");
        player.buildHouse("Park Place");
        player.buildHouse("Boardwalk");
        assertEquals(4, player.getProperties().get(0).getHouseCount());
        player.buildHouse("Park Place");
        assertEquals(0, player.getProperties().get(0).getHouseCount());
        assertEquals(0, player.getProperties().get(3).getHouseCount());
        assertEquals(1, player.getProperties().get(3).getHotelCount());
        assertEquals(1, player.getProperties().get(0).getHotelCount());
    }
    @Test
    void sellHouse() {
        Player player = new Player("Bob");
        player.addProperty("Park Place");
        player.addProperty("Baltic");
        player.addProperty("Virginia");
        player.addProperty("Boardwalk");

        player.buildHouse("Park Place");
        player.buildHouse("Boardwalk");
        player.buildHouse("Boardwalk");
        player.buildHouse("Park Place");
        player.buildHouse("Boardwalk");
        player.buildHouse("Park Place");
        player.buildHouse("Boardwalk");
        player.buildHouse("Park Place");
        player.buildHouse("Boardwalk");
        player.buildHouse("Park Place");
        assertEquals(1, player.findProperty("Park Place").getHotelCount());
        assertEquals(1, player.findProperty("Boardwalk").getHotelCount());
        assertEquals(0, player.findProperty("Park Place").getHouseCount());
        assertEquals(0, player.findProperty("Boardwalk").getHouseCount());

        player.removeHouse("Park Place");
        assertEquals(4, player.findProperty("Park Place").getHouseCount());
        player.removeHouse("Boardwalk");
        player.removeHouse("Boardwalk");
        assertEquals(3, player.findProperty("Boardwalk").getHouseCount());
        player.removeHouse("Park Place");
        player.removeHouse("Boardwalk");
        player.removeHouse("Park Place");
        player.removeHouse("Boardwalk");
        player.removeHouse("Park Place");
        player.removeHouse("Boardwalk");
        player.removeHouse("Park Place");
        assertEquals(0, player.findProperty("Boardwalk").getHouseCount());
        assertEquals(0, player.findProperty("Park Place").getHouseCount());
    }

    @Test
    void addRemoveMoneyWhenBuyingOrSellingHouse() {
        Player player = new Player("Bob");
        player.addProperty("Park Place");
        player.addProperty("Boardwalk");

        player.buildHouse("Park Place");
        player.buildHouse("Boardwalk");
        player.buildHouse("Boardwalk");
        player.buildHouse("Park Place");
        player.buildHouse("Boardwalk");
        player.buildHouse("Park Place");

        assertEquals(300, player.getMoney());

        player.removeHouse("Park Place");
        player.removeHouse("Boardwalk");
        assertEquals(500, player.getMoney());
    }

}