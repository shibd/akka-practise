package com.baozi.akka.type.FireandForget;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Behaviors;

/**
 * @author baozi
 * @date 2020/2/18 10:20 AM
 */
public class Printer {
    public static class PrintMe {
        public final String message;

        public PrintMe(String message) {
            this.message = message;
        }
    }

    public static Behavior<PrintMe> create() {
        return Behaviors.setup(
                context ->
                        Behaviors.receive(PrintMe.class)
                                .onMessage(
                                        PrintMe.class,
                                        printMe -> {
                                            context.getLog().info(printMe.message);
                                            return Behaviors.same();
                                        })
                                .build());
    }

    public static void main(String[] args) {
        final ActorSystem<PrintMe> system =
                ActorSystem.create(Printer.create(), "printer-sample-system");

        // note that system is also the ActorRef to the guardian actor
        final ActorRef<PrintMe> ref = system;

        // these are all fire and forget
        ref.tell(new Printer.PrintMe("message 1"));
        ref.tell(new Printer.PrintMe("message 2"));
    }

}
