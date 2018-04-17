package com.baozi.akka.remote.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by baozi on 2018/4/12.
 */
public class RemoteRmiImpl extends UnicastRemoteObject implements RemoteRmi{

    protected RemoteRmiImpl() throws RemoteException {
    }

    @Override
    public void sendNoReturn(String message) throws RemoteException, InterruptedException {
        System.out.println("进来了:sendNoReturn");
        Thread.sleep(2000);
    }

    @Override
    public String sendHasReturn(JoinRmiEvt joinRmiEvt) throws RemoteException {
        System.out.println("进来了:sendHasReturn");
        if (joinRmiEvt.getId() >= 0) {
            return new StringBuilder("the").append(joinRmiEvt.getName()).append("has join").toString();
        }
        return null;
    }
}
