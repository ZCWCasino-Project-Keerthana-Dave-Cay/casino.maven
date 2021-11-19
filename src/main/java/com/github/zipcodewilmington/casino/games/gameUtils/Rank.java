package com.github.zipcodewilmington.casino.games.gameUtils;

public enum Rank {
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7),
    Eight(8),
    Nine(9),
    Ten(10),
    Jack(11),
    Queen(12),
    King(13),
    Ace(1, 11);

    private final Integer primaryValue;
    private final Integer secondaryValue;

    Rank(Integer value){
        this.primaryValue = value;
        this.secondaryValue = value;
    }

    Rank(Integer primaryValue, Integer secondaryValue) {
        this.primaryValue = primaryValue;
        this.secondaryValue = secondaryValue;
    }

    public Integer getPrimaryValue(){

        return this.primaryValue;
    }

    public Integer getSecondaryValue(){
        return this.secondaryValue;
    }

    public String getDisplayValue() {
        String rankDisplay = "";
        switch (primaryValue) {
            case 1: rankDisplay = "A";
                    break;
            case 11: rankDisplay = "J";
                    break;
            case 12: rankDisplay = "Q";
                    break;
            case 13: rankDisplay = "K";
                    break;
            default: rankDisplay = Integer.toString(primaryValue);
        }
        return rankDisplay;
    }

    public Integer getBlackJackValue(){
        Integer valueDisplay;
        switch (primaryValue) {
            case 1: valueDisplay = 1;
                    break;
            case 11:
            case 12:
            case 13:
                valueDisplay = 10;
                    break;
            default: valueDisplay = primaryValue;
        }
        return valueDisplay;
    }
}
//each item is public static final