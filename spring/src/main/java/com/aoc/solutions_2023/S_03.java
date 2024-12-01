package com.aoc.solutions_2023;

import com.aoc.lib.*;

public class S_03 extends Solution {

  public S_03(String input) {
    super(input);
  }

  @Override
  public String task_1() {
    String[] lines = input.split("\n");
    char[][] table = new char[lines.length + 2][lines[0].length() + 2];
    int counter = 0;
    for (int i = 0; i < lines[0].length() + 2; i++) {
      table[0][i] = '.';
      table[table.length - 1][i] = '.';
    }
    for (int row = 1; row < table.length - 1; row++) {
      table[row][0] = '.';
      for (int col = 1; col < table[0].length - 1; col++) {
        table[row][col] = lines[row - 1].charAt(col - 1);
      }
      table[row][table[0].length - 1] = '.';
    }
    for (int row = 1; row < table.length - 1; row++) {
      boolean isDigit = false;
      boolean symbol = false;
      StringBuilder digitStringBuilder = new StringBuilder();
      for (int col = 1; col < table[0].length; col++) {
        System.out.print(table[row][col]);
        if (Character.isDigit(table[row][col])) {
          isDigit = true;
          digitStringBuilder.append(table[row][col]);
          if (!symbol) {
            if (!Character.isDigit(table[row - 1][col - 1]) && table[row - 1][col - 1] != '.' ||
                !Character.isDigit(table[row - 1][col]) && table[row - 1][col] != '.' ||
                !Character.isDigit(table[row - 1][col + 1]) && table[row - 1][col + 1] != '.' ||
                !Character.isDigit(table[row][col - 1]) && table[row][col - 1] != '.' ||
                !Character.isDigit(table[row][col + 1]) && table[row][col + 1] != '.' ||
                !Character.isDigit(table[row + 1][col - 1]) && table[row + 1][col - 1] != '.' ||
                !Character.isDigit(table[row + 1][col]) && table[row + 1][col] != '.' ||
                !Character.isDigit(table[row + 1][col + 1]) && table[row + 1][col + 1] != '.') {
              symbol = true;
            }
          }
        } else if (isDigit) {
          if (symbol) {
            counter += Integer.parseInt(digitStringBuilder.toString());
          }
          isDigit = false;
          symbol = false;
          digitStringBuilder = new StringBuilder();
        }
      }
      System.out.println("");
    }
    return Integer.toString(counter);
  }

  @Override
  public String task_2() {
    String[] lines = input.split("\n");
    char[][] table = new char[lines.length + 2][lines[0].length() + 2];
    int counter = 0;
    for (int i = 0; i < lines[0].length() + 2; i++) {
      table[0][i] = '.';
      table[table.length - 1][i] = '.';
    }
    for (int row = 1; row < table.length - 1; row++) {
      table[row][0] = '.';
      for (int col = 1; col < table[0].length - 1; col++) {
        table[row][col] = lines[row - 1].charAt(col - 1);
      }
      table[row][table[0].length - 1] = '.';
    }
    for (int row = 1; row < table.length - 1; row++) {
      boolean isDigit = false;
      boolean symbol = false;
      StringBuilder digitStringBuilder = new StringBuilder();
      for (int col = 1; col < table[0].length; col++) {
        System.out.print(table[row][col]);
        if (Character.isDigit(table[row][col])) {
          isDigit = true;
          digitStringBuilder.append(table[row][col]);
          if (!symbol) {
            if (!Character.isDigit(table[row - 1][col - 1]) && table[row - 1][col - 1] != '.' ||
                !Character.isDigit(table[row - 1][col]) && table[row - 1][col] != '.' ||
                !Character.isDigit(table[row - 1][col + 1]) && table[row - 1][col + 1] != '.' ||
                !Character.isDigit(table[row][col - 1]) && table[row][col - 1] != '.' ||
                !Character.isDigit(table[row][col + 1]) && table[row][col + 1] != '.' ||
                !Character.isDigit(table[row + 1][col - 1]) && table[row + 1][col - 1] != '.' ||
                !Character.isDigit(table[row + 1][col]) && table[row + 1][col] != '.' ||
                !Character.isDigit(table[row + 1][col + 1]) && table[row + 1][col + 1] != '.') {
              symbol = true;
            }
          }
        } else if (isDigit) {
          if (symbol) {
            counter += Integer.parseInt(digitStringBuilder.toString());
          }
          isDigit = false;
          symbol = false;
          digitStringBuilder = new StringBuilder();
        }
      }
      System.out.println("");
    }
    return Integer.toString(counter);
  }
}
