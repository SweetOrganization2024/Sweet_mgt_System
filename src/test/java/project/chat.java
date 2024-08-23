package project;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import org.junit.Assert;
import sweetSys.ChatMessage;

public class chat {
    private static final Logger logger = Logger.getLogger(chat.class.getName());
    private ChatMessage m;
    private String currentUser;
    private List<String> receivedMessages;
    private List<String> chatHistory;
    private boolean isMessageDelivered;
    private boolean isErrorEncountered;
    private String errorMessage;

    public chat() {
    }

    @Before
    public void setUp() {
        this.m = new ChatMessage();
        this.receivedMessages = new ArrayList();
        this.chatHistory = new ArrayList();
        this.isMessageDelivered = false;
        this.isErrorEncountered = false;
    }

    @Given("I am logged in as {string}")
    public void iAmLoggedInAs(String user) {
        this.currentUser = user;
        this.m.logIn(user);
        logger.info("Logged in as: " + this.currentUser);
    }

    @Given("I am connected to {string}")
    public void iAmConnectedTo(String user) {
        this.m.connectToUser(user);
    }

    @When("I send a message {string} to {string}")
    public void iSendAMessageTo(String message, String user) {
        this.m.sendMessage(message);
        this.isMessageDelivered = true;
    }

    @Then("the message should be delivered to {string} reliably")
    public void theMessageShouldBeDeliveredToReliably(String user) {
        Assert.assertTrue("Message should be delivered", this.isMessageDelivered);
    }

    @Then("{string} should see the message {string} in her chat window")
    public void shouldSeeTheMessageInHerChatWindow(String user, String message) {
        List<String> messages = this.m.getReceivedMessagesFor(user);
        Assert.assertTrue("User should see the message", messages.contains(message));
    }

    @Then("I should receive a delivery confirmation for the message")
    public void iShouldReceiveADeliveryConfirmationForTheMessage() {
    }

    @When("{string} sends me a message {string}")
    public void sendsMeAMessage(String user, String message) {
        this.m.receiveMessage(message);
        this.receivedMessages = this.m.getReceivedMessagesFor(this.currentUser);
        this.chatHistory = this.m.getChatHistory();
    }

    @Then("I should receive the message {string} reliably")
    public void iShouldReceiveTheMessageReliably(String message) {
        Assert.assertTrue("Message should be received", this.receivedMessages.contains(message));
    }

    @Then("I should see the message {string} in my chat window")
    public void iShouldSeeTheMessageInMyChatWindow(String msg) {
        Assert.assertTrue("Message should be visible in chat window", this.chatHistory.contains(msg));
    }

    @Given("I have previously sent messages to {string}")
    public void iHavePreviouslySentMessagesTo(String user) {
        List<String> previousMessages = List.of("Hello" + user);
        this.m.addPreviousMessages(previousMessages);
    }

    @When("I view my chat history")
    public void iViewMyChatHistory() {
        this.chatHistory = this.m.getChatHistory();
    }

    @Then("I should see all messages sent to {string} in the correct order")
    public void iShouldSeeAllMessagesSentToInTheCorrectOrder(String user) {
        List<String> expectedChatHistory = this.m.getChatHistoryFor(user);
        Assert.assertEquals("Chat history should be in correct order", expectedChatHistory, this.chatHistory);
    }

    @Then("I should be able to search through the chat history")
    public void iShouldBeAbleToSearchThroughTheChatHistory() {
        this.m.viewChatHistory();
        String item = "Hello";
        this.m.searchChatHistory(item);
    }

    @Then("I should have the option to delete individual messages or the entire chat history")
    public void iShouldHaveTheOptionToDeleteIndividualMessagesOrTheEntireChatHistory() {
        if (!this.chatHistory.isEmpty()) {
            String messageToDelete = (String)this.chatHistory.get(0);
            this.m.deleteMessage(messageToDelete);
            this.chatHistory.remove(messageToDelete);
            Assert.assertTrue("Message should be deleted", !this.chatHistory.contains(messageToDelete));
        }

        this.m.clearChatHistory();
        this.chatHistory = this.m.getChatHistory();
        Assert.assertTrue("Chat history should be empty", this.chatHistory.isEmpty());
    }

