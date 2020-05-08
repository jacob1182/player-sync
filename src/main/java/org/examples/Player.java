package org.examples;

/**
 * Represents a player in the system
 * @author jacob1182
 * */
public class Player implements MessageListener {

    private final MessageCounter messageCounter;
    private final MessageSender sender;
    private final String name;

    /**
     * Player constructor
     *
     * @param name the name of the player
     * @param sender the message sender mediator
     * */
    public Player(String name, MessageSender sender, MessageCounter messageCounter) {
        this.name = name;
        this.sender = sender;
        this.messageCounter = messageCounter;
        sender.registerListeners(this);
    }

    /**
     * Send a message to a specific player
     *
     * @param to the receiver player name
     * @param message the message to be sent
     * */
    public void sendMessage(String to, Message message) {
        int count = messageCounter.incrementMessageCount(message);
        sender.send(name, to, message.withMessageCount(count));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void messageHandler(String from, Message message) {
        System.out.println(name + " receives: " + message.getPayload());

        if (!messageCounter.isMessageResendLimitReached(message))
            sendMessage(from, message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }
}
