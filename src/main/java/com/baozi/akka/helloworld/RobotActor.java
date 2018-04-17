package com.baozi.akka.helloworld;

import akka.actor.AbstractActor;

/**
 * Created by baozi on 2018/4/11.
 */
public class RobotActor extends AbstractActor{

    public Receive createReceive() {
        return receiveBuilder().match(TurnOnLight.class, this::turnOnLight)
                .match(BoilWater.class, this::boilWater)
                .build();
    }

    private void turnOnLight(TurnOnLight turnOnLight) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(turnOnLight.getMessage());
    }

    private void boilWater(BoilWater boilWater) {
        System.out.println(boilWater.getMessage());
    }
}
