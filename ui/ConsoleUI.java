
package ui;

import service.ValidationService;
import service.TransactionAnalyzer;
import model.Transaction;
import model.Account;
import model.TransactionType;
import model.Category;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;

public class ConsoleUI {

   private final Scanner scanner;
   private final ValidationService validationService;
   private final TransactionAnalyzer analyzer;

   public ConsoleUI() {
      this.scanner = new Scanner(System.in);
      this.validationService = new ValidationService();
      this.analyzer = new TransactionAnalyzer();
   }

   // ADD THIS METHOD
   public String readMenuOption() {
      return scanner.nextLine();
   }

   // ==========================
   // MAIN MENU
   // ==========================
   public void showMainMenu() {
      System.out.println("\n===== BANKING SYSTEM =====");
      System.out.println("1. Create Account");
      System.out.println("2. Add Transaction");
      System.out.println("3. Show Summary");
      System.out.println("4. Show Full Report");
      System.out.println("5. Exit");
      System.out.print("Select option: ");
   }

   // ==========================
   // READ OWNER NAME
   // ==========================
   public String readOwnerName() {
      System.out.print("Enter owner name: ");
      String name = scanner.nextLine();

      if (name == null || name.isBlank()) {
         throw new IllegalArgumentException("Owner name cannot be empty.");
      }

      return name.trim();
   }

   // ==========================
   // READ TRANSACTION
   // ==========================
   public Transaction readTransaction() {

      try {
         System.out.print("Enter amount: ");
         BigDecimal amount = validationService.validateAmount(scanner.nextLine());

         System.out.print("Enter type (INCOME / EXPENSE): ");
         TransactionType type = TransactionType.valueOf(
               scanner.nextLine().trim().toUpperCase());

         System.out.print("Enter category (FOOD, RENT, SALARY, ENTERTAINMENT, OTHER): ");
         Category category = validationService.validateCategory(scanner.nextLine());

         System.out.print("Enter date (YYYY-MM-DD): ");
         LocalDate date = LocalDate.parse(scanner.nextLine());

         System.out.print("Enter description: ");
         String description = scanner.nextLine();

         return new Transaction(amount, type, category, date, description);

      } catch (DateTimeParseException e) {
         throw new IllegalArgumentException("Invalid date format. Use YYYY-MM-DD.");
      }
   }

   // ==========================
   // PRINT SUMMARY
   // ==========================
   public void printSummary(Account account) {

      System.out.println("\n===== ACCOUNT SUMMARY =====");
      System.out.println("Owner: " + account.getOwnerName());
      System.out.println("Total Income: " + account.getTotalIncome());
      System.out.println("Total Expense: " + account.getTotalExpense());
      System.out.println("Net Balance: " + account.getNetBalance());
      System.out.println("Average Transaction: " + account.getAverageTransaction());
      System.out.println("============================");
   }

   // ==========================
   // PRINT FULL REPORT
   // ==========================
   public void printFullReport(Account account) {

      System.out.println("\n===== FULL FINANCIAL REPORT =====");

      Map<Category, BigDecimal> categoryTotals = analyzer.calculateCategoryTotals(account);

      for (Map.Entry<Category, BigDecimal> entry : categoryTotals.entrySet()) {
         System.out.println(entry.getKey() + ": " + entry.getValue());
      }

      BigDecimal expensePercentage = analyzer.calculateExpensePercentage(account);
      Category highestCategory = analyzer.findHighestSpendingCategory(account);
      boolean highRisk = analyzer.isHighRisk(account);

      System.out.println("\nExpense Percentage: " + expensePercentage + "%");
      System.out.println("Highest Spending Category: " + highestCategory);
      System.out.println("Risk Status: " + (highRisk ? "HIGH RISK" : "LOW RISK"));

      if (highRisk) {
         printRiskWarning("Your expenses are too high compared to income.");
      }

      System.out.println("==================================");
   }

   // ==========================
   // PRINT RISK WARNING
   // ==========================
   public void printRiskWarning(String message) {
      System.out.println("\n⚠ WARNING ⚠");
      System.out.println(message);
      System.out.println("Please review your financial activity.");
   }
}