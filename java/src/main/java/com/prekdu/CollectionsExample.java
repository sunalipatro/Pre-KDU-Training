package com.prekdu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * A basic Java program that demonstrates the use of ArrayList, HashSet, and HashMap. It takes 10
 * strings as input, populates the collections, and prints their contents.
 */
public final class CollectionsExample {

  /** The number of inputs to be taken from the user. */
  private static final int INPUT_COUNT = 10;

  private CollectionsExample() {
    // Private constructor to prevent instantiation.
  }

  /**
   * The main method of the program.
   *
   * @param args the command-line arguments
   */
  public static void main(final String[] args) {

    // Initialization
    List<String> arrayList = new ArrayList<>();
    Set<String> hashSet = new HashSet<>();
    Map<String, Integer> hashMap = new HashMap<>();

    // Scanner for input.
    try (Scanner scanner = new Scanner(System.in)) {
      // Taking INPUT_COUNT inputs.
      for (int i = 1; i <= INPUT_COUNT; i++) {
        String input = scanner.nextLine();

        // Adding to the ArrayList
        arrayList.add(input);

        // Adding to the HashSet
        hashSet.add(input);

        // Adding to the HashMap
        hashMap.put(input, hashMap.getOrDefault(input, 0) + 1);
      }
    }

    // Printing
    System.out.println("Content of the ArrayList:");
    for (String item : arrayList) {
      System.out.println(item);
    }

    System.out.println("Content of the HashSet:");
    for (String item : hashSet) {
      System.out.println(item);
    }

    System.out.println("Content of the HashMap:");
    for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
      System.out.println(entry.getKey() + " : " + entry.getValue());
    }
  }
}
