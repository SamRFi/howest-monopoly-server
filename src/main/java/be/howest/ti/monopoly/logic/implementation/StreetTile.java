package be.howest.ti.monopoly.logic.implementation;

public class StreetTile extends PropertyTile {
    private String color;
    private String streetcolor;
    private String rent;
    private int housePrice;
    private int rentWithOneHouse;
    private int rentWithTwoHouses;
    private int rentWithThreeHouses;
    private int rentWithFourHouses;
    private int rentWithHotel;

    public StreetTile(String name, int position,  int cost, String rent, int groupSize, String color) {
        super( name, position, "street", cost, rent, groupSize, color);
        this.color = color;
        this.rent = rent;
        this.streetcolor = color;
    }

    public int rentWithHouses(int amountOfHouses) {
        if (amountOfHouses == 1) {return rentWithOneHouse;}
        if (amountOfHouses == 2) {return rentWithTwoHouses;}
        if (amountOfHouses == 3) {return rentWithThreeHouses;}
        if (amountOfHouses == 4) {return rentWithFourHouses;}
        return Integer.parseInt(this.rent);
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public void setHousePrice(int housePrice) {
        this.housePrice = housePrice;
    }

    public void setRentWithOneHouse(int rentWithOneHouse) {
        this.rentWithOneHouse = rentWithOneHouse;
    }

    public void setRentWithTwoHouses(int rentWithTwoHouses) {
        this.rentWithTwoHouses = rentWithTwoHouses;
    }

    public void setRentWithThreeHouses(int rentWithThreeHouses) {
        this.rentWithThreeHouses = rentWithThreeHouses;
    }

    public void setRentWithFourHouses(int rentWithFourHouses) {
        this.rentWithFourHouses = rentWithFourHouses;
    }

    public void setRentWithHotel(int rentWithHotel) {
        this.rentWithHotel = rentWithHotel;
    }

    @Override
    public String getRent() {
        return rent;
    }

    public int getHousePrice() {
        return housePrice;
    }

    public int getRentWithOneHouse() {
        return rentWithOneHouse;
    }

    public int getRentWithTwoHouses() {
        return rentWithTwoHouses;
    }

    public int getRentWithThreeHouses() {
        return rentWithThreeHouses;
    }

    public int getRentWithFourHouses() {
        return rentWithFourHouses;
    }

    public int getRentWithHotel() {
        return rentWithHotel;
    }

    public String getStreetcolor() {
        return streetcolor;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "StreetTile{" +
                "position=" + getPosition() +
                ", name=" + getName() +
                ", type=" + getType() +
                ", nameAsPathParameter=" + getNameAsPathParameter() +
                ", cost=" + getCost() +
                ", mortage=" + getMortgage() +
                ", rent='" + rent + '\'' +
                ", groupsize=" + getGroupSize() +
                ", color=" + color +
                ", streetcolor=" + streetcolor +
                ", housePrice=" + housePrice +
                ", rentWithOneHouse=" + rentWithOneHouse +
                ", rentWithTwoHouses=" + rentWithTwoHouses +
                ", rentWithThreeHouses=" + rentWithThreeHouses +
                ", rentWithFourHouses=" + rentWithFourHouses +
                ", rentWithHotel=" + rentWithHotel +
                '}';
    }
}
