package com.github.zipcodewilmington.casino.games.gameUtils;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.games.slots.BigSixWheel;
import com.github.zipcodewilmington.utils.IOConsole;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals; //Assert. is accessing a static method

public class BigSixWheelTest {

    @Test
    public void testGetPayOuts(){
        //given
        BigSixWheel testWheel = new BigSixWheel(new CasinoAccount("",""),new IOConsole());
        //when
        Map<Integer,Integer> payOuts = testWheel.getPayOuts();
        //then
        assertEquals(payOuts.get(1).intValue(),1);
    }

    @Test
    public void testGetPossibleWheelHits(){
        //given
        BigSixWheel testWheel = new BigSixWheel(new CasinoAccount("",""),new IOConsole());
        //when
        List<Integer> getPossibleWheelHits = testWheel.getPossibleWheelHits();
        //then
        assertEquals(getPossibleWheelHits.get(52).intValue(),40);
    }


}