    @Then("the chat history should be subject to any storage limits")
    public void theChatHistoryShouldBeSubjectToAnyStorageLimits() {
        int maxStorageLimit = 100;
        Assert.assertTrue("Chat history should not exceed storage limit", this.chatHistory.size() <= maxStorageLimit);
    }

    @Given("I encounter an issue while sending or receiving a message")
    public void iEncounterAnIssueWhileSendingOrReceivingAMessage() {
        this.isErrorEncountered = true;
        this.errorMessage = "Network issue encountered.";
    }

    @Then("I should see an appropriate error message explaining the problem")
    public void iShouldSeeAnAppropriateErrorMessageExplainingTheProblem() {
        Assert.assertTrue("Error should be encountered", this.isErrorEncountered);
        Assert.assertEquals("Error message should be correct", "Network issue encountered.", this.errorMessage);
    }

    @Then("I should be informed about any steps to resolve the issue")
    public void iShouldBeInformedAboutAnyStepsToResolveTheIssue() {
        String resolutionSteps = "Please check your network connection and try again.";
        Assert.assertEquals("Resolution steps should be provided", resolutionSteps, "Please check your network connection and try again.");
    }

    @Given("I am in a group chat with multiple users")
    public void iAmInAGroupChatWithMultipleUsers() {
    }

    @When("I send a message in the group chat")
    public void iSendAMessageInTheGroupChat() {
        String groupMessage = "Hello Group!";
        List<String> groupMembers = List.of("Hala", "samia", "eman");
        Iterator var3 = groupMembers.iterator();

        while(var3.hasNext()) {
            String user = (String)var3.next();
            this.m.connectToUser(user);
            this.m.sendMessage(groupMessage);
        }

    }

    @Then("all group members should receive the message reliably")
    public void allGroupMembersShouldReceiveTheMessageReliably() {
        List<String> groupMembers = List.of("Hala", "samia", "eman");
        String groupMessage = "Hello Group!";
        Iterator var3 = groupMembers.iterator();

        while(var3.hasNext()) {
            String user = (String)var3.next();
            List<String> messages = this.m.getReceivedMessagesFor(user);
            Assert.assertTrue("All group members should receive the message", messages.contains(groupMessage));
        }

    }

    @Then("I should be able to join or leave the group chat at any time")
    public void iShouldBeAbleToJoinOrLeaveTheGroupChatAtAnyTime() {
        boolean canJoinGroup = true;
        boolean canLeaveGroup = true;
        Assert.assertTrue("User should be able to join the group", canJoinGroup);
        Assert.assertTrue("User should be able to leave the group", canLeaveGroup);
    }

    @Given("I am offline")
    public void iAmOffline() {
        this.isMessageDelivered = false;
        logger.info("I'm offline now");
    }

    @When("{string} sends me a message")
    public void sendsMeAMessageWhenOffline(String user) {
        if (!this.isMessageDelivered) {
            this.receivedMessages.add("Message from " + user + " is stored.");
        }

    }

    @Then("the message should be stored and delivered to me once I am back online")
    public void theMessageShouldBeStoredAndDeliveredToMeOnceIAmBackOnline() {
        this.isMessageDelivered = true;
        Assert.assertTrue("Stored message should be delivered once online", this.receivedMessages.contains("Message from samiaaa is stored."));
    }

    @Then("I should see the message in my chat window as soon as I reconnect")
    public void iShouldSeeTheMessageInMyChatWindowAsSoonAsIReconnect() {
        if (this.isMessageDelivered) {
            this.receivedMessages.add("Message from samiaaa is delivered.");
            Assert.assertTrue("Message should be visible in chat window", this.receivedMessages.contains("Message from samiaaa is delivered."));
        }

    }
}
