package com.baozi.akka.cluster;

import akka.cluster.sharding.ShardRegion;

/**
 * @Author: baozi
 * @Date: 12/3/20 5:45 PM
 */
public class ClusterPrintExtractor implements ShardRegion.MessageExtractor{

    private final int shardNum;

    public ClusterPrintExtractor(int shardNum) {
        this.shardNum = shardNum;
    }

    @Override
    public String entityId(Object message) {
        return (String) message;
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
