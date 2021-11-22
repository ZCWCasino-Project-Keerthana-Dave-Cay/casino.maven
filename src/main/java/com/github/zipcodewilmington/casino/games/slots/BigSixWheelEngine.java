package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

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
    private IOConsole consolePurple;
    private IOConsole consoleCyan;


    public BigSixWheelEngine(BigSixWheel bigSixWheel, IOConsole console) {
        this.bigSixWheel = bigSixWheel;
        this.tileRandomizer = new Random();
        this.playerBets = new HashMap<>();
        this.keyboard = new Scanner(System.in);
        this.payOutMap = bigSixWheel.getPayOuts();
        this.consolePurple = new IOConsole(AnsiColor.PURPLE);
        this.consoleCyan = new IOConsole(AnsiColor.CYAN);//constructor
        this.consolePurple.println("Welcome to the Big Six Wheel! \n");
    }

    //TODO - create a way to check account balance against player bets
    //TODO - enforce a min bet to the corresponding tile value
    //TODO - negative inputs need to be checked for
    //TODO - make some color edits? Create a method for color changes?
    public void start() {
        reset();
        //get player's bets
        boolean isPlayerSettingBet = true;
        int rangeNumber;
        consolePurple.println("Pick Your Numbers: \n  [$1 = 1:1] [$2 = 2:1] [$5 = 5:1] [$10 = 10:1] [$20 = 20:1] [$40 = 40:1]");
        while (isPlayerSettingBet) {
            for (Map.Entry<Integer, Integer> playerRangeBet : playerBets.entrySet()) {
                consoleCyan.println("Your Pick " + playerRangeBet.getKey() + ": Value = $" + payOutMap.get(playerRangeBet.getKey()) + ", Current Bet = $" + playerRangeBet.getValue());
            }
            consoleCyan.println("Enter Tile Number (-1 to finish): ");
            rangeNumber = keyboard.nextInt();
        if (rangeNumber == -1) {
            consoleCyan.println("Player's Bets Finished");
            isPlayerSettingBet = false;
        } else if (payOutMap.get(rangeNumber) == null) {
            consoleCyan.println("Invalid Input!");
        } else {
            consoleCyan.println("Enter Bet Amount: ");
            int betAmount = keyboard.nextInt();
        if (betAmount < 0) { //no negative bets
            consoleCyan.println("Invalid Input!");
        } else {
            playerBets.put(rangeNumber, playerBets.getOrDefault(rangeNumber, 0) + betAmount);
        }
    }
}
        //play game
        Integer results = spinWheel();

        //collect winnings
        payPlayer(results);

        //prompt me if I would like to continue?
        consoleCyan.println("Would you to continue?");
        String y = keyboard.next();
        String x = keyboard.next();
        if(y.equalsIgnoreCase("YES")){
            //restart the wheel for picks and spin
            start();
        }
        else{
            if(x.equalsIgnoreCase("NO")){
            }
        }

    }


    public void payPlayer(int winningTile) {
        int payOut = 0;
        //range of possible winnings from the wheel
        int range = bigSixWheel.getPossibleWheelHits().get(winningTile);
        if(playerBets.getOrDefault(range, 0) > 0){
            //payout player if the bet matches wheel output
            //take the bet and multiply it by the Multiplier...
            payOut += playerBets.get(range) * payOutMap.get(range);
            consoleCyan.println("Paying Player $" + payOut + " in Winnings ");
        }
        else{
            consoleCyan.println("You did not win!");
            consolePurple.println("Paying Player $" + payOut);
        }
        bigSixWheel.getPlayer().getArcadeAccount().addWinningsToBalance(payOut);
        //loop back to pick your tile instead of exiting the game

    }



    public void reset(){

        playerBets.clear();
    }



    public Integer spinWheel () {

        int randomElement = tileRandomizer.nextInt(bigSixWheel.getPossibleWheelHits().size());

        return randomElement;
    }


}
