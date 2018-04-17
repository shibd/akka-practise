package com.baozi.akka.remote.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by baozi on 2018/4/12.
 */
public interface RemoteRmi extends Remote {

   void sendNoReturn(String message)throws RemoteException, InterruptedException;

   String sendHasReturn(JoinRmiEvt joinRmiEvt) throws RemoteException;
}
