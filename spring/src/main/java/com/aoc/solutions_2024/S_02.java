package com.aoc.solutions_2024;

import com.aoc.lib.*;

public class S_02 extends Solution {
  private ExecutionTimer et;
  private InputHandler IH;

  public S_02(String input) {
    super(input);
    et = new ExecutionTimer();
    IH = new InputHandler(input);
  }

  @Override
  public String task_1() {
    String[] lines = IH.getLines();
    long safeCounter = 0;
    for (String string : lines) {
      boolean difference = true, increase = true, decrease = true;
      String[] a = string.split(" ");
      for (int i = 1; i < a.length; i++) {
        if (Integer.parseInt(a[i]) >= Integer.parseInt(a[i - 1])) {
          decrease = false;
        }
        if (Integer.parseInt(a[i]) <= Integer.parseInt(a[i - 1])) {
          increase = false;
        }
        int x = (int) Integer.parseInt(a[i]);
        int y = (int) Integer.parseInt(a[i - 1]);
        if (Math.abs(x - y) > 3) {
          difference = false;
        }
      }
      if (difference && (increase || decrease)) {
        safeCounter++;
      }

    }
    return String.valueOf(safeCounter);
  }

  @Override
  public String task_2() {
    String[] lines = IH.getLines();
    long safeCounter = 0;

    for (String line : lines) {
      boolean increase = false, decrease = false;
      int numErrors = 0;

      String[] tokens = line.split(" ");
      int[] numbers = new int[tokens.length];
      for (int i = 0; i < tokens.length; i++) {
        numbers[i] = Integer.parseInt(tokens[i]);
      }

      // Determine the trend: increasing or decreasing
      if (numbers[0] < numbers[numbers.length - 1]) {
        increase = true;
      } else {
        decrease = true;
      }

      // Check the sequence
      for (int i = 1; i < numbers.length; i++) {
        if (increase && numbers[i] <= numbers[i - 1]) {
          numErrors++;
        } else if (decrease && numbers[i] >= numbers[i - 1]) {
          numErrors++;
        }
        if (Math.abs(numbers[i] - numbers[i - 1]) > 3) {
          numErrors++;
        }
      }

      // Count as safe if errors are less than 2
      if (numErrors < 2) {
        safeCounter++;
      }
    }

    return String.valueOf(safeCounter);
  }
}
