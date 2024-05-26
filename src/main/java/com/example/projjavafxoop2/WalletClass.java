package com.example.projjavafxoop2;


public class WalletClass {
    private double balance;
    private int walletid;
    private int userid;
    private String username;


    public WalletClass(double balance, int walletid, int userid, String username) {
        this.balance = balance;
        this.walletid = walletid;
        this.userid = userid;
        this.username = username;
    }

    public double getBalance() {
        return balance;
    }


    public int getWalletid() {
        return walletid;
    }


    public int getUserid() {
        return userid;
    }


    public String getUsername() {
        return username;
    }


    public void depositAmount(double amount){
        this.balance+=amount;
    }

    public void withdrawAmount(double amount){
        this.balance-=amount;

    }
    public void setBalance(double bal){
        this.balance = bal;
    }
}
