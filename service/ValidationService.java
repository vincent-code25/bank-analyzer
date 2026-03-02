package service;

import model.Category;

import java.math.BigDecimal;

public class ValidationService {

   public BigDecimal validateAmount(String input) {
      if (input == null || input.isBlank()) {
         throw new IllegalArgumentException("Amount cannot be empty.");
      }

      try {
         BigDecimal amount = new BigDecimal(input);

         if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
         }

         return amount;

      } catch (NumberFormatException e) {
         throw new IllegalArgumentException("Invalid amount format.");
      }
   }

   public Category validateCategory(String input) {
      if (input == null || input.isBlank()) {
         throw new IllegalArgumentException("Category cannot be empty.");
      }

      try {
         return Category.valueOf(input.trim().toUpperCase());
      } catch (IllegalArgumentException e) {
         throw new IllegalArgumentException("Invalid category. Available: FOOD, RENT, SALARY, ENTERTAINMENT, OTHER.");
      }
   }

   public int validateTransactionCount(String input) {
      if (input == null || input.isBlank()) {
         throw new IllegalArgumentException("Transaction count cannot be empty.");
      }

      try {
         int count = Integer.parseInt(input);

         if (count < 0) {
            throw new IllegalArgumentException("Transaction count cannot be negative.");
         }

         return count;

      } catch (NumberFormatException e) {
         throw new IllegalArgumentException("Invalid number format for transaction count.");
      }
   }
}