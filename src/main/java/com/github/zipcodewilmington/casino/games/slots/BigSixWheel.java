package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import jdk.internal.access.JavaSecurityAccess;

import java.util.*;

/**
 * Created by leon on 7/21/2020.
 */

public class BigSixWheel implements GameInterface {
    //Wheel will spin
    //payout multipliers
    //Array<> of 54 tile
    //Tiles for players to choose from

    private Random tileRandomizer;
    private Integer bettingTiles;
    private Integer tilesNumber;
    private HashMap<Integer,Integer> payOuts;
    private List<Integer> possibleWheelHits;
    private BigSixWheelEngine bigSixWheelEngine;
    private CasinoAccount casinoAccount;
    private BigSixWheelPlayer player;


    public BigSixWheel(CasinoAccount casinoAccount){

        this.casinoAccount = casinoAccount;
        this.tileRandomizer = new Random();
        this.bigSixWheelEngine = new BigSixWheelEngine();

        setUpGame();

    }

    private void setUpGame() {

        setWheelHits();

        setPayout();

    }

    private void setWheelHits() {

        possibleWheelHits = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                2, 2, 5, 5, 5, 5, 5, 5, 5, 10, 10, 10, 10, 20, 20, 0, 0);
    }


    private void setPayout() {
        payOuts = new HashMap<>();
        payOuts.put(1,1);
        payOuts.put(2,2);
        payOuts.put(5,5);
        payOuts.put(10,10);
        payOuts.put(20,20);
        payOuts.put(0,40); //this is the bonus tile
    }


    public Integer spinWheel () {

            int randomElement = possibleWheelHits.get(tileRandomizer.nextInt(possibleWheelHits.size()));

            return randomElement;
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
