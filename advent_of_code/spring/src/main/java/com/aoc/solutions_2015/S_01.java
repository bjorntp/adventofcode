package com.aoc.solutions_2015;

import com.aoc.lib.*;

public class S_01 extends Solution {

  public S_01(String input) {
    super(input);
  }

  @Override
  public String task_1() {
    int level = 0;
    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) == ')') {
        level--;
      } else if (input.charAt(i) == '(') {
        level++;
      }
    }
    return Integer.toString(level);
  }

  @Override
  public String task_2() {
    int level = 0;
    int position = -1;
    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) == ')') {
        level--;
      } else if (input.charAt(i) == '(') {
        level++;
      }
      if (level == -1) {
        position = i + 1;
        return Integer.toString(position);
      }
    }
    return Integer.toString(position);
  }

}
