package org.examples;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MessageSenderTest {

    @Test
    void sendSuccessfully() {
        var sender = new MessageSender();
        var listener1 = getMessageListener();
        var listener2 = getMessageListener();

        sender.registerListeners(listener1, listener2);

        var message = new Message("Hi!!");
        sender.send(listener1.getName(), listener2.getName(), message);

        verify(listener2).messageHandler(listener1.getName(), message);
    }

    @Test
    void sendFailure() {
        var sender = new MessageSender();
        var message = new Message("Hi!!");

        assertThrows(IllegalArgumentException.class,
                () -> sender.send("non-existent", "non-existent", message));
    }

    private MessageListener getMessageListener() {
        var listener = mock(MessageListener.class);
        when(listener.getName()).thenReturn(UUID.randomUUID().toString());
        return listener;
    }
}