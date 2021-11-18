package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.casino.CasinoAccount;

import java.util.Random;

public class BigSixWheelEngine {

    private BigSixWheel bigSixWheel;
    private Random tileRandomizer;

    public void start() {
    }

    //takes in BigWheelSiX (game class) and BigWheelSix Player to implements the IOConsole (inputs/outputs)

    public BigSixWheelEngine(BigSixWheel bigSixWheel){
        this.bigSixWheel = bigSixWheel;
        this.tileRandomizer = new Random();
    }


    public Integer spinWheel () {

        int randomElement = bigSixWheel.getPossibleWheelHits().get(tileRandomizer.nextInt(bigSixWheel.getPossibleWheelHits().size()));

        return randomElement;
    }


}
