package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.utils.IOConsole;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class BigSixWheelEngineTest {

    @Test
    public void testSpinWheel(){
        //given
        IOConsole console = new IOConsole();
        BigSixWheel testWheel = new BigSixWheel(new CasinoAccount("", ""), console);
        BigSixWheelEngine testEngine = new BigSixWheelEngine(testWheel, console);
        //when
        Integer testSpin = testEngine.spinWheel();
        //then
        assertTrue(testSpin >= 0 && testSpin < testWheel.getPossibleWheelHits().size()); //testing the ranges
    }


    @Test
    void start() {
    }

    @Test
    void payPlayer() {
    }

    @Test
    void reset() {
    }

    @Test
    void spinWheel() {
    }
}