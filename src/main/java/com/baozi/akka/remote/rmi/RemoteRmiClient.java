package com.baozi.akka.remote.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by baozi on 2018/4/12.
 */
public class RemoteRmiClient {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, InterruptedException {
        System.out.println("the client has started");
        String url = "rmi://127.0.0.1:2553/remote_rmi";
        RemoteRmi remoteRmi = (RemoteRmi) Naming.lookup(url);
        System.out.println("the client has running");
        remoteRmi.sendNoReturn("send no return");
        System.out.println(remoteRmi.sendHasReturn(new JoinRmiEvt(1L," baozi ")));
        System.out.println("the client has end");
    }
}
