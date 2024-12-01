package com.aoc.solutions_2015;

import com.aoc.lib.*;

import java.util.ArrayList;

public class S_02 extends Solution {

  public S_02(String input) {
    super(input);
  }

  @Override
  public String task_1() {
    String[] split = input.split("\\r?\\n");
    int total = 0;

    for (String line : split) {
      ArrayList<Integer> list = new ArrayList<Integer>();
      for (String element : line.split("x")) {
        list.add(Integer.parseInt(element));
      }
      list.sort(null);
      int input_value = 2 * (list.get(0) * list.get(1) + list.get(1) * list.get(2) + list.get(2) * list.get(0))
          + list.get(0) * list.get(1);
      total += input_value;
    }

    return "" + total;
  }

  @Override
  public String task_2() {
    String[] split = input.split("\\r?\\n");
    int total = 0;

    for (String line : split) {
      ArrayList<Integer> list = new ArrayList<Integer>();
      for (String element : line.split("x")) {
        list.add(Integer.parseInt(element));
      }
      list.sort(null);
      int input_value = list.get(0) * 2 + list.get(1) * 2 + list.get(0) * list.get(1) * list.get(2);
      total += input_value;
    }

    return "" + total;
  }

}
