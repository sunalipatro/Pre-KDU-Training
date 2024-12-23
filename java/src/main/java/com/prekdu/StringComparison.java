package com.prekdu;

import java.util.Scanner;

public final class StringComparison {

  /** Private constructor to prevent instantiation. */
  private StringComparison() {
    throw new UnsupportedOperationException("Utility class");
  }

  /**
   * The entry point of the program.
   *
   * @param args command-line arguments (not used).
   */
  public static void main(final String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      // Prompt user for the first string
      System.out.print("Enter the first string: ");
      String firstString = scanner.nextLine();

      // Prompt user for the second string
      System.out.print("Enter the second string: ");
      String secondString = scanner.nextLine();

      // Calculate and print the length of the first string
      int firstLength = firstString.length();
      System.out.println("Length of the first string: " + firstLength);

      // Calculate and print the length of the second string
      int secondLength = secondString.length();
      System.out.println("Length of the second string: " + secondLength);

      // Check if the lengths match and print the result
      if (firstLength == secondLength) {
        System.out.println("Lengths match.");
      } else {
        System.out.println("Lengths do not match.");
      }

      // Check if the two strings are the same and print the result
      if (firstString.equals(secondString)) {
        System.out.println("The two strings are the same.");
      } else {
        System.out.println("The two strings are different.");
      }
    }
  }
}
