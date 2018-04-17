package com.baozi.akka.helloworld;

/**
 * Created by baozi on 2018/4/11.
 */
public class TurnOnLight extends Action{

    private final String message = "Turn on the living room light";

    @Override
    public String getMessage() {
        return message;
    }
}
