package be.howest.ti.monopoly.logic.implementation;

public class Tile {
    private final String name;
    private final int position;
    private String type;
    private String nameAsPathParameter;

    public Tile( String name,int position, String type) {
        this.name = name;
        this.position = position;
        this.nameAsPathParameter = name.replace(" ", "_");
        this.type = type;
    }
    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }

    public String getNameAsPathParameter() {
        return nameAsPathParameter;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "position=" + position +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", nameAsPathParameter='" + nameAsPathParameter + '\'' +
                '}';
    }
}
