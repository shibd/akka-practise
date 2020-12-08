package com.baozi.akka.task;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.sharding.ClusterSharding;
import akka.cluster.sharding.ClusterShardingSettings;
import akka.management.cluster.bootstrap.ClusterBootstrap;
import akka.management.javadsl.AkkaManagement;
import com.baozi.akka.task.config.ActorSystemHelper;
import com.baozi.akka.task.message.SubmitTaskGroup;

/**
 * @Author: baozi
 * @Date: 12/3/20 5:27 PM
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        ActorSystem actorSystem = ActorSystemHelper.getActorSystem();
        AkkaManagement.get(actorSystem).start();
        ClusterBootstrap.get(actorSystem).start();

        ActorRef taskGroupActor = ClusterSharding.get(actorSystem).start(TaskGroupActor.class.getSimpleName(),
                Props.create(TaskGroupActor.class, () -> new TaskGroupActor()),
                ClusterShardingSettings.create(actorSystem),
                new TaskGroupExtractor(100));


        ActorRef env = actorSystem.actorOf(Props.create(EnvActor.class, taskGroupActor), "env");


        for (int i = 0; i < 10; i++) {
            env.tell(new SubmitTaskGroup("envId", "taskId" + i, "actionGroupId", "caseId"), ActorRef.noSender());
            if (i > 5) {
                Thread.sleep(i * 10000);
                System.out.println("延迟提交任务给环境");
                env.tell(new SubmitTaskGroup("envId", "taskId" + i, "actionGroupId", "caseId"), ActorRef.noSender());
            }
        }

    }
}
