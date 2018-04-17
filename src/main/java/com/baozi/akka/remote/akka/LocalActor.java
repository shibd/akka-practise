package com.baozi.akka.remote.akka;

import akka.actor.*;

/**
 * Created by baozi on 2018/4/12.
 */
public class LocalActor extends AbstractActor{

    ActorSelection actorSelection = context().actorSelection("akka.tcp://RemoteDemoSystem@127.0.0.1:2552/usr/RemoteActor");

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Object.class, s -> {
                    actorSelection.tell("Hello Akka Rmoete", getSelf());
                }).build();
    }


    public static void main(String[] args) {

        ActorSystem system = ActorSystem.create();
        ActorRef actorRef = system.actorOf(Props.create(LocalActor.class));

        actorRef.tell("ss", null);
    }
}
