package be.howest.ti.monopoly.logic.implementation;

public class PropertyTile extends Tile {
    private int cost;
    private int mortgage;
    private String rent;
    private int groupSize;
    private String color;

    public PropertyTile(String name, int position, String type, int cost, String rent, int groupSize, String color) {
        super(name, position,  type);
        this.cost = cost;
        this.mortgage = cost / 2;
        this.rent = rent;
        this.groupSize = groupSize;
        this.color = color;
    }

    public int getCost() {
        return cost;
    }

    public int getMortgage() {
        return mortgage;
    }

    public String getRent() {
        return rent;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public String getColor() {
        return color;
    }


    @Override
    public String toString() {
        return "PropertyTile{" +
                "name=" + getName() +
                ", position=" + getPosition() +
                ", type=" + getType() +
                ", nameAsPathParameter=" + getNameAsPathParameter() +
                ", cost=" + cost +
                ", mortgage=" + mortgage +
                ", rent='" + rent + '\'' +
                ", groupSize=" + groupSize +
                ", color='" + color + '\'' +
                '}';
    }
}
