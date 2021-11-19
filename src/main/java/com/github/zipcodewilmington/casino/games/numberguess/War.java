package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.gameUtils.Deck;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * Created by leon on 7/21/2020.
 */
public class War implements GameInterface {

    private WarPlayer player;
    private Dealer dealer;

    public War() {
        dealer = new Dealer();
    }

    @Override
    public void add(PlayerInterface player) {
        this.player = (WarPlayer) player;
    }

    @Override
    public void remove(PlayerInterface player) {
        this.player = null;
    }

    @Override
    public void run() {

        WarEngine warEngine = new WarEngine(dealer, player);
        warEngine.startGame();
    }
}