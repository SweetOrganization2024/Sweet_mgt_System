package sweetSys;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class chat_msg {
    private String currentUser;
    private String connectedUser;
    private String sentMessage;
    private List<String> receivedMessages;
    private List<String> chatHistory;
    private boolean isMessageDelivered;
    private boolean isErrorEncountered;
    private String errorMessage;
    private static final Logger logger = Logger.getLogger(chat_msg.class.getName());

    public chat_msg() {
        chatHistory = new ArrayList<>();
        receivedMessages = new ArrayList<>();
        isMessageDelivered = false;
        isErrorEncountered = false;
    }

    public void logIn(String user) {
        this.currentUser = user;
        logger.info(currentUser + " is logged in.");
    }

    public void connectToUser(String user) {
        this.connectedUser = user;
        logger.info(currentUser + " is connected to " + connectedUser);
    }

    public void sendMessage(String message) {
        if (connectedUser != null) {
            this.sentMessage = message;
            receivedMessages.add(message);
            chatHistory.add(message);
            logger.info("Message sent to " + connectedUser + ": " + message);
        } else {
            this.isErrorEncountered = true;
            this.errorMessage = "Unable to send message, no user connected.";
        }
    }


    public void receiveMessage(String message) {
        if (connectedUser != null) {
            receivedMessages.add(message);
            chatHistory.add(message);
            logger.info("Received message: " + message);
        } else {
            this.isErrorEncountered = true;
            this.errorMessage = "Unable to receive message, no user connected.";
            logger.info(errorMessage);
        }
    }

    public void viewChatHistory() {
        logger.info("Chat History: " + chatHistory);
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
        logger.info("Message deleted: " + message);
    }

    public void clearChatHistory() {
        chatHistory.clear();
        logger.info("Chat history cleared.");
    }

   /* public void printStatus() {
        if (isErrorEncountered) {
            logger.info("Error: " + errorMessage);
        } else if (isMessageDelivered) {
            logger.info("Message delivered: " + sentMessage);
        }
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public String getConnectedUser() {
        return connectedUser;
    }

    public List<String> getReceivedMessages() {
        return new ArrayList<>(receivedMessages);
    }*/

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
            logger.info("Added previous message to chat history: " + message);
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
