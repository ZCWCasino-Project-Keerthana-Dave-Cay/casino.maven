package com.github.zipcodewilmington.casino.games.BlackJack;

import com.github.zipcodewilmington.casino.games.gameUtils.*;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.ArrayList;
import java.util.List;

public class BlackJackEngine {
    IOConsole console;
    BlackJackPlayer dealer;
    BlackJackPlayer player;
    Deck gameDeck;
    List<BlackJackPlayer> players;
    Hand dealerBJHand;
    Hand playerBJHand;

    public BlackJackEngine(BlackJackPlayer dealer, BlackJackPlayer player) {
        this.dealer = dealer;
        this.player = player;
        this.console = new IOConsole();
        this.gameDeck = new Deck(8);
        this.players = new ArrayList<>();
        this.dealerBJHand = new Hand();
        this.playerBJHand = new Hand();
    }

    //when they first start game, sout what the rules/actions
    //working on methods so far
    public void startBJGame() {
        //to start: players, deck,
        System.out.println("Welcome to BlackJack!");
        displayRules();
        welcomePlayer();
        betCycle();
        // initial deal for both player and dealer
        initialPlayersHand();
        initialDealersHand();
        // player turn
        // dealer turn
        // declare winner

        // restart game? y/n
    }

    //create a new method that says display rules
    public void displayRules() {
        String userInput =
                console.getStringInput("Would you like to see the rules? \n " +
                        "YES/NO", "YES", "NO");

        if (userInput.equals("YES")) {
            System.out.println("1. The goal of the game is to beat the dealer's hand without going over 21. \n" +
                    "2. Face cards are worth 10. \n" +
                    "3. Each player starts with two cards, one of the dealer's cards will be hidden until the end. \n" +
                    "4. To 'Hit' is to ask for another card. \n" +
                    "5. If you go over 21, you bust and the dealer wins regardless of their hand.\n" +
                    "6. If you are dealt 21 from the start (Ace and 10), you got a Blackjack! \n" +
                    "7. If you win the hand you win 1x your bet, if you get a BlackJack, you win 2x your bet. \n" +
                    "8. If your card value is equal to the dealer (Push), bet amount is returned.");
        }
    }

    //list players in game
    private void welcomePlayer() {
        System.out.printf("Welcome to the table, %s%n", player.getArcadeAccount().getAccountName());
    }

    // bet cycle to validate bet amount before game
    private void betCycle() {
        boolean isValidBet = false;
        do {
            Integer bet = getUserBetAmount();
            if (checkBet(bet)) {
                isValidBet = true;
            }
        } while (!isValidBet);
    }

    //promt how much they want ot bet??
    private Integer getUserBetAmount() {
        return console.getIntegerInput("How much would you like to bet? \n", "");
    }

    //check balance to see if player had enough
    private boolean checkBet(Integer bet) {
        if (bet <= player.casinoAccount.getPlayerBalance()) {
            System.out.println("Bet accepted!");
            return true;
        }
        System.out.println("Insufficient funds, try again!");
        return false;
    }

    //deal initial hands for player/dealer
    private void initialPlayersHand() {
        //i need 2 cards from gameDeck, so grab 2 cards
        List<Card> initialTwoCardHand = gameDeck.drawMultipleCards(2);
        playerBJHand.add(initialTwoCardHand);
        System.out.println("Your hand is " + playerBJHand.toString());
        System.out.println("The total of your hand is " + playerBJHand.calcHandBJScore());
    }

    //dealers hand but second is hidden
    private void initialDealersHand() {
        //i need 2 cards from gameDeck, so grab 2 cards
        List<Card> initialTwoCardHand = gameDeck.drawMultipleCards(2);
        dealerBJHand.add(initialTwoCardHand);
        System.out.println("Dealer's hand is " + dealerBJHand.displayAllButFirst() + ", [???]");
    }

    //method hit if no hit stand
    private void hitOrStand() {
        String userInput = console.getStringInput("Would you like to HIT or STAND?", "HIT", "STAND");
        //they need to be able ot repeat it multiple times
        if (userInput.equals("HIT")) {
            playerBJHand.add(gameDeck.drawCard());
        } else if (userInput.equals("STAND")) {
            //move to the dealers turn
        }
    }

    //build players/dealers turn
    private void playersTurn() {
        hitOrStand();
        //total up the players card values???
        //players turn will hit or stand
        //then keep prompting to hit until stand input while loop
        while (userInput != "STAND") {

        }
    }

    private void dealersTurn() {
        // if dealers hand is less than 16, dealer should automatically hit
        if(dealerBJHand.calcHandBJScore() < 16){
            dealerBJHand.add(gameDeck.drawCard());
        } else if (dealerBJHand.calcHandBJScore() < playerBJHand.calcHandBJScore()){
            dealerBJHand.add(gameDeck.drawCard());
        } //do i need to do anything else?
    }
    //build players/dealers turn

    //get winner take total of each hand and compare
}

