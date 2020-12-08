package com.baozi.akka.task;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import com.baozi.akka.task.message.StartTaskCmd;
import com.baozi.akka.task.message.StartTaskOkEvent;

import java.time.Duration;

public class TaskActor extends AbstractActor {

    // 任务执行状态
    private int taskState;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StartTaskCmd.class, this::startTask)
                .match(String.class, this::askTaskState)
                .build();
    }

    /**
     * 询问任务执行状态
     */
    public void askTaskState(String ask) {
        // 模拟任务状态
        if (taskState == 3) {
            System.out.println("任务执行成功");
            getSender().tell(new StartTaskOkEvent(), ActorRef.noSender());
        } else {
            System.out.println("等待任务执行成功");
            taskState++;
            getContext().system().scheduler().scheduleOnce(Duration.ofSeconds(5), getSelf(), "ask", getContext().getSystem().dispatcher(), getSender());
        }
    }

    public void startTask(StartTaskCmd cmd) throws InterruptedException {
        System.out.println("正在开启任务");
        getContext().system().scheduler().scheduleOnce(Duration.ofSeconds(5), getSelf(), "ask", getContext().getSystem().dispatcher(), getSender());
    }
}
