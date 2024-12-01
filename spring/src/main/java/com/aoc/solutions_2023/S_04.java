package com.aoc.solutions_2023;

import java.util.ArrayList;
import com.aoc.lib.*;

public class S_04 extends Solution {

  public S_04(String input) {
    super(input);
  }

  @Override
  public String task_1() {
    String[] input_lines = input.split("\n");
    int point_counter = 0;
    for (String line : input_lines) {
      ArrayList<String> winning_numbers = new ArrayList<>();
      ArrayList<String> played_numbers = new ArrayList<>();
      String[] numbers = line.split(": +")[1].split(" \\| ");
      for (String string : numbers[0].split(" +")) {
        winning_numbers.add(string);
      }
      for (String string : numbers[1].split(" +")) {
        played_numbers.add(string);
      }
      int counter = 0;
      for (String string : played_numbers) {
        if (winning_numbers.contains(string)) {
          counter++;
        }
      }
      switch (counter) {
        case 0:
          break;
        case 1:
          point_counter++;
          break;
        default:
          point_counter += Math.pow(2, counter - 1);
          break;
      }
    }
    return Integer.toString(point_counter);
  }

  @Override
  public String task_2() {
    String[] input_lines = input.split("\n");
    int point_counter = 0;
    ArrayList<Integer> multiplier = new ArrayList<>();
    for (String line : input_lines) {
      ArrayList<String> winning_numbers = new ArrayList<>();
      ArrayList<String> played_numbers = new ArrayList<>();
      String[] numbers = line.split(": +")[1].split(" \\| ");
      for (String string : numbers[0].split(" +")) {
        winning_numbers.add(string);
      }
      for (String string : numbers[1].split(" +")) {
        played_numbers.add(string);
      }
      int n = 0;
      for (String string : played_numbers) {
        if (winning_numbers.contains(string)) {
          n++;
        }
      }
      int m;
      if (!multiplier.isEmpty()) {
        m = multiplier.getFirst();
        multiplier.removeFirst();
      } else {
        m = 1;
      }
      for (int i = 0; i < n; i++) {
        if (multiplier.size() > i) {
          multiplier.set(i, multiplier.get(i) + m);
        } else {
          multiplier.add(1 + m);
        }
      }
      point_counter += m;
      System.out.println(multiplier + "  - " + m);
    }
    return Integer.toString(point_counter);
  }
}
