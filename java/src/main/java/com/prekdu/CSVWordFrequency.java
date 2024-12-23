package com.prekdu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class CSVWordFrequency {

  private CSVWordFrequency() {
    // private
  }

  /**
   * The main method.
   *
   * @param args command-line arguments, not used in this example
   */
  public static void main(final String[] args) {
    // The path to the CSV file.
    String csvFilePath = "java/src/main/resources/input.csv";

    Map<String, Integer> wFreq = new HashMap<>();
    Locale english = Locale.forLanguageTag("en");

    try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
      String line;

      while ((line = br.readLine()) != null) {
        String[] words = line.split("[,]");

        for (String word : words) {
          if (!word.isEmpty()) {
            word = word.toLowerCase(english);
            wFreq.put(word, wFreq.getOrDefault(word, 0) + 1);
          }
        }
      }
    } catch (IOException e) {
      System.out.println("Error reading the file: " + e.getMessage());
      return;
    }

    List<Map.Entry<String, Integer>> sEntry = new ArrayList<>(wFreq.entrySet());
    sEntry.sort((a, b) -> b.getValue().compareTo(a.getValue()));

    final int num = 3;
    System.out.println("Top 3 repeated words:");
    for (int i = 0; i < Math.min(num, sEntry.size()); i++) {
      Map.Entry<String, Integer> entry = sEntry.get(i);
      System.out.println(entry.getKey() + ": " + entry.getValue());
    }
  }
}
