package com.github.zipcodewilmington.casino.games.gameUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {

    Deck newDeckOfCards;

    public Deck() {

    }

    //    List<Card> deckList = new ArrayList<>();
    //reset deck

    //shuffle cards

    //draw a card - must be stack method
    Stack<Card> cardDeck = new Stack<>();

    public Stack<Card> getCardDeck() {
        return cardDeck;
    }



    public Deck(int numberOdDecks) {
        collectCard(numberOdDecks);
    }

    public void collectCard(int noOfDecks) {
        for (int i = 0; i < noOfDecks; i++) {
            for(Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    cardDeck.push(new Card(rank,suit));
                }
            }
        }
        Collections.shuffle(cardDeck);
    }

    /*
    Gets the top card of the deck and removes it from the deck
     */

    public Card drawCard(){
        if(!cardDeck.isEmpty()){
            Card drawnCard = cardDeck.pop();
            return drawnCard;
        } else {
            System.out.println("Deck is empty");
        }
        return null;

    }

    public void setDeck(Stack<Card> cardDeck) {
        this.cardDeck = cardDeck;
    }

    public Stack<Card> getDeck() {
        return cardDeck;
    }

   /*Adds a card to the top of the Deck
     * @param card the Card to put on top of the deck

    */
    public void add(Card card){
         cardDeck.push(card);
    }

    /*Adds an array of cards to the top of the deck
     * @param cards an array of Cards

     */
    public void add(Card[] cards) {
        for(Card card : cards)
            cardDeck.push(card);
    }

    public void add(List<Card> cards){
        for(Card card : cards)
            cardDeck.push(card);
    }

    /*
    draws multiple cards from the deck
     */

     public List<Card> drawMultipleCards(int numberOfCards) {
         List<Card> newCards = new ArrayList<Card>();
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
