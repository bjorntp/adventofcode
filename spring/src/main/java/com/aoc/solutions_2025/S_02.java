package com.aoc.solutions_2025;

import com.aoc.lib.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S_02 extends Solution {
  private InputHandler IH;

  public S_02(String input) {
    super(input);
    IH = new InputHandler(input);
  }

  @Override
  public String task_1() {
    Pattern pattern = Pattern.compile("(\\d+)\\1");
    return solver(pattern);
  }

  @Override
  public String task_2() {
    Pattern pattern = Pattern.compile("(\\d+)\\1+");
    return solver(pattern);
  }

  private String solver(Pattern pattern) {
    long invalidCounter = 0;
    String[] h = IH.getLines()[0].split(",");
    for (int i = 0; i < h.length; i++) {
      String[] k = h[i].split("-");
      for (long j = Long.parseLong(k[0]); j < Long.parseLong(k[1]) + 1; j++) {
        Matcher match = pattern.matcher(String.valueOf(j));
        if (match.matches()) {
          invalidCounter += j;
        }
      }
    }
    return String.valueOf(invalidCounter);
  }
}
