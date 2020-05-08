package org.examples;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * Mediator class to handle message communication
 * @author jacob1182
 * */
public class MessageSender {

    private final Map<String, MessageListener> listeners = new HashMap<>();

    /**
     * Register message listener
     *
     * @param listeners a message listener array
     * */
    public void registerListeners(MessageListener... listeners) {
        asList(listeners).forEach(listener -> this.listeners.put(listener.getName(), listener));
    }

    /**
     * Perform sending a message to a specific destination
     *
     * @param from the sender name
     * @param to the receiver name
     * @param message the message to send
     * */
    public void send(String from, String to, Message message) {
        if (listeners.containsKey(to)) {
            var listener = listeners.get(to);
            listener.messageHandler(from, message);
        } else
            throw new IllegalArgumentException("Unknown receiver: " + to);
    }
}
