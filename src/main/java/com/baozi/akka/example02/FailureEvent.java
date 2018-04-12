package com.baozi.akka.example02;

/**
 * Created by baozi on 2018/4/12.
 */
public class FailureEvent extends Event{

    public Long userId;

    public String reason;

    public FailureEvent(Long userId, String reason) {
        this.userId = userId;
        this.reason = reason;
    }

    public FailureEvent() {
    }
}
