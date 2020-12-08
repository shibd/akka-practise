package com.baozi.akka.task;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import com.baozi.akka.task.message.StartTaskGroupCmd;
import com.baozi.akka.task.message.StartTaskGroupOkEvent;
import com.baozi.akka.task.message.SubmitTaskGroup;

import java.util.*;

public class EnvActor extends AbstractActor {

    private Queue<SubmitTaskGroup> taskGroups = new LinkedList<>();

    private final ActorRef taskGroupCluster;

    private boolean runTask = false;

    public EnvActor(ActorRef taskGroupCluster) {
        this.taskGroupCluster = taskGroupCluster;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(SubmitTaskGroup.class, this::submitTaskGroup)
                .match(StartTaskGroupOkEvent.class, this::handler)
                .build();
    }

    /**
     * @param startTaskGroupOkEvent
     */
    public void handler(StartTaskGroupOkEvent startTaskGroupOkEvent) {
        System.out.println("任务组完成" + startTaskGroupOkEvent.toString());
        runTask = false;
        startTaskGroup();
    }


    /**
     * @param submitTaskGroup
     */
    public void submitTaskGroup(SubmitTaskGroup submitTaskGroup) {
        System.out.println("添加一个任务组" + submitTaskGroup.toString());
        taskGroups.add(submitTaskGroup);
        startTaskGroup();
    }


    public void startTaskGroup() {
        if (runTask == false) {
            SubmitTaskGroup taskGroup = taskGroups.poll();
            if (taskGroup == null) {
                System.out.println("没有找到任务组,等待提交任务组");
                return;
            }
            StartTaskGroupCmd startTaskGroupCmd = new StartTaskGroupCmd(taskGroup.getEnvId(), taskGroup.getTaskId(), taskGroup.getActionGroupId(),
                    taskGroup.getCaseId());
            System.out.println("发送执行任务组的指令" + startTaskGroupCmd.toString());
            taskGroupCluster.tell(startTaskGroupCmd, getSelf());
            runTask = true;
        }
    }

}
