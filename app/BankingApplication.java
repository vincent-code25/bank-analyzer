package app;

import model.Account;
import model.Transaction;
import ui.ConsoleUI;

public class BankingApplication {

   private Account account;
   private final ConsoleUI ui;

   public BankingApplication() {
      this.ui = new ConsoleUI();
   }

   public void start() {

      System.out.println("Welcome to Banking Transaction Analyzer");

      boolean running = true;

      while (running) {

         ui.showMainMenu();
         String option = ui.readMenuOption(); // FIX: no new Scanner

         try {

            switch (option) {

               case "1":
                  String ownerName = ui.readOwnerName();
                  this.account = new Account(ownerName);
                  System.out.println("Account created successfully.");
                  break;

               case "2":
                  if (account == null) {
                     System.out.println("Please create an account first.");
                     break;
                  }
                  Transaction transaction = ui.readTransaction();
                  account.addTransaction(transaction);
                  System.out.println("Transaction added successfully.");
                  break;

               case "3":
                  if (account == null) {
                     System.out.println("Please create an account first.");
                     break;
                  }
                  ui.printSummary(account);
                  break;

               case "4":
                  if (account == null) {
                     System.out.println("Please create an account first.");
                     break;
                  }
                  ui.printFullReport(account);
                  break;

               case "5":
                  System.out.println("Exiting application. Goodbye.");
                  running = false;
                  break;

               default:
                  System.out.println("Invalid option. Try again.");
            }

         } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
         }
      }
   }

   public static void main(String[] args) {
      BankingApplication bankingApplication = new BankingApplication();
      bankingApplication.start();
   }
}