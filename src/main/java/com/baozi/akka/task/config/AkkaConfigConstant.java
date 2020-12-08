package com.baozi.akka.task.config;

public class AkkaConfigConstant {

    public static final String AKKA_REMOTE_ARTERY_ENABLED                   = "akka.remote.artery.enabled";

    public static final String AKKA_REMOTE_ARTERY_TRANSPORT                 = "akka.remote.artery.transport";

    public static final String AKKA_REMOTE_ARTERY_CANONICAL_PORT            = "akka.remote.artery.canonical.port";

    public static final String AKKA_REMOTE_ARTERY_CANONICAL_HOSTNAME        = "akka.remote.artery.canonical.hostname";

    public static final String AKKA_REMOTE_ARTERY_BIND_HOSTNAME             = "akka.remote.artery.bind.hostname";

    public static final String AKKA_CLUSTER_SEED_NODES                      = "akka.cluster.seed-nodes";

    public static final String AKKA_DISCOVERY_METHOD                        = "akka.discovery.method";

    public static final String AKKA_DISCOVERY_KUBERNETES_POD_LABEL_SELECTOR = "akka.discovery.kubernetes-api.pod-label-selector";

    public static final String AKKA_DISCOVERY_KUBERNETES_POD_DOMAIN         = "akka.discovery.kubernetes-api.pod-domain";

    public static final String AKKA_MANAGEMENT_HTTP_PORT                    = "akka.management.http.port";

    public static final String AKKA_MANAGEMENT_HTTP_BIND_HOSTNAME           = "akka.management.http.bind-hostname";

    public static final String AKKA_ACTOR_PROVIDER                          = "akka.actor.provider";

    // akka.management.cluster.bootstrap
    public static final String MANAGEMENT_SERVICE_NAME                      = "akka.management.cluster.bootstrap.contact-point-discovery.service-name";

    public static final String MANAGEMENT_DISCOVERY_METHOD                  = "akka.management.cluster.bootstrap.contact-point-discovery.discovery-method";

}
