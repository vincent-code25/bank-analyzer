package service;

import model.Account;
import model.Category;
import model.Transaction;

import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TransactionAnalyzer {

   public Map<Category, BigDecimal> calculateCategoryTotals(Account account) {
      Map<Category, BigDecimal> totals = new HashMap<>();

      for (Transaction t : account.getAllTransactions()) {

         if (t.isExpense()) { // FIX: only expenses
            Category category = t.getCategory();
            BigDecimal currentTotal = totals.getOrDefault(category, BigDecimal.ZERO);
            totals.put(category, currentTotal.add(t.getAmount()));
         }
      }

      return totals;
   }

   public BigDecimal calculateExpensePercentage(Account account) {
      BigDecimal income = account.getTotalIncome();
      BigDecimal expense = account.getTotalExpense();

      if (income.compareTo(BigDecimal.ZERO) == 0) {
         return BigDecimal.ZERO;
      }

      return expense
            .divide(income, 4, RoundingMode.HALF_UP)
            .multiply(BigDecimal.valueOf(100));
   }

   public Category findHighestSpendingCategory(Account account) {
      Map<Category, BigDecimal> totals = calculateCategoryTotals(account);

      Category highestCategory = null;
      BigDecimal highestAmount = BigDecimal.ZERO;

      for (Map.Entry<Category, BigDecimal> entry : totals.entrySet()) {
         if (highestCategory == null ||
               entry.getValue().compareTo(highestAmount) > 0) {

            highestCategory = entry.getKey();
            highestAmount = entry.getValue();
         }
      }

      return highestCategory;
   }

   public boolean isHighRisk(Account account) {
      BigDecimal netBalance = account.getNetBalance();
      BigDecimal expensePercentage = calculateExpensePercentage(account);

      return netBalance.compareTo(BigDecimal.ZERO) < 0
            || expensePercentage.compareTo(BigDecimal.valueOf(80)) > 0;
   }

   public String generateRiskReport(Account account) {
      BigDecimal netBalance = account.getNetBalance();
      BigDecimal expensePercentage = calculateExpensePercentage(account);
      Category highestCategory = findHighestSpendingCategory(account);
      boolean highRisk = isHighRisk(account);

      return "===== RISK REPORT =====\n" +
            "Net Balance: " + netBalance + "\n" +
            "Expense Percentage: " + expensePercentage + "%\n" +
            "Highest Spending Category: " + highestCategory + "\n" +
            "Risk Level: " + (highRisk ? "HIGH RISK" : "LOW RISK") + "\n" +
            "========================";
   }
}
