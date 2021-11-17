package com.github.zipcodewilmington.casino.games.gameUtils;

public class Cards {

    private Suit suit;
    private Rank rank;
    public Cards(Rank rank, Suit suit) {
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

    public String valueOfCard(){
        return this.rank.toString() + this.suit.toString();
    }
}
