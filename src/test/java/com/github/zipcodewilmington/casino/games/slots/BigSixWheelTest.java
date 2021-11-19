package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.utils.IOConsole;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class BigSixWheelTest {

    @Test
    void getPayOuts() {
        //given
        BigSixWheel testWheel = new BigSixWheel(new CasinoAccount("", ""), new IOConsole());
        //when
        Map<Integer, Integer> payOuts = testWheel.getPayOuts();
        //then
        assertEquals(payOuts.get(1).intValue(), 1);
    }

    @Test
    void getPossibleWheelHits() {
        //given
        BigSixWheel testWheel = new BigSixWheel(new CasinoAccount("", ""), new IOConsole());
        //when
        List<Integer> getPossibleWheelHits = testWheel.getPossibleWheelHits();
        //then
        assertEquals(getPossibleWheelHits.get(52).intValue(), 40);
    }

    @Test
    void getPlayer() {
        //given
        BigSixWheel testWheel = new BigSixWheel(new CasinoAccount("Dave", ""), new IOConsole());
        //when
        BigSixWheelPlayer player = testWheel.getPlayer();
        //then
        assertEquals(player.getArcadeAccount().getAccountName(), "Dave");
    }

    @Test
    public void testSetPayout(){
        //given
        HashMap<Integer, Integer> testPayOuts = new HashMap<>();
        testPayOuts.put(1,1);
        testPayOuts.put(2,2);
        testPayOuts.put(5,5);
        testPayOuts.put(10,10);
        testPayOuts.put(20,20);
        testPayOuts.put(40,40);

        //when

        //then

    }


    @Test
    void add() {
    }

    @Test
    void remove() {
    }

    @Test
    void run() {
    }
}