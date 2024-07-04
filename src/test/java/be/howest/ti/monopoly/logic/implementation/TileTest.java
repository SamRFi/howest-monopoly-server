package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {
    @Test
    void returnTileInfoStreetTile() {
        StreetTile streetTile = new StreetTile( "Random Tile", 4,600, "40",3, "BLUE");
        streetTile.setHousePrice(40);
        streetTile.setRentWithOneHouse(50);
        streetTile.setRentWithTwoHouses(70);
        streetTile.setRentWithThreeHouses(80);
        streetTile.setRentWithFourHouses(200);
        streetTile.setRentWithHotel(250);

        assertEquals("StreetTile{" + "" +
                        "position=4, " +
                        "name=Random Tile, " +
                        "type=street, " +
                        "nameAsPathParameter=Random_Tile, " +
                        "cost=600, " +
                        "mortage=300, " +
                        "rent='40', " +
                        "groupsize=3, " +
                        "color=BLUE, " +
                        "streetcolor=BLUE, " +
                        "housePrice=40, " +
                        "rentWithOneHouse=50, " +
                        "rentWithTwoHouses=70, " +
                        "rentWithThreeHouses=80, " +
                        "rentWithFourHouses=200, " +
                        "rentWithHotel=250" + "}", streetTile.toString());
    }
    @Test
    void returnTileInfoTile(){
        Tile tile = new Tile("Random Tile", 0, "Random Tile");

        assertEquals("Tile{" + "" +
                        "position=0, " +
                        "name='Random Tile', " +
                        "type='Random Tile', " +
                        "nameAsPathParameter='Random_Tile'" + "}", tile.toString());
    }

    @Test
    void returnTileInfoPropertyTile(){
        PropertyTile propertyTile = new PropertyTile("Random" , 1, "property", 200, "25",1, "BLACK");

        assertEquals("PropertyTile{" + "" +
                        "name=Random, " +
                        "position=1, " +
                        "type=property, " +
                        "nameAsPathParameter=Random, " +
                        "cost=200, " +
                        "mortgage=100, " +
                        "rent='25', " +
                        "groupSize=1, " +
                        "color='BLACK'" + "}", propertyTile.toString());

    }
}