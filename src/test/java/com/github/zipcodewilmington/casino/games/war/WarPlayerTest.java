package com.github.zipcodewilmington.casino.games.war;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.gameUtils.Cards;
import com.github.zipcodewilmington.casino.games.numberguess.WarPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WarPlayerTest {

    PlayerInterface player;

    @Test
    public void testPlayer_drawCard() {

        // Given
        CasinoAccount expectedAccount = new CasinoAccount("test", "test");
        player = new WarPlayer(expectedAccount);

        // when
        CasinoAccount actualAccount = player.getArcadeAccount();

        // then
        Assertions.assertEquals(expectedAccount.getAccountName(), actualAccount.getAccountName());
    }

    @Test
    public void testPlayer_drawCard_no_account() {

        // Given
        player = new WarPlayer(null);

        // when
        CasinoAccount actualAccount = player.getArcadeAccount();

        // then
        Assertions.assertNull(actualAccount);
    }

    @Test
    public void testPlayer_drawCard_account_balance() {

        // Given
        CasinoAccount expectedAccount = new CasinoAccount("test", "test");
        expectedAccount.setBalance(100);
        player = new WarPlayer(expectedAccount);

        // when
        CasinoAccount actualAccount = player.getArcadeAccount();

        // then
        Assertions.assertEquals(100, actualAccount.getAccountBalance());
    }

    @Test
    public void testPlayer_drawCard_play() {

        // Given
        player = new WarPlayer(null);

        // when
        Cards card = player.play();

        // then
        Assertions.assertNotNull(card);
        Assertions.assertNotNull(card.getSuit());
        Assertions.assertNotNull(card.getRank());
    }
}
