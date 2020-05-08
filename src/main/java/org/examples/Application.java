package org.examples;

/**
 * @author jacob1182
 * */
public class Application {

    static int messageResendLimit = 10;

    public static void main(String[] args) {
        var sender = new MessageSender();
        var player1 = createNewPlayer("player1", sender);
        var player2 = createNewPlayer("player2", sender);

        player1.sendMessage(player2.getName(), new Message("Hi!!!!!"));
    }

    /**
     * Create a new player
     *
     * @apiNote
     * This method suggest the need of a factory in the future for players creation.
     * */
    private static Player createNewPlayer(String name, MessageSender sender) {
        return new Player(name, sender, new MessageCounter(messageResendLimit));
    }
}
