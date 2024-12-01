package com.aoc.solutions_2023;

import java.util.ArrayList;

import com.aoc.lib.*;

public class S_09 extends Solution {

  private InputHandler input;

  public S_09(String input) {
    super(input);
    this.input = new InputHandler(input);

  }

  public String task_1() {
    String[] lines = input.getLines();
    Long total = 0l;
    for (String firstLine : lines) {
      String lineNumbers[] = firstLine.split(" ");
      ArrayList<ArrayList<Long>> lineMapping = new ArrayList<>();
      lineMapping.add(new ArrayList<>());
      for (int i = 0; i < lineNumbers.length; i++) {
        lineMapping.get(0).add(Long.parseLong(lineNumbers[i]));
      }
      Boolean x = true;
      int index = 0;
      while (x) {
        lineMapping.add(new ArrayList<>());
        for (int i = 0; i < lineMapping.get(index).size() - 1; i++) {
          long n = lineMapping.get(index).get(i + 1);
          long n2 = lineMapping.get(index).get(i);
          lineMapping.getLast().add(n - n2);
        }
        if (lineMapping.getLast().size() == 1) {
          x = false;
        }
        index++;
      }
      for (int i = lineMapping.size() - 1; i > 0; i--) {
        lineMapping.get(i - 1).add(lineMapping.get(i).getLast() + lineMapping.get(i - 1).getLast());
      }
      total += lineMapping.getFirst().getLast();
    }

    return Long.toString(total);
  }

  public String task_2() {
    String[] lines = input.getLines();
    Long total = 0l;
    for (String firstLine : lines) {
      String lineNumbers[] = firstLine.split(" ");
      ArrayList<ArrayList<Long>> lineMapping = new ArrayList<>();
      lineMapping.add(new ArrayList<>());
      for (int i = 0; i < lineNumbers.length; i++) {
        lineMapping.get(0).add(Long.parseLong(lineNumbers[i]));
      }
      Boolean x = true;
      int index = 0;
      while (x) {
        lineMapping.add(new ArrayList<>());
        for (int i = 0; i < lineMapping.get(index).size() - 1; i++) {
          long n = lineMapping.get(index).get(i + 1);
          long n2 = lineMapping.get(index).get(i);
          lineMapping.getLast().add(n - n2);
        }
        if (lineMapping.getLast().size() == 1) {
          x = false;
        }
        index++;
      }
      for (int i = lineMapping.size() - 1; i > 0; i--) {
        lineMapping.get(i - 1).add(0, lineMapping.get(i - 1).getFirst() - lineMapping.get(i).getFirst());
      }
      total += lineMapping.getFirst().getFirst();
    }

    return Long.toString(total);
  }

}
