package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

   private final BigDecimal amount;
   private final TransactionType type;
   private Category category;
   private LocalDate date;
   private String description;

   public Transaction(BigDecimal amount, TransactionType type, Category category, LocalDate date, String description) {
      if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
         throw new IllegalArgumentException("Amount must be positive");
      }
      if (type == null) {
         throw new IllegalArgumentException("Transaction type required");
      }
      this.amount = amount;
      this.type = type;
      this.category = category;
      this.date = date;
      this.description = description;
   }

   public BigDecimal getAmount() {
      return amount;
   }

   public TransactionType getType() {
      return type;
   }

   public Category getCategory() {
      return category;
   }

   public LocalDate getDate() {
      return date;
   }

   public String getDescription() {
      return description;
   }

   public boolean isIncome() {
      return type == TransactionType.INCOME;
   }

   public boolean isExpense() {
      return type == TransactionType.EXPENSE;
   }

   public BigDecimal getAbsoluteAmount() {
      return amount.abs();
   }
}