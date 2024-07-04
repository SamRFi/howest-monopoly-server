package be.howest.ti.monopoly.logic.implementation;


import java.util.ArrayList;
import java.util.List;


public class Board {
    private List<Boolean> directSalePositions;
    private List<Tile> tiles;
    private List<SpecialCard> chanceCards;
    private List<SpecialCard> communityCards;

    public Board() {
        directSalePositions = new ArrayList<>();
        tiles = new ArrayList<>();
        chanceCards = new ArrayList<>();
        communityCards = new ArrayList<>();
        List<Boolean> directSalePositionsList = List.of(false, true, false, true, false, true,
                true, false, true, true, false, true, true,
                true, true, true, true, false,
                true, true, false, true, false, true,
                true, true, true, true, true, true,
                false, true, true, false, true, true,
                false, true, false, true);
        directSalePositions.addAll(directSalePositionsList);

        chanceCards = List.of(
                new SpecialCard("Advance to Boardwalk", "Boardwalk"),
                new SpecialCard("Advance to Go (Collect $200)", "Go"),
                new SpecialCard("Advance to Illinois Avenue. If you pass Go, collect $200", "Illinois Avenue"),
                new SpecialCard("Advance to St. Charles Place. If you pass Go, collect $200", "St. Charles Place"),
                new SpecialCard("Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay owner twice the rental to which they are otherwise entitled", "Railroad"),
                new SpecialCard("Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times amount thrown.", "Utility"),
                new SpecialCard("Bank pays you dividend of $50)", ""),
                new SpecialCard("Get Out of Jail Free", "jailcard"),
                new SpecialCard("Go Back 3 Spaces", ""),
                new SpecialCard("Go to Jail. Go directly to Jail, do not pass Go, do not collect $200", ""),
                new SpecialCard("Make general repairs on all your property. For each house pay $25. For each hotel pay $100", ""),
                new SpecialCard("Speeding fine $15", "-15"),
                new SpecialCard("Take a trip to Reading Railroad. If you pass Go, collect $200", ""),
                new SpecialCard("You have been elected Chairman of the Board. Pay each player $50", ""),
                new SpecialCard("Your building loan matures. Collect $150", "")
        );

        communityCards = List.of(
                new SpecialCard("Advance to Go (Collect $200)", "Go"),
                new SpecialCard("Bank error in your favor. Collect $200", "+200"),
                new SpecialCard("Doctor's fee. Pay $50", "-50"),
                new SpecialCard("From sale of stock you get $50", "+50"),
                new SpecialCard("Get Out of Jail Free", "+1"),
                new SpecialCard("Go to Jail. Go directly to jail, do not pass Go, do not collect $200", "True"),
                new SpecialCard("Holiday fund matures. Receive $100", "+100"),
                new SpecialCard("Income tax refund. Collect $20", "+20"),
                new SpecialCard("It is your birthday. Collect $10 from every player", "-10 * (amountOfPlayers-1)"),
                new SpecialCard("Life insurance matures. Collect $100", "+100"),
                new SpecialCard("Pay hospital fees of $100", "-100"),
                new SpecialCard("Pay school fees of $50", "+50"),
                new SpecialCard("Receive $25 consultancy fee", "+25"),
                new SpecialCard("You are assessed for street repair. $40 per house. $115 per hotel", "geen idee"),
                new SpecialCard("You have won second prize in a beauty contest. Collect $10", "+10"),
                new SpecialCard("You inherit $100", "+100")
        );

        String purple = "PURPLE";
        String orange = "ORANGE";
        String lightblue = "LIGHTBLUE";
        String violet = "VIOLET";
        String yellow = "YELLOW";
        String darkGreen = "DARKGREEN";
        String railroad = "railroad";
        String black = "BLACK";
        String red = "RED";
        String darkBlue = "DARKBLUE";
        StreetTile mediterranean = new StreetTile("Mediterranean", 1, 60, "2", 2,  purple);
        mediterranean.setRentWithOneHouse(10);
        mediterranean.setRentWithTwoHouses(30);
        mediterranean.setRentWithThreeHouses(90);
        mediterranean.setRentWithFourHouses(160);
        mediterranean.setRentWithHotel(250);
        mediterranean.setHousePrice(50);
        StreetTile baltic = new StreetTile("Baltic", 3, 60, "4", 2, purple);
        baltic.setRentWithOneHouse(20);
        baltic.setRentWithTwoHouses(60);
        baltic.setRentWithThreeHouses(180);
        baltic.setRentWithFourHouses(320);
        baltic.setRentWithHotel(450);
        baltic.setHousePrice(50);
        StreetTile oriental = new StreetTile("Oriental", 6, 100, "6", 3, lightblue);
        oriental.setRentWithOneHouse(30);
        oriental.setRentWithTwoHouses(90);
        oriental.setRentWithThreeHouses(270);
        oriental.setRentWithFourHouses(400);
        oriental.setRentWithHotel(550);
        oriental.setHousePrice(50);
        StreetTile vermont = new StreetTile("Vermont", 8, 100, "6", 3, lightblue);
        vermont.setRentWithOneHouse(30);
        vermont.setRentWithTwoHouses(90);
        vermont.setRentWithThreeHouses(270);
        vermont.setRentWithFourHouses(400);
        vermont.setRentWithHotel(550);
        vermont.setHousePrice(50);
        StreetTile connecticut = new StreetTile("Connecticut", 9, 120, "8", 3, lightblue);
        connecticut.setRentWithOneHouse(40);
        connecticut.setRentWithTwoHouses(100);
        connecticut.setRentWithThreeHouses(300);
        connecticut.setRentWithFourHouses(450);
        connecticut.setRentWithHotel(600);
        connecticut.setHousePrice(50);
        StreetTile saintCharlesPlace = new StreetTile("Saint Charles Place", 11, 140, "10", 3, violet);
        saintCharlesPlace.setRentWithOneHouse(50);
        saintCharlesPlace.setRentWithTwoHouses(150);
        saintCharlesPlace.setRentWithThreeHouses(450);
        saintCharlesPlace.setRentWithFourHouses(625);
        saintCharlesPlace.setRentWithHotel(750);
        saintCharlesPlace.setHousePrice(100);
        StreetTile states = new StreetTile("States", 13, 140, "10", 3, violet);
        states.setRentWithOneHouse(50);
        states.setRentWithTwoHouses(150);
        states.setRentWithThreeHouses(450);
        states.setRentWithFourHouses(625);
        states.setRentWithHotel(750);
        states.setHousePrice(100);
        StreetTile virginia = new StreetTile("Virginia", 13, 140, "10", 3, violet);
        virginia.setRentWithOneHouse(60);
        virginia.setRentWithTwoHouses(180);
        virginia.setRentWithThreeHouses(500);
        virginia.setRentWithFourHouses(700);
        virginia.setRentWithHotel(900);
        virginia.setHousePrice(100);
        StreetTile saintJames = new StreetTile("Saint James", 16, 180, "14", 3, orange);
        saintJames.setRentWithOneHouse(70);
        saintJames.setRentWithTwoHouses(200);
        saintJames.setRentWithThreeHouses(550);
        saintJames.setRentWithFourHouses(750);
        saintJames.setRentWithHotel(950);
        saintJames.setHousePrice(100);
        StreetTile tennessee = new StreetTile("tennessee", 18, 180, "14", 3, orange);
        tennessee.setRentWithOneHouse(70);
        tennessee.setRentWithTwoHouses(200);
        tennessee.setRentWithThreeHouses(550);
        tennessee.setRentWithFourHouses(750);
        tennessee.setRentWithHotel(950);
        tennessee.setHousePrice(100);
        StreetTile newYork = new StreetTile("New York", 19, 200, "16", 3, orange);
        newYork.setRentWithOneHouse(80);
        newYork.setRentWithTwoHouses(220);
        newYork.setRentWithThreeHouses(600);
        newYork.setRentWithFourHouses(800);
        newYork.setRentWithHotel(1000);
        newYork.setHousePrice(100);
        StreetTile kentuckyAvenue = new StreetTile("Kentucky Avenue", 21, 220, "18", 3, red);
        kentuckyAvenue.setRentWithOneHouse(90);
        kentuckyAvenue.setRentWithTwoHouses(250);
        kentuckyAvenue.setRentWithThreeHouses(750);
        kentuckyAvenue.setRentWithFourHouses(875);
        kentuckyAvenue.setRentWithHotel(1050);
        kentuckyAvenue.setHousePrice(150);
        StreetTile indianaAvenue = new StreetTile("Indiana Avenue", 23, 220, "18", 3, red);
        indianaAvenue.setRentWithOneHouse(90);
        indianaAvenue.setRentWithTwoHouses(250);
        indianaAvenue.setRentWithThreeHouses(750);
        indianaAvenue.setRentWithFourHouses(875);
        indianaAvenue.setRentWithHotel(1050);
        indianaAvenue.setHousePrice(150);
        StreetTile illinoisAvenue = new StreetTile("Illinois Avenue", 24, 240, "20", 3, red);
        illinoisAvenue.setRentWithOneHouse(100);
        illinoisAvenue.setRentWithTwoHouses(300);
        illinoisAvenue.setRentWithThreeHouses(750);
        illinoisAvenue.setRentWithFourHouses(925);
        illinoisAvenue.setRentWithHotel(1100);
        illinoisAvenue.setHousePrice(150);
        StreetTile atlantic = new StreetTile("Atlantic", 26, 260, "22", 3, yellow);
        atlantic.setRentWithOneHouse(110);
        atlantic.setRentWithTwoHouses(330);
        atlantic.setRentWithThreeHouses(800);
        atlantic.setRentWithFourHouses(975);
        atlantic.setRentWithHotel(1150);
        atlantic.setHousePrice(150);
        StreetTile ventnor = new StreetTile("Ventnor", 27, 260, "22", 3, yellow);
        ventnor.setRentWithOneHouse(110);
        ventnor.setRentWithTwoHouses(330);
        ventnor.setRentWithThreeHouses(800);
        ventnor.setRentWithFourHouses(975);
        ventnor.setRentWithHotel(1150);
        ventnor.setHousePrice(150);
        StreetTile marvinGardens = new StreetTile("Marvin Gardens", 29, 280,"24",3, yellow);
        marvinGardens.setRent("4");
        marvinGardens.setRentWithOneHouse(120);
        marvinGardens.setRentWithTwoHouses(360);
        marvinGardens.setRentWithThreeHouses(850);
        marvinGardens.setRentWithFourHouses(1025);
        marvinGardens.setRentWithHotel(1200);
        marvinGardens.setHousePrice(150);
        StreetTile pacific = new StreetTile("pacific", 31, 300,"26",3, darkGreen);
        pacific.setRent("26");
        pacific.setRentWithOneHouse(130);
        pacific.setRentWithTwoHouses(390);
        pacific.setRentWithThreeHouses(900);
        pacific.setRentWithFourHouses(1100);
        pacific.setRentWithHotel(1275);
        pacific.setHousePrice(200);
        StreetTile northCarolrina = new StreetTile("North Carolina", 32, 300,"26",3, darkGreen);
        northCarolrina.setRent("26");
        northCarolrina.setRentWithOneHouse(130);
        northCarolrina.setRentWithTwoHouses(390);
        northCarolrina.setRentWithThreeHouses(900);
        northCarolrina.setRentWithFourHouses(1100);
        northCarolrina.setRentWithHotel(1275);
        northCarolrina.setHousePrice(200);
        StreetTile pennsylvania = new StreetTile("Pennsylvania", 34, 320, "28",3, darkGreen);
        pennsylvania.setRent("28");
        pennsylvania.setRentWithOneHouse(150);
        pennsylvania.setRentWithTwoHouses(450);
        pennsylvania.setRentWithThreeHouses(1000);
        pennsylvania.setRentWithFourHouses(1200);
        pennsylvania.setRentWithHotel(1400);
        pennsylvania.setHousePrice(200);
        StreetTile parkPlace = new StreetTile("Park Place", 37, 350,"35",2, darkBlue);
        parkPlace.setRent("35");
        parkPlace.setRentWithOneHouse(175);
        parkPlace.setRentWithTwoHouses(500);
        parkPlace.setRentWithThreeHouses(1100);
        parkPlace.setRentWithFourHouses(1300);
        parkPlace.setRentWithHotel(1500);
        parkPlace.setHousePrice(200);
        StreetTile boardwalk = new StreetTile("Boardwalk", 39, 400,"50",2, darkBlue);
        boardwalk.setRent("50");
        boardwalk.setRentWithOneHouse(200);
        boardwalk.setRentWithTwoHouses(600);
        boardwalk.setRentWithThreeHouses(1400);
        boardwalk.setRentWithFourHouses(1700);
        boardwalk.setRentWithHotel(2000);
        boardwalk.setHousePrice(200);

        List<Tile> tilesList = List.of(
                new Tile("Go", 0, "Go"),
                mediterranean,
                new Tile("Community Chest I", 2, "Community Chest I"),
                baltic,
                new Tile("Tax Income", 4, "Tax Income"),
                new PropertyTile("Reading RR" , 5, railroad,200, "25", 4, black),
                oriental,
                new Tile("Chance I", 7, "chance"),
                vermont,
                connecticut,
                new Tile("Jail", 10, "Jail"),
                saintCharlesPlace,
                new PropertyTile("Electric company", 12, "utility",150, "4 or 10 times the dice roll", 2, "WHITE"),
                states,
                virginia,
                new PropertyTile("Pennsylvania RR", 15, railroad,200, "25", 4, black),
                saintJames,
                new Tile("Community Chest II", 17, "Community Chest II"),
                tennessee,
                newYork,
                new Tile("Free Parking", 20, "Free Parking"),
                kentuckyAvenue,
                new Tile("Chance II", 22, "Chance II"),
                indianaAvenue,
                illinoisAvenue,
                new PropertyTile("Baltimore and Ohio RR", 25, railroad,200, "25", 4, black),
                atlantic,
                ventnor,
                new PropertyTile("Water Works", 28, "utility", 150, "4 or 10 times the dice roll",2, "WHITE"),
                marvinGardens,
                new Tile("Go to Jail", 30, "Go to Jail"),
                pacific,
                northCarolrina,
                new Tile("Community chest III", 33, "Community chest III"),
                pennsylvania,
                new PropertyTile("Short Line RR", 35, railroad, 200, "25",4, black),
                new Tile("Chance III", 36, "Chance III"),
                parkPlace,
                new Tile("Luxury Tax", 38, "Luxury Tax"),
                boardwalk
        );
        tiles.addAll(tilesList);

        }

