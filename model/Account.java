package RoadmapForJava2026.model;

import java.util.*;
import java.lang.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Account {

   private String ownerName;
   private List<Transaction> transactions;

   public Account(String ownerName) {
      if (ownerName == null || ownerName.isBlank()) {
         throw new IllegalArgumentException("Owner name required");
      }

      this.ownerName = ownerName;
      this.transactions = new ArrayList<>();

   }

   public String getOwnerName() {
      return ownerName;
   }

   public void addTransaction(Transaction t) {
      if (t == null) {
         throw new IllegalArgumentException("Transaction can not be null");
      }
      transactions.add(t);
   }

   public List<Transaction> getAllTransactions() {
      return Collections.unmodifiableList(transactions);
   }

   public BigDecimal getTotalIncome() {
      BigDecimal total = BigDecimal.ZERO;
      for (Transaction t : transactions) {
         if (t.isIncome()) {
            total = total.add(t.getAbsoluteAmount());
         }
      }
      return total;
   }

   public BigDecimal getTotalExpense() {
      BigDecimal total = BigDecimal.ZERO;
      for (Transaction t : transactions) {
         if (t.isExpense()) {
            total = total.add(t.getAmount().abs());
         }
      }
      return total;
   }

   public BigDecimal getNetBalance() {
      return getTotalIncome().subtract(getTotalExpense());
   }

   public Transaction getLargestTransaction() {
      Transaction largestTransaction = null;
      for (Transaction t : transactions) {
         if (largestTransaction == null
               || t.getAbsoluteAmount().compareTo(largestTransaction.getAbsoluteAmount()) > 0) {
            largestTransaction = t;
         }
      }
      return largestTransaction;
   }

   public BigDecimal getAverageTransaction() {
      if (transactions.isEmpty()) {
         return BigDecimal.ZERO;
      }

      BigDecimal total = BigDecimal.ZERO;

      for (Transaction t : transactions) {
         total = total.add(t.getAbsoluteAmount());
      }

      return total.divide(
            BigDecimal.valueOf(transactions.size()),
            2,
            RoundingMode.HALF_UP);
   }

   public List<Transaction> getTransactionsByCategory(Category c) {
      List<Transaction> categoryTransaction = new ArrayList<>();
      for (Transaction t : transactions) {
         if (t.getCategory() == c) {
            categoryTransaction.add(t);
         }
      }
      return categoryTransaction;
   }

   public List<Transaction> getTransactionsByType(TransactionType t) {
      List<Transaction> transactionT = new ArrayList<>();
      for (Transaction transaction : transactions) {
         if (transaction.getType() == t) {
            transactionT.add(transaction);
         }
      }
      return transactionT;
   }
}