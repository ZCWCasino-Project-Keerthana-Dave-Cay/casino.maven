package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.gameUtils.Cards;
import com.github.zipcodewilmington.casino.games.gameUtils.Rank;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

public class WarEngine {

    //takes in War (game class) and War Player to implements the IOConsole (inputs/outputs)
    private IOConsole defaultConsole;
    private IOConsole cardConsole;
    private IOConsole resultConsole;
    private PlayerInterface dealer;
    private PlayerInterface player;

    private String PLAYER = "PLAYER";
    private String DEALER = "DEALER";
    private String TIE = "TIE";


    public WarEngine(PlayerInterface dealer, PlayerInterface warPlayer) {
        this.defaultConsole = new IOConsole(AnsiColor.YELLOW);
        this.cardConsole = new IOConsole(AnsiColor.CYAN);
        this.resultConsole = new IOConsole(AnsiColor.GREEN);
        this.dealer = dealer;
        this.player = warPlayer;
    }

    public void startGame() {

        defaultConsole.println("###  WELCOME TO WAR GAME  ###");
        do {

            Integer betAmount = defaultConsole.getIntegerInput("Please enter BET amount:");
            Integer tieAmount = defaultConsole.getIntegerInput("Please enter TIE amount (if interested):");

            while (player.getArcadeAccount().getAccountBalance() == null || (betAmount + tieAmount) >= player.getArcadeAccount().getAccountBalance()) {
                defaultConsole.println("Player Account Balance: %s \nInsufficient Balance to continue the game!", player.getArcadeAccount().getAccountBalance());
                player.getArcadeAccount().setBalance(player.getArcadeAccount().getAccountBalance() + defaultConsole.getIntegerInput("Please enter amount to load into your account:"));
                defaultConsole.println("Player Account Balance: %s", player.getArcadeAccount().getAccountBalance());
            }

            if ("N".equalsIgnoreCase(defaultConsole.getStringInput("Do you like to make a DEAL (Y/N)?")))
                continue;

            startDrawing(betAmount, tieAmount, false);

            defaultConsole.println("Player - Account Balance: %s", player.getArcadeAccount().getAccountBalance());
        }
        while ("Y".equalsIgnoreCase(defaultConsole.getStringInput("Do you like to continue the game (Y/N)?")));

        defaultConsole.println("###  THANK YOU! HOPE YOU ENJOYED WAR GAME  ###");
    }

    private void startDrawing(int betAmount, int tieAmount, boolean isWarRound) {

        Cards playerCard = player.play();
        defaultConsole.println("Player has drawn card from DECK; PLAYER CARD: ");
        displayCard(playerCard);

        Cards dealerCard = dealer.play();
        defaultConsole.println("Dealer has drawn card from DECK; DEALER CARD: ");
        displayCard(dealerCard);

        String winnerStatus = whoIsWinner(playerCard, dealerCard, isWarRound);

        adjustAccountBalance(winnerStatus, betAmount, tieAmount);

        if (TIE.equalsIgnoreCase(winnerStatus))  {

            Integer warOption = defaultConsole.getIntegerInput("Please choose the below options: \n 1) WAR 2) FOLD / SURRENDER");
            if (1 == warOption) {
                defaultConsole.println("PLAYER choose to WAR");
                defaultConsole.println("Draw three cards from Deck and keep aside");
                // Check for player interface to add additional method or get deck from player then keep card aside
                dealer.play();
                dealer.play();
                dealer.play();
                startDrawing(betAmount * 2, 0, true);
            } else {
                defaultConsole.println("PLAYER choose to SURRENDER");
                resultConsole.println("PLAYER LOST THE GAME! Deduced %s (Half of Bet Amount %s)", betAmount / 2, betAmount);
                player.getArcadeAccount().subtractBetAmountFromBalance(betAmount / 2);
            }
        }
    }

    public String whoIsWinner(Cards playerCard, Cards dealerCard, boolean isWarRound) {

        String winner;
        if (playerCard.getRank().getSecondaryValue() > dealerCard.getRank().getSecondaryValue()) {
            winner = PLAYER;
        } else if (playerCard.getRank().getSecondaryValue() < dealerCard.getRank().getSecondaryValue()) {
            winner = DEALER;
        } else {
            winner = isWarRound ? PLAYER : TIE;
        }
        return winner;
    }

    public void adjustAccountBalance(String winner, Integer betAmount, Integer tieAmount) {

        if (PLAYER.equalsIgnoreCase(winner)) {

            resultConsole.println("PLAYER WON THE GAME !!!");
            resultConsole.println("PLAYER collects the Bet Amount: %s and added to the account", betAmount);
            player.getArcadeAccount().addWinningsToBalance(betAmount);
            deductTieAmountIfEligible(tieAmount);
        } else if (DEALER.equalsIgnoreCase(winner)) {
            resultConsole.println("DEALER WON !!!");
            resultConsole.println("PLAYER lost the Bet Amount: %s and deduced from the account", betAmount);
            player.getArcadeAccount().subtractBetAmountFromBalance(betAmount);
            deductTieAmountIfEligible(tieAmount);
        } else {
            defaultConsole.println("ITS A TIE !!!!!!!");
            resultConsole.println("PLAYER collects Tie Amount: %s and added to the account", tieAmount);
            player.getArcadeAccount().addWinningsToBalance(tieAmount);
        }
    }

    public void deductTieAmountIfEligible(Integer tieAmount) {

        if (tieAmount > 0) {
            resultConsole.println("PLAYER lost the Tie Amount: %s and deduced from the account", tieAmount);
            player.getArcadeAccount().subtractBetAmountFromBalance(tieAmount);
        }
    }

    private void displayCard(Cards card) {
        cardConsole.println("-------------");
        cardConsole.println("|\t\t%s  |", getDisplayValue(card.getRank()));
        cardConsole.println("| \t  %s   \t|", card.getSuit().getGraphic());
        cardConsole.println("| %s\t\t|", getDisplayValue(card.getRank()));
        cardConsole.println("------------\n");
    }

    private String getDisplayValue(Rank rank) {
        String displayValue = null;
        if (rank.getSecondaryValue() <= 9) {
            displayValue = " " + rank.getSecondaryValue();
        }
        else if (rank.getSecondaryValue() == 10) {
            displayValue = "" + rank.getSecondaryValue();
        }
        else {
            displayValue = " " + rank.name().substring(0,1).toUpperCase();
        }

        return displayValue;
    }


}
