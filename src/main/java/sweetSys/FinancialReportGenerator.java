package sweetSys;

import java.io.*;
import java.util.*;

public class FinancialReportGenerator {

    private static final String FILE_NAME = "financial_data.txt";

    public static void generateFinancialReports() {
        List<String> records = readFinancialData();
        if (records.isEmpty()) {
            System.out.println("No financial data found.");
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

        System.out.println("Financial Report:");
        System.out.println("Total Income: $" + totalIncome);
        System.out.println("Total Expense: $" + totalExpense);
        System.out.println("Net Profit/Loss: $" + netProfit);
    }

    private static List<String> readFinancialData() {
        List<String> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading financial data.");
            e.printStackTrace();
        }
        return records;
    }

}
