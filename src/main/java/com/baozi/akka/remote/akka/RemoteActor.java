package com.baozi.akka.remote.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Created by baozi on 2018/4/12.
 */
public class RemoteActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    System.out.println("RemoteActor received message " + s);
                    getSender().tell("Hello from the RemoteActor", null);
                }).build();
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("RemoteDemoSystem");
        ActorRef remoteActor = system.actorOf(Props.create(RemoteActor.class), "RemoteActor");

    }
}
