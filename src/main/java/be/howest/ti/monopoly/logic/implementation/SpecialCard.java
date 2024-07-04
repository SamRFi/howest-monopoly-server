package be.howest.ti.monopoly.logic.implementation;


public class SpecialCard {
    private final String description;
    private final String actionOfSpecialCard;

    public SpecialCard(String description, String actionOfSpecialCard) {
        this.description = description;
        this.actionOfSpecialCard = actionOfSpecialCard;
    }

    public String getDescription() {
        return description;
    }
    public String getActionOfSpecialCard(){
        return actionOfSpecialCard;
    }
}
