package org.examples;

public interface MessageListener {

    /**
     * Invoked when a message is sent to the listener name
     *
     * @param from the message sender name
     * @param message the received message
     * */
    void messageHandler(String from, Message message);

    /**
     * Returns the message listener name
     *
     * @apiNote the name should be unique to every listener
     * */
    String getName();
}
