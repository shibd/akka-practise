package com.baozi.akka.helloworld;

/**
 * Created by baozi on 2018/4/11.
 */
public class BoilWater extends Action{

    private final String message = "Burn a pot of water";

    @Override
    public String getMessage() {
        return message;
    }
}
