package com.github.zipcodewilmington.casino.games.BlackJack;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

public class BlackJack implements GameInterface {
    BlackJackPlayer blackJackPlayer;
    BlackJackPlayer blackJackDealer;
    BlackJackEngine runTheBJGame;
    CasinoAccount casinoAccount;


    public BlackJack(CasinoAccount casinoAccount){
        this.casinoAccount = casinoAccount;
        blackJackDealer = new BlackJackPlayer();
    }

    @Override
    public void add(PlayerInterface player) {
        blackJackPlayer = (BlackJackPlayer) player;
    }

    @Override
    public void remove(PlayerInterface player) {
        blackJackPlayer = null;
    }

    @Override
    public void run() {
        BlackJackPlayer playerOne = new BlackJackPlayer();
        BlackJackPlayer dealer = new BlackJackPlayer();
        runTheBJGame = new BlackJackEngine(dealer, playerOne);
        runTheBJGame.startBJGame();
    }

}
