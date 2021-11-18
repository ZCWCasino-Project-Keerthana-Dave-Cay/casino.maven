package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.utils.AnsiColor;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class BigSixWheelEngine {
    //takes in BigWheelSiX (game class) and BigWheelSix Player to implements the IOConsole (inputs/outputs)

    private BigSixWheel bigSixWheel;
    private Random tileRandomizer;
    private Map<Integer, Integer> playerBets;
    private Scanner keyboard;
    private AnsiColor ansiColor;


    public BigSixWheelEngine(BigSixWheel bigSixWheel) {
        this.bigSixWheel = bigSixWheel;
        this.tileRandomizer = new Random();
        this.playerBets = new HashMap<>();
        this.keyboard = new Scanner(System.in);
    }



    public void start() {
        reset();
        //get player's bets
        boolean isPlayerSettingBet = true;
        int tileNumber;
        while (isPlayerSettingBet) {
            System.out.println("Welcome to the Big Six Wheel! \n Pick a tile from 1-7... (-1 to finish): ");
            tileNumber = keyboard.nextInt();
            if (tileNumber >= bigSixWheel.getPossibleWheelHits().size() || tileNumber < -1) {
                System.out.println("Invalid Input!");
            } else if (tileNumber == -1) {
                System.out.println("Player's Bets Finished");
                isPlayerSettingBet = false;
            } else {
                //TODO - set limits on just betting on 7 tiles
                //TODO - check account balance against player bets
                //TODO - enforce a min bet to the corresponding tile value
                //TODO - create an exit method
                System.out.println("Enter Bet Amount: ");
                int betAmount = keyboard.nextInt();
                if (betAmount < 0) { //no negative bets
                    System.out.println("Invalid Input!");
                } else {
                    playerBets.put(tileNumber, playerBets.getOrDefault(tileNumber, 0) + betAmount);
                }
            }
        }
        //play game
        Integer results = spinWheel();

        //collect winnings
        payPlayer(results);

        //prompt me if i would like to continue?
        System.out.println("Would you to continue?");
        String y = keyboard.next();
        if( y.equalsIgnoreCase("YES")){
            start();
        }

    }


    public void payPlayer(int winningTile) {
        int payOut = 0;
        if (playerBets.getOrDefault(winningTile, 0) > 0) {
            payOut += playerBets.get(winningTile) * bigSixWheel.getPossibleWheelHits().get(winningTile);
            System.out.println("Paying Player $" + payOut + " in Winnings ");
        }
        else{
            System.out.println("You did not win!");
            System.out.println("Paying Player $" + payOut);
        }
        bigSixWheel.getPlayer().getArcadeAccount().addWinningsToBalance(payOut);
        //loop back to pick your tile instead of exiting the game

    }



    public void reset(){
        playerBets.clear();
    }



    public Integer spinWheel () {

        int randomElement = bigSixWheel.getPossibleWheelHits().get(tileRandomizer.nextInt(bigSixWheel.getPossibleWheelHits().size()));

        return randomElement;
    }


}
