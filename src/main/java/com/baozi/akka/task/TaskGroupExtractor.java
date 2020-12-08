package com.baozi.akka.task;


import com.baozi.akka.task.message.StartTaskGroupCmd;
import com.baozi.akka.task.utils.HashUtil;

import akka.cluster.sharding.ShardRegion;

/**
 * @Author: baozi
 * @Date: 12/3/20 5:45 PM
 */
public class TaskGroupExtractor implements ShardRegion.MessageExtractor{

    private final int shardNum;

    public TaskGroupExtractor(int shardNum) {
        this.shardNum = shardNum;
    }

    @Override
    public String entityId(Object message) {
        if (message instanceof StartTaskGroupCmd) {
            StartTaskGroupCmd startTaskGroupCmd = (StartTaskGroupCmd) message;
            return startTaskGroupCmd.actorId();
        }
        return null;
    }

    @Override
    public Object entityMessage(Object message) {
        return message;
    }

    @Override
    public String shardId(Object message) {
        int id = HashUtil.dekHash(entityId(message));
        return String.valueOf(id % shardNum);
    }
}
