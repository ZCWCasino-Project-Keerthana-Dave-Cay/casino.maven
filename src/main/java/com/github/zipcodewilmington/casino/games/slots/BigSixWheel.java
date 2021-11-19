package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;


import java.util.*;

/**
 * Created by leon on 7/21/2020.
 */

public class BigSixWheel implements GameInterface {
    //Wheel will spin
    //payout multipliers
    //Array<> of 54 tile
    //Tiles for players to choose from



    private Map<Integer,Integer> payOuts;
    private List<Integer> possibleWheelHits;
    private BigSixWheelEngine bigSixWheelEngine;
    private CasinoAccount casinoAccount;
    private BigSixWheelPlayer player;


    public BigSixWheel(CasinoAccount casinoAccount, IOConsole console){

        this.casinoAccount = casinoAccount;
        add(new BigSixWheelPlayer(casinoAccount));
        setUpGame();
        this.bigSixWheelEngine = new BigSixWheelEngine(this, console);

    }

    private void setUpGame() {

        setWheelHits();

        setPayout();

    }

    private void setWheelHits() {

        possibleWheelHits = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                2, 2, 5, 5, 5, 5, 5, 5, 5, 10, 10, 10, 10, 20, 20, 40, 40);

    }


    private void setPayout() {
        payOuts = new HashMap<>();
        payOuts.put(1,1);
        payOuts.put(2,2);
        payOuts.put(5,5);
        payOuts.put(10,10);
        payOuts.put(20,20);
        payOuts.put(40,40); //this is the bonus tile
    }

    public Map<Integer, Integer> getPayOuts(){

        return payOuts;
    }


    public List<Integer> getPossibleWheelHits(){

        return possibleWheelHits;
    }

    public BigSixWheelPlayer getPlayer(){

        return player;
    }



    @Override
    public void add(PlayerInterface player) {
        this.player = (BigSixWheelPlayer)player;

    }

    @Override
    public void remove(PlayerInterface player) {

    }

    @Override
    public void run() {

        bigSixWheelEngine.start();

    }

}
