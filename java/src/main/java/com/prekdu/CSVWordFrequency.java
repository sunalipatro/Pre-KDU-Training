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

  /** Maximum number of top words to display. */
  private static final int TOP_WORD_COUNT = 3;

  private CSVWordFrequency() {
    // Private constructor to prevent instantiation.
  }

  /**
   * The main method of the program.
   *
   * @param args the command-line arguments
   */
  public static void main(final String[] args) {

    // Setting the file path.
    String filePath = "java/src/main/resources/input.csv";

    // Creating a hashmap to store word frequencies.
    Map<String, Integer> wordFrequencyMap = new HashMap<>();

    // Read from the CSV file.
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;

      while ((line = br.readLine()) != null) {
        String[] words = line.split("[,;.?!'\"\\s]+");

        for (String word : words) {
          word = word.toLowerCase(Locale.ENGLISH).trim();
          if (!word.isEmpty()) {
            wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
          }
        }
      }

    } catch (IOException e) {
      System.out.println("Error reading the file: " + e.getMessage());
    }

    // Convert the word frequency map
    // to a list of map entries.
    List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordFrequencyMap.entrySet());

    // Sort the list by frequency in descending order.
    wordList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

    // Print the top words.
    for (int i = 0; i < TOP_WORD_COUNT && i < wordList.size(); i++) {
      Map.Entry<String, Integer> entry = wordList.get(i);
      System.out.println(entry.getKey() + " : " + entry.getValue());
    }
  }
}
