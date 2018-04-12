package com.baozi.akka.example01;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Created by baozi on 2018/4/11.
 */
public class Main {

    public static void main(String[] args) {

        final ActorSystem actorSystem = ActorSystem.create();

        final ActorRef robotActor = actorSystem.actorOf(Props.create(RobotActor.class), "robotActor");

        robotActor.tell(new TurnOnLight(), ActorRef.noSender());
        robotActor.tell(new BoilWater(), ActorRef.noSender());

    }
}
