package com.baozi.akka.cluster;

import akka.actor.AbstractActor;

/**
 * @Author: baozi
 * @Date: 12/3/20 5:27 PM
 */
public class ClusterPrintActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class, this::PrintInfo).build();
    }

    private void PrintInfo(String info) {
        System.out.println(getSelf().path().toString() + ": " + info);
    }
}
