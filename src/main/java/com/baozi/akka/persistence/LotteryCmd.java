package com.baozi.akka.persistence;

/**
 * Created by baozi on 2018/4/12.
 */
public class LotteryCmd extends Cmd{

    public Long userId;

    public String username;

    public LotteryCmd(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public LotteryCmd() {
    }
}
