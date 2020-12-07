package com.baozi.akka.cluster;

import com.baozi.akka.cluster.config.ActorSystemHelper;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.sharding.ClusterSharding;
import akka.cluster.sharding.ClusterShardingSettings;
import akka.management.cluster.bootstrap.ClusterBootstrap;
import akka.management.javadsl.AkkaManagement;

/**
 * @Author: baozi
 * @Date: 12/3/20 5:27 PM
 */
public class Main {

    public static void main(String[] args) {

        ActorSystem actorSystem = ActorSystemHelper.getActorSystem();
        AkkaManagement.get(actorSystem).start();
        ClusterBootstrap.get(actorSystem).start();

        ActorRef clusterPrintActor = ClusterSharding.get(actorSystem).start(ClusterPrintActor.class.getSimpleName(),
                Props.create(ClusterPrintActor.class, () -> new ClusterPrintActor()),
                ClusterShardingSettings.create(actorSystem),
                new ClusterPrintExtractor(100));

        for (int i = 0; i < 100; i++) {
            clusterPrintActor.tell("hello world" + i, ActorRef.noSender());
        }

        actorSystem.terminate();
    }


}
