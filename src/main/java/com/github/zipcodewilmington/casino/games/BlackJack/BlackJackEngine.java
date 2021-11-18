package com.github.zipcodewilmington.casino.games.BlackJack;
import com.github.zipcodewilmington.casino.games.gameUtils.Deck;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.ArrayList;
import java.util.List;

public class BlackJackEngine {
    IOConsole console;
    BlackJackPlayer dealer;
    BlackJackPlayer player;
    Deck gameDeck;
    List<BlackJackPlayer> players;


    public BlackJackEngine(BlackJackPlayer dealer, BlackJackPlayer player) {
        this.dealer = dealer;
        this.player = player;
        this.console = new IOConsole();
        this.gameDeck = new Deck(1);
        this.players = new ArrayList<>();

    }

    //when they first start game, sout what the rules/actions
    //working on methods so far

    public void startBJGame(){
        //to start: players, deck,
        System.out.println("Welcome to BlackJack!");
        displayRules();
        listingPlayers();
    }

    //create a new method that says display rules
    public void displayRules(){
        String userInput =
        console.getStringInput("Would you like to see the rules? \n " +
                "YES/NO", "YES", "NO");

        if (userInput.equals("YES")){
            System.out.println("1. The goal of the game is to beat the dealer's hand without going over 21. \n" +
                    "2. Face cards are worth 10. \n" +
                    "3. Each player starts with two cards, one of the dealer's cards will be hidden until the end. \n" +
                    "4. To 'Hit' is to ask for another card. \n" +
                    "5. If you go over 21, you bust and the dealer wins regardless of their hand.\n" +
                    "6. If you are dealt 21 from the start (Ace and 10), you got a Blackjack! \n" +
                    "7. If you win the hand you win 1x your bet, if you get a BlackJack, you win 2x your bet. \n" +
                    "8. Dealer will hit until their card total is 17 or higher.");
        }
    }

    //list players in game
    public void listingPlayers() {
        players.add(dealer);
        players.add(player);
        System.out.println(players.toString());
    }

}
