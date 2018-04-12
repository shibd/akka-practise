package com.baozi.akka.example02;

/**
 * Created by baozi on 2018/4/12.
 */
public class Lottery {

    /**
     * 红包总金额
     */
    public int totalAmount;

    /**
     * 红包剩余金额
     */
    public int remainAmount;


    public Lottery(int totalAmount, int remainAmount) {
        this.totalAmount = totalAmount;
        this.remainAmount = remainAmount;
    }

    public void update(int luckyMoney) {
        remainAmount = remainAmount - luckyMoney;
    }
}
