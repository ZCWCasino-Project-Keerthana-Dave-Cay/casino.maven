package com.github.zipcodewilmington.casino.games.gameUtils;

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

    public Stack<Cards> getCardDeck() {
        return cardDeck;
    }

    public Deck() {

    }

    public Deck(int numberOdDecks) {
        collectCard(numberOdDecks);
    }

    public void collectCard(int noOfDecks) {
        for (int i = 0; i < noOfDecks; i++) {
            for(Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    cardDeck.push(new Cards(rank,suit));
                }
            }
        }
        Collections.shuffle(cardDeck);
    }

    /*
    Gets the top card of the deck and removes it from the deck
     */

    public Cards drawCard(){

        if(!cardDeck.isEmpty()){
            Cards drawnCard = cardDeck.pop();
            return drawnCard;
        }
        else
            System.out.println("Deck is empty");
        return null;

    }


    public void setDeck(Stack<Cards> cardDeck) {
        this.cardDeck = cardDeck;
    }

    public Stack<Cards> getDeck() {
        return cardDeck;
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

     public List<Cards> drawMultipleCards(int numberOfCards) {
         List<Cards> newCards = new ArrayList<Cards>();
         for (int i = 0; i < numberOfCards; i++) {
             newCards.add(cardDeck.pop());
         }
         return newCards;
     }

        public Integer getSize() {
            return cardDeck.size();
    }


    public void shuffleDeck(){
        Collections.shuffle(cardDeck);
    }
    /* Removes all cards within the deck

     */
    public void clear() {
        cardDeck.clear();
    }


}
