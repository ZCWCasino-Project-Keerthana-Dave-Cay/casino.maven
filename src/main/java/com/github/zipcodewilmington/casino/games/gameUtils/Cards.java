package com.github.zipcodewilmington.casino.games.gameUtils;

import org.junit.jupiter.api.Assertions;

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

<<<<<<< HEAD
=======
    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

>>>>>>> 8f159e1f093d57019f563ecefde343f6e5cca8f9
    public Suit getSuit(){
        return suit;
    }

    public Rank getRank(){
        return rank;
    }

    @Override
    public String toString() {
        return this.suit.getGraphic() + this.rank.getPrimaryValue();
    }

}
