package com.aoc.solutions_2024;

import java.util.List;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Collections;

import com.aoc.lib.*;

public class S_01 extends Solution {
  private InputHandler IH;

  public S_01(String input) {
    super(input);
    IH = new InputHandler(input);
  }

  @Override
  public String task_1() {
    String[] lines = IH.getLines();

    List<Long> l1 = new ArrayList<>();
    List<Long> l2 = new ArrayList<>();
    for (String line : lines) {
      String[] l = line.split(" +");
      l1.add(Long.parseLong(l[0]));
      l2.add(Long.parseLong(l[1]));
    }

    Collections.sort(l1);
    Collections.sort(l2);

    long sum = IntStream.range(0, l1.size())
        .mapToLong(i -> Math.abs(l1.get(i) - l2.get(i)))
        .sum();

    return String.valueOf(sum);
  }

  @Override
  public String task_2() {
    ArrayList<Long> l1 = new ArrayList<>();
    ArrayList<Long> l2 = new ArrayList<>();
    String[] lines = IH.getLines();
    for (String line : lines) {
      String[] l = line.split(" +");
      l1.add(Long.parseLong(l[0]));
      l2.add(Long.parseLong(l[1]));
    }
    Collections.sort(l1);
    Collections.sort(l2);

    long totalScore = 0;
    int i = 0;
    for (int j = 0; j < lines.length; j++) {
      long cc = 0;
      while (i < l2.size()) {
        if (l1.get(j).equals(l2.get(i))) {
          cc++;
        }
        if (l1.get(j) < l2.get(i)) {
          i -= (int) cc;
          break;
        }
        i++;
      }
      totalScore += cc * l1.get(j);
    }

    return String.valueOf(totalScore);
  }
}
