package com.github.zipcodewilmington.casino.games.war;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.gameUtils.Cards;
import com.github.zipcodewilmington.casino.games.numberguess.Dealer;
import com.github.zipcodewilmington.casino.games.numberguess.War;
import com.github.zipcodewilmington.casino.games.numberguess.WarPlayer;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import javax.smartcardio.Card;

public class WarTest {

    private War war = new War();

    @Test
    public void test_addPlayer() {

        // Given
        PlayerInterface expectedPlayer = new WarPlayer(new CasinoAccount("test", "test"));

        // when
        war.add(expectedPlayer);
        PlayerInterface actualPlayer =  war.getPlayer();

        //then
        Assertions.assertEquals(expectedPlayer, actualPlayer);
    }

    @Test
    public void test_removePlayer() {

        // Given
        PlayerInterface expectedPlayer = new WarPlayer(new CasinoAccount("test", "test"));
        war.add(expectedPlayer);

        // when
        war.remove(expectedPlayer);
        PlayerInterface actualPlayer =  war.getPlayer();

        //then
        Assertions.assertNull(actualPlayer);
    }

    @Test
    public void test_newWar_null_player() {

        // Given
        war = new War();

        // when
        PlayerInterface actualPlayer =  war.getPlayer();

        //then
        Assertions.assertNull(actualPlayer);
    }

    @Test
    public void test_dealer() {

        // Given
        Dealer dealer = new Dealer();

        // when
        CasinoAccount ca = dealer.getArcadeAccount();

        // then
        Assertions.assertNull(ca);
    }

    @Test
    public void test_dealer_play_validCard() {

        // Given
        Dealer dealer = new Dealer();

        // when
        Cards card = dealer.play();

        // then
        Assertions.assertNotNull(card);
    }
}
