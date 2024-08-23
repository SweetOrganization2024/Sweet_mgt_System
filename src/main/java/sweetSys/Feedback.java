package sweetSys;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.io.*;
public class Feedback {
    private final String emai;
    private final String sweetid;
    private final String feedbackText;
    private final int rating;
    private final Date feedbackDate;

    public Feedback(String emai, String sweetid, String feedbackText, int rating) {
        this.emai = emai;
        this.sweetid = sweetid;
        this.feedbackText = feedbackText;
        this.rating = rating;
        this.feedbackDate = new Date();
    }

    public void saveFeedbackToFile() {
        String fileName = "feedback.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Feedback by User: " + emai);
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
