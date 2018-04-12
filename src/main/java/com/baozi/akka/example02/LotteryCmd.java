package com.baozi.akka.example02;

/**
 * Created by baozi on 2018/4/12.
 */
public class LotteryCmd extends Cmd{

    public Long userId;

    public String username;

    public String email;


    public LotteryCmd(Long userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public LotteryCmd() {
    }
}
