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
    private Map<Integer, Integer> playerBets, payOutMap;
    private Scanner keyboard;
    private final int NUM_RANGES = 6;
    private AnsiColor ansiColor;


    public BigSixWheelEngine(BigSixWheel bigSixWheel) {
        this.bigSixWheel = bigSixWheel;
        this.tileRandomizer = new Random();
        this.playerBets = new HashMap<>();
        this.keyboard = new Scanner(System.in);
        this.payOutMap = bigSixWheel.getPayOuts();
        System.out.println("Welcome to the Big Six Wheel! \n");
    }



    public void start() {
        reset();
        //get player's bets
        boolean isPlayerSettingBet = true;
        int rangeNumber;
        System.out.println("Pick from these ranges: \n  [0-23]:$1 [24-38]:$2  [39-45]:$5  [46-49]:$10  [50-51]:$20 [52-53]:Bonus");
        while (isPlayerSettingBet) {
            for(Map.Entry<Integer,Integer> playerRangeBet: playerBets.entrySet()){
                System.out.println("Range " + playerRangeBet.getKey() + ": Value = $" + payOutMap.get(playerRangeBet.getKey()) + ", Current Bet = $" + playerRangeBet.getValue());
            }
            System.out.println("Enter Tile Number (-1 to finish): ");
            rangeNumber = keyboard.nextInt();

            if (rangeNumber == -1) {
                System.out.println("Player's Bets Finished");
                isPlayerSettingBet = false;
            } else if(payOutMap.get(rangeNumber) == null) {
                System.out.println("Invalid Input!");
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
                    playerBets.put(rangeNumber, playerBets.getOrDefault(rangeNumber, 0) + betAmount);
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
            payOut += playerBets.get(winningTile) * payOutMap.get(bigSixWheel.getPossibleWheelHits().get(winningTile));
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
