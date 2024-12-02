package com.aoc.solutions_2024;

import com.aoc.lib.*;

public class S_02 extends Solution {
  private ExecutionTimer ET;
  private InputHandler IH;

  public S_02(String input) {
    super(input);
    ET = new ExecutionTimer();
    IH = new InputHandler(input);
  }

  @Override
  public String task_1() {
    ET.start();
    String[] lines = IH.getLines();
    long safeCounter = 0;
    for (String line : lines) {
      String[] tokens = line.split(" ");
      int[] numbers = new int[tokens.length];
      for (int i = 0; i < tokens.length; i++) {
        numbers[i] = Integer.parseInt(tokens[i]);
      }
      if (isSafe(numbers)) {
        safeCounter++;
      }
    }
    ET.stop();
    System.out.println(ET);
    return String.valueOf(safeCounter);
  }

  @Override
  public String task_2() {
    ET.start();
    String[] lines = IH.getLines();
    long safeCounter = 0;
    for (String line : lines) {
      String[] tokens = line.split(" ");
      int[] numbers = new int[tokens.length];
      for (int i = 0; i < tokens.length; i++) {
        numbers[i] = Integer.parseInt(tokens[i]);
      }
      if (isSafe(numbers)) {
        safeCounter++;
      } else {
        for (int i = 0; i < numbers.length; i++) {
          if (isSafe(removeIndex(i, numbers))) {
            safeCounter++;
            break;
          }
        }
      }
    }
    ET.stop();
    System.out.println(ET);
    return String.valueOf(safeCounter);
  }

  private boolean isSafe(int[] x) {
    boolean difference = true, increase = true, decrease = true;
    for (int i = 1; i < x.length; i++) {
      if (x[i] >= x[i - 1]) {
        decrease = false;
      }
      if (x[i] <= x[i - 1]) {
        increase = false;
      }
      if (Math.abs(x[i] - x[i - 1]) > 3) {
        difference = false;
      }
    }
    return difference && (increase || decrease);
  }

  private int[] removeIndex(int i, int[] a) {
    int b[] = new int[a.length - 1];
    for (int j = 0; j < b.length; j++) {
      if (j == i) {
        break;
      }
      b[j] = a[j];
    }
    for (int j = i; j < b.length; j++) {
      b[j] = a[j + 1];
    }
    return b;
  }
}
