package com.aoc.solutions_2022;

import com.aoc.lib.*;

public class S_01 extends Solution {

  public S_01(String input) {
    super(input);
  }

  @Override
  public String task_1() {
    InputHandler ih = new InputHandler(input);
    String[] lines = ih.getLines();
    int counter = 0;
    for (int i = 1; i < lines.length; i++) {
      if (Integer.parseInt(lines[i]) > Integer.parseInt(lines[i - 1])) {
        counter++;
      }
    }
    return "" + counter;
  }

  @Override
  public String task_2() {
    InputHandler ih = new InputHandler(input);
    String[] lines = ih.getLines();
    Integer[] sumWindows = new Integer[lines.length - 2];
    for (int i = 2; i < lines.length; i++) {
      sumWindows[i - 2] = Integer.parseInt(lines[i - 2]) + Integer.parseInt(lines[i - 1]) + Integer.parseInt(lines[i]);
    }
    int counter = 0;
    for (int i = 1; i < sumWindows.length; i++) {
      if (sumWindows[i] > sumWindows[i - 1]) {
        counter++;
      }

    }
    return "" + counter;
  }

}
