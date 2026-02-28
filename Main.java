// Mini Banking Transaction Analyzer

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {


   public static void main(String[] args) throws IOException {

      BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(System.in));

      String ownersName = "";
      int transaction = 0;
      double amount = 0;
      double totalIncome = 0;
      double totalExpense = 0;
      double netBalance = 0;
      double largestAmount = Double.NEGATIVE_INFINITY;
      double averageAmount = 0;
      double totalAmount = 0;
      double[] transactions;
      boolean isRunning = true;

      System.out.println("Welcome to Banking Transaction Analyzer");

      while (isRunning) {
         System.out.println(">>START || EXIT<<");
         System.out.println("* Type START to make a Transaction");
         System.out.println("* Type EXIT to stop a Transaction");
         System.out.print("Enter = ");
         final String input = bufferedReader.readLine();
         if ("START".equalsIgnoreCase(input)) {
            totalIncome = 0;
            totalExpense = 0;
            totalAmount = 0;
            largestAmount = Double.NEGATIVE_INFINITY;
            averageAmount = 0;
            netBalance = 0;

            System.out.print("Enter Your Name = ");
            ownersName = bufferedReader.readLine().trim().toUpperCase();
            System.out.println("Welcome " + ownersName + "\n");

            System.out.println("Enter Number of Transaction");
            System.out.print("Enter = ");
            transaction = Integer.parseInt(bufferedReader.readLine());

            transactions = new double[transaction];
            for (int i = 0; i < transactions.length; i++) {

               if (transaction == 0) {
                  break;
               }

               System.out.print("Enter Amount = ");
               amount = Double.parseDouble(bufferedReader.readLine());

               transactions[i] = amount;


               if (amount > 0)
                  totalIncome += amount;

               if (amount < 0)
                  totalExpense += Math.abs(amount);

               netBalance = totalIncome - totalExpense;
               largestAmount = Math.max(largestAmount, transactions[i]);

               totalAmount += amount;
               if (transaction > 0) {
                  averageAmount = totalAmount / transaction;
               } else {
                  averageAmount = 0;
               }
            }

            System.out.println("--------------------------------");
            System.out.println("Total Income = " + totalIncome);
            System.out.println("Total Expense = " + totalExpense);
            System.out.println("Net Balance = " + netBalance);
            System.out.println("Largest Transaction Value = " + (transaction > 0 ? largestAmount : 0));
            System.out.println("Average Transaction Value = " + averageAmount);
            System.out.println("--------------------------------");


            if (netBalance > 10000) {
               System.out.println("Excellent " + netBalance);
            } else if (netBalance > 0) {
               System.out.println("Profitable " + netBalance);
            } else if (netBalance == 0) {
               System.out.println("Break Even " + netBalance);
            } else {
               System.out.println("Loss " + netBalance);
            }
            System.out.println("--------------------------------");
         }

         if (!"START".equalsIgnoreCase(input) && !"EXIT".equalsIgnoreCase(input)) {
            System.err.println("Error = input does not match. Try again.");
            continue;
         }

         if ("EXIT".equalsIgnoreCase(input)) {
            System.out.println("Exiting Transaction. Come Again.");
            isRunning = false;
            break;
         }
      }
      bufferedReader.close();
   }
}