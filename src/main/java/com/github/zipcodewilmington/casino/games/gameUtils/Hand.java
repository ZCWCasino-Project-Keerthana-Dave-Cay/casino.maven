package com.github.zipcodewilmington.casino.games.gameUtils;

import java.util.*;

public class Hand {

    private List<Cards> cardInHand = new ArrayList<Cards>();

    public void assignCard(Cards card){
        cardInHand.add(card);
    }

    public void assignCard(ArrayList<Cards> newCards){
        cardInHand.addAll(newCards);
    }

    public Cards getCard(){
        return cardInHand.get(0);
    }

}
