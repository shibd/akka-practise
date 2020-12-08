package com.baozi.akka.task;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import com.baozi.akka.task.message.RestartServerCmd;
import com.baozi.akka.task.message.RestartServerOkEvent;

/**
 * @Author: baozi
 * @Date: 12/7/20 11:29 AM
 */
public class RestartServerActor extends AbstractActor {


    @Override
    public Receive createReceive() {
        return receiveBuilder().match(RestartServerCmd.class, this::restartServer).build();
    }

    public void restartServer(RestartServerCmd info) throws InterruptedException {
        System.out.println("正在重启节点");

        Thread.sleep(5000);

        System.out.println("重启节点成功");

        getSender().tell(new RestartServerOkEvent(), ActorRef.noSender());
    }
}
