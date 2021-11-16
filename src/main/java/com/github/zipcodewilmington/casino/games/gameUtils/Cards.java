package com.github.zipcodewilmington.casino.games.gameUtils;

import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.List;

public class Cards {

    private Suit suit;
    private Rank rank;
    public Cards(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Suit getSuit(){
        return suit;
    }

    public Rank getRank(){
        return rank;
    }


}
