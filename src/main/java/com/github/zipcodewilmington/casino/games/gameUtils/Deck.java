package com.github.zipcodewilmington.casino.games.gameUtils;

import com.sun.xml.internal.bind.v2.TODO;

import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {
//    List<Card> deckList = new ArrayList<>();
    //reset deck

    //shuffle cards

    //draw a card - must be stack method
    Stack<Cards> cardDeck = new Stack<>();


    public Deck() {
        for(Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cardDeck.push(new Cards(suit,rank));
            }
        }
    }

    /*
    Gets the top card of the deck and removes it from the deck
     */

    public Cards draw(){

        return cardDeck.pop();

    }

   /*Adds a card to the top of the Deck
     * @param card the Card to put on top of the deck

    */
    public void add(Cards card){
         cardDeck.push(card);
    }

    /*Adds an array of cards to the top of the deck
     * @param cards an array of Cards

     */
    public void add(Cards[] cards) {
        for(Cards card : cards)
            cardDeck.push(card);
    }
    /*
    draws multiple cards from the deck
     */

//    public void drawMultipleCards(Cards[] cards) {
//        for(Cards card : cards)
//            cardDeck.pop();
//
//    }
    public List<Cards> drawMultipleCards(int numberOfCards) {
        List<Cards> newCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            newCards.add(cardDeck.pop());
        }
        return newCards;
    }

    public void shuffleDeck(){
        Collections.shuffle(cardDeck);
    }
    /* Removes all cards within the deck

     */
    public void clear() {
        cardDeck.clear();
    }

   /*
   check to see if there are any cards in the deck
    */
    public Boolean isEmpty(){
        return cardDeck.isEmpty();
    }

}
