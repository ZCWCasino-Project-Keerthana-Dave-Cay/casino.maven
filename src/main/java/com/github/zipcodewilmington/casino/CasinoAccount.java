package com.github.zipcodewilmington.casino;

/**
 * Created by leon on 7/21/2020.
 * `ArcadeAccount` is registered for each user of the `Arcade`.
 * The `ArcadeAccount` is used to log into the system to select a `Game` to play.
 */
public class CasinoAccount {
    private String accountName;
    private String password;
    private Integer accountBalance;

    public CasinoAccount(String accountName, String password){
        this.accountName = accountName;
        this.password = password;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getPassword() {
        return password;
    }



    public Integer addWinningsToBalance(Integer winnings){
        accountBalance+= winnings; //accountBalance = accountBalance + winnings
        return accountBalance;
    }
    //adding the winnings to the players balance

    public Integer subtractBetAmountFromBalance(Integer betAmount) {
        accountBalance -= betAmount;
       return accountBalance;
    }

    public Integer checkBalance(Integer balance){
        return accountBalance;
    }

}
