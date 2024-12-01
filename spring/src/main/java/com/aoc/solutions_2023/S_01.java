package com.aoc.solutions_2023;

import com.aoc.lib.*;

public class S_01 extends Solution {

  public S_01(String input) {
    super(input);
  }

  @Override
  public String task_1() {

    String[] lines = input.split("\n");
    int counter = 0;
    for (String line : lines) {
      char c1 = '0', c2 = '0';
      for (int i = 0; i < line.length(); i++) {
        if (Character.isDigit(line.charAt(i))) {
          c1 = line.charAt(i);
          break;
        }
      }
      for (int i = line.length() - 1; i >= 0; i--) {
        if (Character.isDigit(line.charAt(i))) {
          c2 = line.charAt(i);
          break;
        }
      }
      counter += Integer.parseInt(Character.toString(c1) + Character.toString(c2));
    }
    return Integer.toString(counter);
  }

  @Override
  public String task_2() {
    String[] lines = input.split("\n");
    int counter = 0;
    for (String line : lines) {
      char c1 = '0', c2 = '0';
      line = line.replace("one", "one1one");
      line = line.replace("two", "two2two");
      line = line.replace("three", "three3three");
      line = line.replace("four", "four4four");
      line = line.replace("five", "five5five");
      line = line.replace("six", "six6six");
      line = line.replace("seven", "seven7seven");
      line = line.replace("eight", "eight8eight");
      line = line.replace("nine", "nine9nine");
      for (int i = 0; i < line.length(); i++) {
        if (Character.isDigit(line.charAt(i))) {
          c1 = line.charAt(i);
          break;
        }
      }
      for (int i = line.length() - 1; i >= 0; i--) {
        if (Character.isDigit(line.charAt(i))) {
          c2 = line.charAt(i);
          break;
        }
      }
      counter += Integer.parseInt(Character.toString(c1) + Character.toString(c2));
    }
    return Integer.toString(counter);
  }

}
