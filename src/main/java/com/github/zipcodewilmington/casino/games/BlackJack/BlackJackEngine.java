package com.github.zipcodewilmington.casino.games.BlackJack;

import com.github.zipcodewilmington.casino.games.gameUtils.*;
import com.github.zipcodewilmington.utils.AnsiColor;
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
    IOConsole ansiColorConsole;

    final String BLACKJACK_RULES = "1. The goal of the game is to beat the dealer's hand without going over 21. \n" +
            "2. Face cards are worth 10. \n" +
            "3. Each player starts with two cards, one of the dealer's cards will be hidden until the end. \n" +
            "4. To 'Hit' is to ask for another card. \n" +
            "5. If you go over 21, you bust and the dealer wins regardless of their hand.\n" +
            "6. If you are dealt 21 from the start (Ace and 10), you got a Blackjack! \n" +
            "7. If you win the hand you win 1x your bet, if you get a BlackJack, you win 2x your bet. \n" +
            "8. If your card value is equal to the dealer (Push), bet amount is returned.";

    public Hand getDealerBJHand() {
        return dealerBJHand;
    }

    public Hand getPlayerBJHand() {
        return playerBJHand;
    }

    public BlackJackEngine(BlackJackPlayer dealer, BlackJackPlayer player, IOConsole console) {
        this.dealer = dealer;
        this.player = player;
        this.console = console;
        this.gameDeck = new Deck(8);
        this.players = new ArrayList<>();
        this.dealerBJHand = new Hand();
        this.playerBJHand = new Hand();
        this.ansiColorConsole = new IOConsole(AnsiColor.CYAN);
    }

    public void startBJGame() {
        //to start: players, deck,
        boolean restartGame = false;
        System.out.println("♥◆♧♠ Welcome to BlackJack! ♠♧◆♥");
        displayRules();
        welcomePlayer();
        do {
            int bet = betCycle();
            initializeDealerHand();
            initializePlayerHand();
            boolean didPlayerBust = false;
            if (!isBlackJack(playerBJHand)) {
                didPlayerBust = !playerHitStandCycle(playerBJHand);
            }
            dealersTurn();
            determineWinner(didPlayerBust, bet);
            // restart game? y/n
            restartGame = promptRestartGame();
        } while (restartGame);
    }

    //create a new method that says display rules
    public void displayRules() {
        String userInput =
                console.getStringInput("Would you like to see the rules? \n " +
                        "YES/NO", "YES", "NO");
        if (userInput.equals("YES")) {
            System.out.println(BLACKJACK_RULES);
        }
    }

    public void welcomePlayer() {
        System.out.printf("\n \n Welcome to the table, %s%n", player.getArcadeAccount().getAccountName());
    }

    // bet cycle to validate bet amount before game
    public int betCycle() {
        Integer bet = 0;
        boolean isValidBet = false;
        do {
            bet = getUserBetAmount();
            if (checkBet(bet)) {
                isValidBet = true;
            }
        } while (!isValidBet);

        return bet;
    }
    public Integer getUserBetAmount() {
        System.out.printf("Current account balance: $%d \n", player.casinoAccount.getPlayerBalance());
        return console.getIntegerInput("How much would you like to bet? \n", "");
    }
    public boolean checkBet(Integer bet) {
        if (bet <= player.casinoAccount.getPlayerBalance()) {
            System.out.println("Bet accepted!");
            player.casinoAccount.subtractBetAmountFromBalance(bet);
            return true;
        }
        System.out.println("Insufficient funds, try again!");
        return false;
    }
    public void initializePlayerHand() {
        List<Cards> initialTwoCardHand = gameDeck.drawMultipleCards(2);
        playerBJHand.add(initialTwoCardHand);
        System.out.println("Your hand is " + playerBJHand.toString());
        displayHandTotal(playerBJHand);
    }
    //dealers hand but second is hidden
    public void initializeDealerHand() {
        //i need 2 cards from gameDeck, so grab 2 cards
        List<Cards> initialTwoCardHand = gameDeck.drawMultipleCards(2);
        dealerBJHand.add(initialTwoCardHand);
        System.out.println("\nDealer's hand is " + dealerBJHand.displayAllButFirst() + ", [???]");
    }
    public void displayHandTotal(Hand hand) {
        System.out.println("The total of your hand is " + getBlackJackHandTotal(hand));
    }

    public boolean validateTotal(Hand hand) {
        return  (getBlackJackHandTotal(hand) <= 21);
    }
    public boolean playerHitStandCycle(Hand hand) {
        String userDecision;
        boolean isValidHandTotal;
        do {
            userDecision = hitOrStand();
            isValidHandTotal = validateTotal(hand);
        } while (!userDecision.equals("STAND") && isValidHandTotal);
        if (!isValidHandTotal) {
            System.out.println("Oh no, you busted your hand! You lose.");
        }

        return isValidHandTotal;
    }
    public String hitOrStand() {
        String userInput = console.getStringInput("Would you like to HIT or STAND?", "HIT", "STAND");
        if (userInput.equals("HIT")) {
            hitAction(playerBJHand);
        } else if (userInput.equals("STAND")) {
            System.out.println("Player chose to stand.");
        } else {
            System.out.println("Invalid action, try again...");
        }
        return userInput;
    }
    public void hitAction(Hand hand) {
        System.out.println("Drawing a card... \n\n");
        Cards hitDraw = gameDeck.drawCard();
        System.out.println("New Card: " + hitDraw.toString());
        hand.add(hitDraw);
        displayHandTotal(hand);
    }
    public void dealerHitOrStandCycle() {
        boolean action = false;
        Integer handTotal = getBlackJackHandTotal(dealerBJHand);
        do {
            if (handTotal <= 16) {
                // hit card
                dealerBJHand.add(gameDeck.drawCard());
                handTotal = getBlackJackHandTotal(dealerBJHand);
                if (handTotal > 16 && handTotal <= 21) {
                    action = false;
                } else if (handTotal <= 16) {
                    action = true;
                } else if (handTotal > 21) {
                    System.out.println("Dealer busted!!!!");
                    action = false;
                }
            }
        } while (action);
    }
    public void dealersTurn() {
        isBlackJack(dealerBJHand);
        if (isBlackJack(dealerBJHand)) {
            System.out.println("Dealer got BlackJack!");
        } else {
            dealerHitOrStandCycle();
        }
    }
    public boolean isBlackJack(Hand hand) {
        return (hand.getSize() == 2) && (getBlackJackHandTotal(hand) == 21);
    }
    public void determineWinner(boolean playerBust, int bet){
        System.out.println("Turns have ended! Let's determine the winner...\n");
        System.out.printf("Player's hand: %s, Player's total: %d\n\n", playerBJHand.toString(), getBlackJackHandTotal(playerBJHand));
        if (playerBust) {
            System.out.println("You bust, therefore you lose your initial bet! \n");
        } else {
            System.out.printf("Dealer's hand: %s, Dealer's total: %d\n\n", dealerBJHand.toString(), getBlackJackHandTotal(dealerBJHand));
            distributeWinnings(bet);
        }
    }

    public void distributeWinnings(int bet) {
        Integer dealerTotal = getBlackJackHandTotal(dealerBJHand);
        Integer playerTotal = getBlackJackHandTotal(playerBJHand);
        if (dealerTotal.intValue() == playerTotal.intValue()) {
            System.out.printf("Outcome: Push! Initial Bet of $%d returned to Casino Balance. \n", bet);
            player.casinoAccount.addWinningsToBalance(bet);
        } else if (dealerTotal > playerTotal && dealerTotal <= 21) {
            System.out.printf("Outcome: You lost! Initial Bet of $%d removed from Casino Balance. \n", bet);
        } else if (dealerTotal < playerTotal) {
            System.out.printf("Outcome: You won! Initial Bet of %d is doubled! %d added to Casino Balance. \n", bet, (bet * 2));
        } else if(dealerTotal > 21) {
            System.out.printf("Dealer busted, you win! Initial Bet of %d is double! %d added to Casino Balance. \n", bet, bet);
        }
    }
    //playing again?
    public boolean promptRestartGame(){
        String userInput = console.getStringInput("Do you want to play again? YES/NO", "YES", "NO");
        if (userInput.equals("YES")){
            gameDeck = new Deck(8);
            playerBJHand.clear();
            dealerBJHand.clear();
            return true;
        } else {
            return false;
        }
    }
    public Integer getBlackJackHandTotal(Hand hand){
        Integer total = 0;
        int aceCount = 0;
        // check if hand has an ACE
        for (Cards card: hand.getCardDeck()) {
            if (card.getRank().equals(Rank.Ace)) {
                aceCount++;
            } else {
                total += card.getRank().getBlackJackValue();
            }
        }
        for (int i = 0; i < aceCount; i++) {
            if (total + 11 > 21) {
                total += Rank.Ace.getPrimaryValue();
            } else {
                total += Rank.Ace.getSecondaryValue();
            }
        }
        return total;
    }
}

