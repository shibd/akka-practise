package com.baozi.akka.task;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.baozi.akka.task.message.*;

/**
 * @Author: baozi
 * @Date: 12/3/20 5:27 PM
 */
public class TaskGroupActor extends AbstractActor {

    // 重启节点actor
    private ActorRef restartServerActor;
    // 任务执行Actor
    private ActorRef taskActor;
    // 环境Actor
    private ActorRef envActor;
    //
    private StartTaskGroupCmd startTaskGroupCmd;

    @Override
    public void preStart() throws Exception {
        restartServerActor = context().actorOf(Props.create(RestartServerActor.class, () -> new RestartServerActor()));
        taskActor = context().actorOf(Props.create(TaskActor.class, () -> new TaskActor()));
        // ... 其他
        super.preStart();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StartTaskGroupCmd.class, this::stateMachine)
                .match(RestartServerOkEvent.class, this::stateMachine)
                .match(StartTaskOkEvent.class, this::stateMachine)
                .build();
    }

    /**
     * 修改任务组状态
     */
    private void updateState() {

    }

    /**
     * 查询任务组数据
     * @return
     */
    private Object queryData() {
        return null;
    }

    /**
     *
     * @param object
     */
    private void stateMachine(Object object) {

        // 开始执行任务组，任务组的所有状态都由TaskGroup来控制
        if (object instanceof StartTaskGroupCmd) {
            System.out.println(getSelf().path().toString() + ": 开始执行任务组");
            restartServerActor.tell(new RestartServerCmd(), getSelf());
            envActor = getSender();
            startTaskGroupCmd = (StartTaskGroupCmd) object;
        }

        // 重启服务成功，开始
        if (object instanceof RestartServerOkEvent) {
            System.out.println(getSelf().path().toString() + "重启节点成功, 开始进行下一步: 开始发起任务调用");
            taskActor.tell(new StartTaskCmd(), getSelf());
        }

        // 任务调用成功
        if (object instanceof StartTaskOkEvent) {
            System.out.println(getSelf().path().toString() + "任务调用成功, 开始获取数据，写入");
            System.out.println("数据写入成功,停掉任务组, 通知环境执行下一个任务组");

            StartTaskGroupOkEvent startTaskGroupOkEvent = new StartTaskGroupOkEvent(startTaskGroupCmd.getEnvId(),
                    startTaskGroupCmd.getTaskId(), startTaskGroupCmd.getActionGroupId(), startTaskGroupCmd.getCaseId());
            envActor.tell(startTaskGroupOkEvent, ActorRef.noSender());
        }

        // ...
//        if () {
//        }
    }


    /**
     * 获取下一个任务
     * @return
     */
    private ActorRef getNextTask() {
        return null;
    }
}
