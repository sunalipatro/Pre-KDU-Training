package com.prekdu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/** A basic Java program that demonstrates the use of List, Set, and Map. */
public final class CollectionsExample {

  /** The number of strings to be entered by the user. */
  private static final int NUMBER_OF_STRINGS = 10;

  private CollectionsExample() {
    // Private constructor to prevent instantiation
  }

  /**
   * The main method to execute the program.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(final String[] args) {
    Scanner scanner = null;
    try {
      scanner = new Scanner(System.in);

      List<String> stringList = new ArrayList<>();
      Set<String> stringSet = new HashSet<>();
      Map<String, Integer> frequencyMap = new HashMap<>();

      System.out.println("Enter " + NUMBER_OF_STRINGS + " strings:");

      for (int i = 0; i < NUMBER_OF_STRINGS; i++) {
        String input = scanner.nextLine().trim();

        stringList.add(input);
        stringSet.add(input);

        frequencyMap.put(input, frequencyMap.getOrDefault(input, 0) + 1);
      }

      System.out.println("\nContents of the List:");
      for (String item : stringList) {
        System.out.println(item);
      }

      System.out.println("\nContents of the Set:");
      for (String item : stringSet) {
        System.out.println(item);
      }

      System.out.println("\nContents of the Map:");
      for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
        System.out.printf("Key:%s,Val:%d%n", entry.getKey(), entry.getValue());
      }
    } finally {
      if (scanner != null) {
        scanner.close();
      }
    }
  }
}
