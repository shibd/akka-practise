package com.baozi.akka.persistence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @Author: baozi
 * @Date: 12/7/20 10:08 AM
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Lottery lottery = new Lottery(100000, 100000);
        ActorSystem actorSystem = ActorSystem.create();

        ActorRef actorRef = actorSystem.actorOf(Props.create(LotteryActory.class, lottery), "actor-4");

        ExecutorService pool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            pool.submit(new LotteryRun(actorRef, new LotteryCmd(Long.valueOf(i), "baozi")));
        }
        Thread.sleep(5000);
        pool.shutdown();
        actorSystem.terminate();
    }
}
