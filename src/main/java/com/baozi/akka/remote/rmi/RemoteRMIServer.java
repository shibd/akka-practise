package com.baozi.akka.remote.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * Created by baozi on 2018/4/12.
 */
public class RemoteRMIServer {

    public static void main(String[] args) throws Exception {

        System.out.println("the RemoteRMIServer is Starting ...");
        RemoteRmiImpl remoteRmi = new RemoteRmiImpl();

        System.out.println("Binding server Implementation to registry");
        LocateRegistry.createRegistry(2553);
        Naming.bind("rmi://127.0.0.1:2553/remote_rmi",remoteRmi);
        System.out.println("the RemoteRMIServer is Started");
        Thread.sleep(10000000);
    }
}
