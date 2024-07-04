package be.howest.ti.monopoly.logic.implementation;

import io.vertx.ext.web.openapi.ErrorType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnedPropertyTest {
    @Test
    void putPropertyInMortgage() {
        OwnedProperty ownedProperty = new OwnedProperty("Oriental");
        assertEquals(false, ownedProperty.isMortgage());

        ownedProperty.putInMortgage();
        assertEquals(true, ownedProperty.isMortgage());

        ownedProperty.putOutOfMorgage();
        assertEquals(false, ownedProperty.isMortgage());
    }

    @Test
    void addHouse() {
        OwnedProperty ownedProperty = new OwnedProperty("Oriental");
        ownedProperty.addHouse();
        ownedProperty.addHouse();
        assertEquals(2, ownedProperty.getHouseCount());

        ownedProperty.addHouse();
        ownedProperty.addHouse();
        assertEquals(4, ownedProperty.getHouseCount());

        ownedProperty.addHouse();
        assertEquals(0, ownedProperty.getHouseCount());
    }

    @Test
    void addHotel() {
        OwnedProperty ownedProperty = new OwnedProperty("Oriental");
        ownedProperty.addHouse();
        ownedProperty.addHouse();
        ownedProperty.addHouse();
        ownedProperty.addHouse();
        ownedProperty.addHouse();
        assertEquals(1, ownedProperty.getHotelCount());

    }

}