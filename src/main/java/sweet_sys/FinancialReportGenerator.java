package sweet_sys;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FinancialReportGenerator {

    private static final String FILE_NAME = "financial_data.txt";
    private static final Logger logger = Logger.getLogger(FinancialReportGenerator.class.getName());

     private FinancialReportGenerator() {
        // 
    }

    public static void generateFinancialReports() {
        List<String> records = readFinancialData();
        if (records.isEmpty()) {
            logger.info("No financial data found.");
            return;
        }

        double totalIncome = 0;
        double totalExpense = 0;
        for (String dataLine : records) { 
            String[] parts = dataLine.split(",");
            if (parts.length == 3) {
                String transactionType = parts[0].trim();
                double amount = Double.parseDouble(parts[2].trim());
                if (transactionType.equalsIgnoreCase("Income")) {
                    totalIncome += amount;
                } else if (transactionType.equalsIgnoreCase("Expense")) {
                    totalExpense += amount;
                }
            }
        }
        double netProfit = totalIncome - totalExpense;

        logger.info(String.format("Financial Report:\nTotal Income: $%.2f\nTotal Expense: $%.2f\nNet Profit/Loss: $%.2f", 
                                  totalIncome, totalExpense, netProfit));
    }

    private static List<String> readFinancialData() {
        List<String> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(line);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading financial data.", e);
        }
        return records;
    }
}
