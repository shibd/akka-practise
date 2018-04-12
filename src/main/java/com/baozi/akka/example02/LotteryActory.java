package com.baozi.akka.example02;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.persistence.AbstractPersistentActor;
import akka.persistence.SaveSnapshotSuccess;
import akka.persistence.SnapshotOffer;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by baozi on 2018/4/12.
 */
public class LotteryActory extends AbstractPersistentActor {

    /**
     * 初始Actor状态
     */
    private Lottery state;

    public LotteryActory(Lottery lottery) {
        this.state = lottery;
    }

    public void updateState(LuckyEvent luckyEvent) {
        state.update(luckyEvent.luckyMoney);
    }

    private Event doLottery(LotteryCmd lc) {
        if (state.remainAmount > 0) {
            System.out.println(state.remainAmount);
            int luckyMonery = new Random().nextInt(state.remainAmount) + 1;
            System.out.println(luckyMonery);
            return new LuckyEvent(lc.userId, luckyMonery);
        } else {
            return new FailureEvent(lc.userId, "下次早点来,红包已被抽完");
        }
    }

    private void increaseEvtCountAndSnapshot() {
        int snapShotInterval = 5;
        if (lastSequenceNr() % snapShotInterval == 0 && lastSequenceNr() != 0) { //当有持久化5个事件后我们便存储一次当前Actor状态的快照
            //从新发送
            System.out.println("存够5个事件,发送存储快照命令");
            getSelf().tell("saveSnapshot", null);
        }
    }

    @Override
    public Receive createReceiveRecover() {
        return receiveBuilder()
                .match(LuckyEvent.class, s -> updateState(s))
                .match(SnapshotOffer.class, s -> {
                    Lottery lo = (Lottery) s.snapshot();
                    System.out.println("从快照恢复数据,一共" + lo.totalAmount + "快,还剩余" + lo.remainAmount);
                    state = lo;
                })
                .build();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                //监听抽奖命令
                .match(LotteryCmd.class, l -> {
                    //抽奖,产生事件
                    Event event = doLottery(l);
                    if (event instanceof LuckyEvent) {
                        LuckyEvent luckyEvent = (LuckyEvent) event;
                        persist(luckyEvent, e -> {
                            //更新状态
                            updateState(luckyEvent);
                            //快照存储
                            increaseEvtCountAndSnapshot();
                            //发送成功事件
//                            getSender().tell(null, null);
                            System.out.println("恭喜用户" + luckyEvent.userId + "抽到了" + luckyEvent.luckyMoney + "元红包");
                        });
                    } else {
                        //失败事件
                        System.out.println(((FailureEvent)event).reason);
//                        getSender().tell(null, null);
                    }

                })
                .matchEquals("saveSnapshot", s -> {
                    System.out.println("开始存储快照");
                    saveSnapshot(state);
                })
                .match(SaveSnapshotSuccess.class, s -> {
                    System.out.println("快照存储成功");
                })
                .build();
    }

    @Override
    public String persistenceId() {
        return "actor-2";
    }

    public static void main(String[] args) throws Exception {

        Lottery lottery = new Lottery(10000, 10000);
        ActorSystem actorSystem = ActorSystem.create();

        ActorRef actor = actorSystem.actorOf(Props.create(LotteryActory.class, lottery), "actor-2");

        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 5; i++) {
            pool.submit(new LotteryRun(actor, new LotteryCmd(Long.valueOf(i), "godpan", "xxx@gmail.com")));
        }
        Thread.sleep(5000);
        pool.shutdown();
        actorSystem.terminate();
    }
}

class LotteryRun implements Runnable {

    private ActorRef actorRef;

    private LotteryCmd lotteryCmd;

    public LotteryRun(ActorRef actorRef, LotteryCmd lotteryCmd) {
        this.actorRef = actorRef;
        this.lotteryCmd = lotteryCmd;
    }

    @Override
    public void run() {
        actorRef.tell(lotteryCmd, null);
    }
}

