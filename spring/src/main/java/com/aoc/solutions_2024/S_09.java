package com.aoc.solutions_2024;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aoc.lib.*;

public class S_09 extends Solution {
  private ExecutionTimer ET;
  private InputHandler IH;

  public S_09(String input) {
    super(input);
    ET = new ExecutionTimer();
    IH = new InputHandler(input);
  }

  @Override
  public String task_1() {
    ET.start();
    Pattern digitPattern = Pattern.compile("\\d");
    Matcher digitMatcher = digitPattern.matcher(input);
    long index = -1;
    long id = 0;
    ArrayList<Long> files = new ArrayList<>();
    while (digitMatcher.find()) {
      index++;
      if (index % 2 == 0) {
        for (int i = 0; i < Integer.parseInt(digitMatcher.group()); i++) {
          files.add(id);
        }
        id++;
      } else {
        for (int i = 0; i < Integer.parseInt(digitMatcher.group()); i++) {
          files.add(-1l);
        }
      }
    }
    for (int i = files.size() - 1; i > files.size() / 2; i--) {
      if (files.get(i) != -1) {
        long t = files.get(i);
        files.set(i, -1l);
        for (int j = 0; j < files.size(); j++) {
          if (files.get(j) == -1) {
            files.set(j, t);
            break;
          }
        }
      }
    }
    long total = 0;
    for (int i = 0; i < files.size(); i++) {
      long localid = files.get(i);
      if (localid == -1) {
        break;
      }
      total += i * localid;
    }
    ET.stop();

    System.out.println("Task 1: " + ET);
    return String.valueOf(total);
  }

  @Override
  public String task_2() {
    return "!";
  }
}
