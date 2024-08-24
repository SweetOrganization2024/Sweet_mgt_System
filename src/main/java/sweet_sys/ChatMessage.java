package sweet_sys;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ChatMessage {
    private String currentUser;
    private String connectedUser;
    private final List<String> receivedMessages;
    private final List<String> chatHistory;
    private String errorMessage;
    private static final Logger logger = Logger.getLogger(ChatMessage.class.getName());

    public ChatMessage() {
        chatHistory = new ArrayList<>();
        receivedMessages = new ArrayList<>();
    }

    public void logIn(String user) {
        this.currentUser = user;
        logger.info(() -> String.format("%s is logged in.", currentUser));
    }

    public void connectToUser(String user) {
        this.connectedUser = user;
        logger.info(() -> String.format("%s is connected to %s.", currentUser, connectedUser));
    }

    public void sendMessage(String message) {
        if (connectedUser != null) {
            receivedMessages.add(message);
            chatHistory.add(message);
            logger.info(() -> String.format("Message sent to %s: %s", connectedUser, message));
        } else {
            this.errorMessage = "Unable to send message, no user connected.";
            logger.warning(errorMessage);
        }
    }

    public void receiveMessage(String message) {
        if (connectedUser != null) {
            receivedMessages.add(message);
            chatHistory.add(message);
            logger.info(() -> String.format("Received message: %s", message));
        } else {
            this.errorMessage = "Unable to receive message, no user connected.";
            logger.warning(errorMessage);
        }
    }

    public void viewChatHistory() {
        logger.info(() -> String.format("Chat History: %s", chatHistory));
    }

    public void searchChatHistory(String searchTerm) {
        boolean found = chatHistory.stream().anyMatch(msg -> msg.contains(searchTerm));
        if (found) {
            logger.info("Search term found in chat history.");
        } else {
            logger.info("Search term not found.");
        }
    }

    public void deleteMessage(String message) {
        chatHistory.remove(message);
        logger.info(() -> String.format("Message deleted: %s", message));
    }

    public void clearChatHistory() {
        chatHistory.clear();
        logger.info("Chat history cleared.");
    }

    public List<String> getChatHistory() {
        return new ArrayList<>(chatHistory);
    }

    public List<String> getChatHistoryFor(String user) {
        List<String> userChatHistory = new ArrayList<>();
        for (String message : chatHistory) {
            if (message.contains(user)) {
                userChatHistory.add(message);
            }
        }
        return userChatHistory;
    }

    public void addPreviousMessages(List<String> messages) {
        for (String message : messages) {
            chatHistory.add(message);
            logger.info(() -> String.format("Added previous message to chat history: %s", message));
        }
    }

    public List<String> getReceivedMessagesFor(String user) {
        List<String> userReceivedMessages = new ArrayList<>();
        for (String message : receivedMessages) {
            if (message.contains(user)) {
                userReceivedMessages.add(message);
            }
        }
        return userReceivedMessages;
    }
}