    protected SpecialCard findChanceCard(int index) {
        return chanceCards.get(index);
    }

    protected SpecialCard findCommunityCard(int index) {
        return communityCards.get(index);
    }

    protected int findPosition(String tileName) {
        for (int i = 0; i < tiles.size(); i = i+1 ){
            if (tiles.get(i).getName().equals(tileName)) {
                return i;
            }
        }
        return -1;
    }


    protected String findTileName(int position) {
        return this.tiles.get(position).getName();
    }

    protected Tile findTile(String tileName) {
        return tiles.get(findPosition(tileName));
    }

    protected Tile findTile(int position) {
        return tiles.get(position);
    }


    protected PropertyTile findPropertyTile(String tileName) {
        return (PropertyTile) tiles.get(findPosition(tileName));
    }

    protected StreetTile findStreetTile(String tileName) {
        return (StreetTile) tiles.get(findPosition(tileName));
    }

    protected StreetTile findStreetTile(int position) {
        return (StreetTile) tiles.get(position);
    }

    protected String findStreetColor(String tileName) {
        return findStreetTile(tileName).getColor();
    }

    protected List<String> findStreetsOfThisColor(String color) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < tiles.size(); i = i + 1) {
            if (findTile(i).getType().equals("street") && findStreetTile(i).getColor().equals(color) ) {
                result.add(findStreetTile(i).getName());
            }
        }
        return result;
    }

    protected List<String> findStreetsOfSameColor(String tileName) {
        List<String> result;
        result = findStreetsOfThisColor(findStreetColor(tileName));
        return result;

    }

    protected Boolean isThisTileNameDirectSale(String tileName) {
        int position = findPosition(tileName);
        return directSalePositions.get(position);
    }

    protected Boolean isThisPositionDirectSale(int position) {
        return directSalePositions.get(position);
    }

    protected void putThisPositionOutOfDirectSale(int position) {
        directSalePositions.set(position, false);
    }

    protected void putThisTileNameOutOfDirectSale(String tileName) {
        int position = findPosition(tileName);
        directSalePositions.set(position, false);
    }

    protected int getPropertyPrice(String tileName) {
        return findPropertyTile(tileName).getCost();
    }

    public List<Tile> returnTiles() {
        return tiles;
    }
}
