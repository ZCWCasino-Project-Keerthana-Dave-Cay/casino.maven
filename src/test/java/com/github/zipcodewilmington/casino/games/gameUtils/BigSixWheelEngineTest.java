package com.github.zipcodewilmington.casino.games.gameUtils;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.games.slots.BigSixWheel;
import com.github.zipcodewilmington.casino.games.slots.BigSixWheelEngine;
import com.github.zipcodewilmington.utils.IOConsole;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class BigSixWheelEngineTest {

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

}
