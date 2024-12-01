package com.aoc.solutions_2023;

import com.aoc.lib.*;

public class S_06 extends Solution {

  public S_06(String input) {
    super(input);
  }

  @Override
  public String task_1() {
    String row_1 = input.split("\\n")[0].split(": ")[1].trim().replaceAll(" +", " ");
    String row_2 = input.split("\\n")[1].split(": ")[1].trim().replaceAll(" +", " ");
    String[] times = row_1.split(" ");
    int winmul = 1;
    String[] distances = row_2.split(" ");
    for (int i = 0; i < times.length; i++) {
      int lowerBreak = 0, upperBreak = 0;
      for (int j = 0; j < Integer.parseInt(times[i]); j++) {
        if (j * (Integer.parseInt(times[i]) - j) >= Integer.parseInt(distances[i])) {
          lowerBreak = j;
          break;
        }
      }
      System.out.println("1 " + lowerBreak);
      for (int j = Integer.parseInt(times[i]); j > 0; j--) {
        if (j * (Integer.parseInt(times[i]) - j) >= Integer.parseInt(distances[i])) {
          upperBreak = j;
          break;
        }
      }
      System.out.println("2 " + upperBreak + "\n\n");
      winmul = winmul * (upperBreak - lowerBreak + 1);
    }
    return Integer.toString(winmul);
  }

  @Override
  public String task_2() {
    String time = input.split("\\n")[0].split(": ")[1].replaceAll(" ", "");
    String distance = input.split("\\n")[1].split(": ")[1].replaceAll(" ", "");

    Long lowerBreak = 0l, upperBreak = 0l;
    for (Long j = 0l; j < Long.parseLong(time); j++) {
      if (j * (Long.parseLong(time) - j) >= Long.parseLong(distance)) {
        lowerBreak = j;
        break;
      }
    }
    System.out.println("1 " + lowerBreak);
    for (Long j = Long.parseLong(time); j > 0l; j--) {
      if (j * (Long.parseLong(time) - j) >= Long.parseLong(distance)) {
        upperBreak = j;
        break;
      }
    }
    System.out.println("2 " + upperBreak);
    System.out.println("Time: " + time + "\nDistance: " + distance + "\n\n");
    return Long.toString(upperBreak - lowerBreak + 1);
  }
}
