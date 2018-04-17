package com.baozi.akka.persistence;

import java.util.Random;

/**
 * Created by baozi on 2018/4/12.
 */
public class LuckyEvent extends Event{

    public Long userId;

    public int luckyMoney;

    public LuckyEvent(Long userId, int luckyMoney) {
        this.userId = userId;
        this.luckyMoney = luckyMoney;
    }

    public LuckyEvent() {
    }

    public static void main(String[] args) {
        System.out.println(new Random().nextInt(10) + 1);
    }
}
