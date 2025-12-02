package com.aoc.solutions_2025;

import com.aoc.lib.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S_01 extends Solution {
  private InputHandler IH;

  public S_01(String input) {
    super(input);
    IH = new InputHandler(input);
  }

  @Override
  public String task_1() {

    int j = 50;
    int zeroCounter = 0;

    for (int i = 0; i < IH.getLines().length; i++) {

      int DIRECTION = 1;
      if (IH.getLines()[i].contains("L")) {
        DIRECTION = -1;
      }

      j += getNumber(IH.getLines()[i]) * DIRECTION;

      if (j < 0) {
        int amountToIncrement = (-j / 100) + 1;
        int newj = j + 100 * amountToIncrement;
        j = newj;
        if (newj == 100) {
          j = 0;
          amountToIncrement -= 1;
        } else {
          j = newj;
        }
      } else if (j > 99) {
        int amountToDecrement = (j / 100);
        j -= amountToDecrement * 100;
      }

      if (j == 0) {
        zeroCounter++;
      }
    }
    return String.valueOf(zeroCounter);
  }

  @Override
  public String task_2() {

    int j = 50;
    int zeroCounter = 0;

    for (int i = 0; i < IH.getLines().length; i++) {

      int DIRECTION = 1;
      if (IH.getLines()[i].contains("L")) {
        DIRECTION = -1;
      }

      int oldj = j;
      j += getNumber(IH.getLines()[i]) * DIRECTION;

      if (j < 0) {
        int amountToIncrement = (int) (-j / 100) + 1;
        int newj = j + 100 * amountToIncrement;
        if (newj == 100) {
          j = 0;
          amountToIncrement--;
        } else {
          j = newj;
        }
        if (oldj == 0) {
          amountToIncrement--;
        }
        zeroCounter += amountToIncrement;
      } else if (j > 99) {
        int amountToDecrement = (int) (j / 100);
        j -= amountToDecrement * 100;
        if (j == 0) {
          amountToDecrement--;
        }
        zeroCounter += amountToDecrement;
      }
      if (j == 0) {
        zeroCounter++;
      }
    }
    return String.valueOf(zeroCounter);
  }

  private int getNumber(String s) {
    Matcher intMatcher = Pattern.compile("\\d+").matcher(s);
    if (intMatcher.find()) {
      return Integer.parseInt(intMatcher.group());
    } else {
      return 0;
    }
  }
}
