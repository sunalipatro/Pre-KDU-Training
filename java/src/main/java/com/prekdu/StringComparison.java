package com.prekdu;

import java.util.Scanner;

/*
 * Create a basic Java Program that takes two strings as input and produces the following output.
 * Print the length of the first string
 * Print the length of the second string
 * Print if the length matches or not
 * Print if the two strings are the same
 */

public final class StringComparison {

  /**
   * The main method that takes two strings as input, c ompares their lengths, and checks if they
   * are the same.
   */
  private StringComparison() {
    // private method
  }

  /**
   * This is the main entry point of the program. It compares the lengths and content of two input
   * strings.
   *
   * @param args Command line arguments (not used in this program).
   */
  public static void main(final String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      // 1st input
      System.out.print("Enter the first string: ");
      String s1 = scanner.nextLine();

      // 2nd input
      System.out.print("Enter the second string: ");
      String s2 = scanner.nextLine();

      // Printing the length of the two strings.
      Integer len1 = s1.length();
      Integer len2 = s2.length();
      System.out.println("Length of the first string: " + len1);
      System.out.println("Length of the second string: " + len2);

      // Comparison of length
      if (len1.equals(len2)) {
        System.out.println("Lengths of the two strings matched");
      } else {
        System.out.println("Lengths of the two strings do not match");
      }

      // Comparison of strings
      if (s1.equals(s2)) {
        System.out.println("The two strings are the same");
      } else {
        System.out.println("The two strings are not the same");
      }
    }
  }
}
