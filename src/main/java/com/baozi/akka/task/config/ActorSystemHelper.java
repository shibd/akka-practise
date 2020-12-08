package com.baozi.akka.task.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorSystem;

/**
 * @Author: baozi
 * @Date: 12/7/20 9:48 AM
 */
public class ActorSystemHelper {

    public static final String APP_NAME = "test";

    public static ActorSystem getActorSystem() {
        Config conf;
        // a.withFallback(b) //a和b合并，如果有相同的key，以a为准
        conf = ConfigFactory
                .parseString(AkkaConfigConstant.AKKA_ACTOR_PROVIDER + "=cluster")
                .withFallback(ConfigFactory.parseString(AkkaConfigConstant.MANAGEMENT_DISCOVERY_METHOD + "=config"))
                .withFallback(ConfigFactory.parseString(AkkaConfigConstant.MANAGEMENT_SERVICE_NAME + "=" + APP_NAME))
                .withFallback(ConfigFactory.parseString(AkkaConfigConstant.AKKA_REMOTE_ARTERY_ENABLED + "=on"))
                .withFallback(ConfigFactory.parseString(AkkaConfigConstant.AKKA_REMOTE_ARTERY_TRANSPORT + "=tcp"))
                .withFallback(ConfigFactory.parseString(AkkaConfigConstant.AKKA_REMOTE_ARTERY_CANONICAL_PORT + "=2551"))
                .withFallback(ConfigFactory.parseString(AkkaConfigConstant.AKKA_REMOTE_ARTERY_CANONICAL_HOSTNAME + "=127.0.0.1"))
                .withFallback(ConfigFactory.parseString(AkkaConfigConstant.AKKA_REMOTE_ARTERY_BIND_HOSTNAME + "=0.0.0.0"))
                .withFallback(ConfigFactory.parseMap(getSeedNode()))
                .withFallback(ConfigFactory.parseString(AkkaConfigConstant.AKKA_DISCOVERY_METHOD + "=akka-dns"))
                .withFallback(ConfigFactory.parseString(AkkaConfigConstant.AKKA_MANAGEMENT_HTTP_PORT + "=8558"))
                .withFallback(ConfigFactory.parseString(AkkaConfigConstant.AKKA_MANAGEMENT_HTTP_BIND_HOSTNAME + "=0.0.0.0"));
        return ActorSystem.create("test", conf);
    }

    /**
     * 获取seedNode
     *
     * @return
     */
    private static Map<String, List<String>> getSeedNode() {
        Map<String, List<String>> res = new HashMap<>();
        res.put(AkkaConfigConstant.AKKA_CLUSTER_SEED_NODES,
                Arrays.asList("akka://" + "test" + "@127.0.0.1:2551"));
        return res;
    }
}
