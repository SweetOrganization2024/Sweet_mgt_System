package sweetSys;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.io.*;
public class Feedback {
    private String email;
    private String sweetid;
    private String feedbackText;
    private int rating;
    private Date feedbackDate;
    public Feedback(String email, String sweetid, String feedbackText, int rating) {
        this.email = email;
        this.sweetid = sweetid;
        this.feedbackText = feedbackText;
        this.rating = rating;
        this.feedbackDate = new Date();
    }
    public String getUserId() {
        return email;
    }

    public void setUserId(String email) {
        this.email = email;
    }

    public String getSweetId() {
        return sweetid;
    }

    public void setSweetId(String sweetid) {
        this.sweetid = sweetid;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public void saveFeedbackToFile() {
        String fileName = "feedback.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Feedback by User: " + email);
            if (sweetid != null) {
                writer.write("\nSweet ID: " + sweetid);
                writer.write("\nRating: " + rating);
            }
            writer.write("\nFeedback: " + feedbackText);
            writer.write("\nDate: " + feedbackDate);
            writer.write("\n-----------------------\n");
        } catch (IOException e) {
            System.out.println("An error occurred while saving feedback: " + e.getMessage());
        }
    }
    public static void readFeedbackFromFile() {
        String fileName = "feedback.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading feedback: " + e.getMessage());
        }
    }
}