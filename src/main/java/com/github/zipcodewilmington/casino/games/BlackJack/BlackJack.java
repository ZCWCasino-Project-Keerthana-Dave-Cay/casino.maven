package com.github.zipcodewilmington.casino.games.BlackJack;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.utils.IOConsole;

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
        this.blackJackPlayer = (BlackJackPlayer) player;
        blackJackPlayer.setCasinoAccount(casinoAccount);
    }

    @Override
    public void remove(PlayerInterface player) {
        blackJackPlayer = null;
    }

    @Override
    public void run() {
        runTheBJGame = new BlackJackEngine(blackJackDealer, blackJackPlayer, new IOConsole());
        runTheBJGame.startBJGame();
    }

}
