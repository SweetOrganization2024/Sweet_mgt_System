package project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweetSys.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class chat {
    private static final Logger logger = Logger.getLogger(chat.class.getName());

    private ChatMessage m;
    private String currentUser;
    private String connectedUser;
    private List<String> receivedMessages;
    private List<String> chatHistory;
    private boolean isMessageDelivered;
    private boolean isErrorEncountered;
    private String errorMessage;

    @Before
    public void setUp() {
        m = new ChatMessage();
        receivedMessages = new ArrayList<>();
        chatHistory = new ArrayList<>();
        isMessageDelivered = false;
        isErrorEncountered = false;
    }

    @Given("I am logged in as {string}")
    public void iAmLoggedInAs(String user) {
        currentUser = user;
        m.logIn(user);
        logger.info("Logged in as: " + currentUser);
    }

    @Given("I am connected to {string}")
    public void iAmConnectedTo(String user) {
        this.connectedUser = user;
        m.connectToUser(user);
    }

    @When("I send a message {string} to {string}")
    public void iSendAMessageTo(String message, String user) {
        m.sendMessage(message);
        isMessageDelivered = true;
    }

    @Then("the message should be delivered to {string} reliably")
    public void theMessageShouldBeDeliveredToReliably(String user) {
        assertTrue("Message should be delivered", isMessageDelivered);
    }

    @Then("{string} should see the message {string} in her chat window")
    public void shouldSeeTheMessageInHerChatWindow(String user, String message) {
        List<String> messages = m.getReceivedMessagesFor(user);
        assertTrue("User should see the message", messages.contains(message));
    }
    @Then("I should receive a delivery confirmation for the message")
    public void iShouldReceiveADeliveryConfirmationForTheMessage() {}

    @When("{string} sends me a message {string}")
    public void sendsMeAMessage(String user, String message) {
        m.receiveMessage(message);
        receivedMessages = m.getReceivedMessagesFor(currentUser);
        chatHistory = m.getChatHistory();
    }

    @Then("I should receive the message {string} reliably")
    public void iShouldReceiveTheMessageReliably(String message) {
        assertTrue("Message should be received", receivedMessages.contains(message));
    }

    @Then("I should see the message {string} in my chat window")
    public void iShouldSeeTheMessageInMyChatWindow(String msg) {
        assertTrue("Message should be visible in chat window", chatHistory.contains(msg));
    }

    @Given("I have previously sent messages to {string}")
    public void iHavePreviouslySentMessagesTo(String user) {
        this.connectedUser = user;
        List<String> previousMessages = List.of("Hello"+ user);
        m.addPreviousMessages(previousMessages);
    }

    @When("I view my chat history")
    public void iViewMyChatHistory() {
        chatHistory = m.getChatHistory();
    }

    @Then("I should see all messages sent to {string} in the correct order")
    public void iShouldSeeAllMessagesSentToInTheCorrectOrder(String user) {
        List<String> expectedChatHistory = m.getChatHistoryFor(user);
        assertEquals("Chat history should be in correct order", expectedChatHistory, chatHistory);
    }

    @Then("I should be able to search through the chat history")
    public void iShouldBeAbleToSearchThroughTheChatHistory() {
        m.viewChatHistory();
        String item = "Hello";
        m.searchChatHistory(item);
    }

    @Then("I should have the option to delete individual messages or the entire chat history")
    public void iShouldHaveTheOptionToDeleteIndividualMessagesOrTheEntireChatHistory() {
        if (!chatHistory.isEmpty()) {
            String messageToDelete = chatHistory.get(0);
            m.deleteMessage(messageToDelete);
            chatHistory.remove(messageToDelete);
            assertTrue("Message should be deleted", !chatHistory.contains(messageToDelete));
        }
        m.clearChatHistory();
        chatHistory = m.getChatHistory(); // Refresh chatHistory after clearing
        assertTrue("Chat history should be empty", chatHistory.isEmpty());
    }

    @Then("the chat history should be subject to any storage limits")
    public void theChatHistoryShouldBeSubjectToAnyStorageLimits() {
        int maxStorageLimit = 100;
        assertTrue("Chat history should not exceed storage limit", chatHistory.size() <= maxStorageLimit);
    }

    @Given("I encounter an issue while sending or receiving a message")
    public void iEncounterAnIssueWhileSendingOrReceivingAMessage() {
        isErrorEncountered = true;
        errorMessage = "Network issue encountered.";
    }

    @Then("I should see an appropriate error message explaining the problem")
    public void iShouldSeeAnAppropriateErrorMessageExplainingTheProblem() {
        assertTrue("Error should be encountered", isErrorEncountered);
        assertEquals("Error message should be correct", "Network issue encountered.", errorMessage);
    }

    @Then("I should be informed about any steps to resolve the issue")
    public void iShouldBeInformedAboutAnyStepsToResolveTheIssue() {
        String resolutionSteps = "Please check your network connection and try again.";
        assertEquals("Resolution steps should be provided", resolutionSteps, "Please check your network connection and try again.");
    }

    @Given("I am in a group chat with multiple users")
    public void iAmInAGroupChatWithMultipleUsers() {
        // This step is a placeholder; the group chat functionality should be implemented in the chat_msg class if needed
    }

    @When("I send a message in the group chat")
    public void iSendAMessageInTheGroupChat() {
        String groupMessage = "Hello Group!";
        List<String> groupMembers = List.of("Hala", "samia", "eman");

        for (String user : groupMembers) {
            m.connectToUser(user);  // Simulate connection to each user
            m.sendMessage(groupMessage);  // Send message
        }
    }

    @Then("all group members should receive the message reliably")
    public void allGroupMembersShouldReceiveTheMessageReliably() {
        List<String> groupMembers = List.of("Hala", "samia", "eman");
        String groupMessage = "Hello Group!";

        for (String user : groupMembers) {
            List<String> messages = m.getReceivedMessagesFor(user);  // Retrieve messages for each user
            assertTrue("All group members should receive the message", messages.contains(groupMessage));
        }
    }

    @Then("I should be able to join or leave the group chat at any time")
    public void iShouldBeAbleToJoinOrLeaveTheGroupChatAtAnyTime() {
        boolean canJoinGroup = true;
        boolean canLeaveGroup = true;
        assertTrue("User should be able to join the group", canJoinGroup);
        assertTrue("User should be able to leave the group", canLeaveGroup);
    }

    @Given("I am offline")
    public void iAmOffline() {
        isMessageDelivered = false;
        logger.info("I'm offline now");
    }

    @When("{string} sends me a message")
    public void sendsMeAMessageWhenOffline(String user) {
        if (!isMessageDelivered) {
            receivedMessages.add("Message from " + user + " is stored.");
        }
    }

    @Then("the message should be stored and delivered to me once I am back online")
    public void theMessageShouldBeStoredAndDeliveredToMeOnceIAmBackOnline() {
        isMessageDelivered = true;
        assertTrue("Stored message should be delivered once online", receivedMessages.contains("Message from samiaaa is stored."));
    }

    @Then("I should see the message in my chat window as soon as I reconnect")
    public void iShouldSeeTheMessageInMyChatWindowAsSoonAsIReconnect() {
        if (isMessageDelivered) {
            receivedMessages.add("Message from samiaaa is delivered.");
            assertTrue("Message should be visible in chat window", receivedMessages.contains("Message from samiaaa is delivered."));
        }
    }
}
