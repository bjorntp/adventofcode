package com.aoc.solutions_2015;

import com.aoc.lib.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S_06 extends Solution {

  public S_06(String input) {
    super(input);
  }

  @Override
  public String task_1() {
    Pattern pn = Pattern.compile("\\d+");
    Matcher m;
    int[][] mx = new int[1000][1000];
    String[] lines = input.split("\n");
    for (String string : lines) {
      m = pn.matcher(string);
      Integer[] x = new Integer[4];
      m = pn.matcher(string);
      m.find();
      x[0] = Integer.parseInt(m.group());
      m.find();
      x[1] = Integer.parseInt(m.group());
      m.find();
      x[2] = Integer.parseInt(m.group());
      m.find();
      x[3] = Integer.parseInt(m.group());
      if (string.contains("on")) {
        for (int i = x[0]; i <= x[2]; i++) {
          for (int j = x[1]; j <= x[3]; j++) {
            mx[i][j] = 1;
          }
        }
      } else if (string.contains("off")) {
        for (int i = x[0]; i <= x[2]; i++) {
          for (int j = x[1]; j <= x[3]; j++) {
            mx[i][j] = 0;
          }
        }
      } else {
        for (int i = x[0]; i <= x[2]; i++) {
          for (int j = x[1]; j <= x[3]; j++) {
            mx[i][j] = 1 - mx[i][j];
          }
        }
      }
    }
    int cc = 0;
    for (int i = 0; i < 1000; i++) {
      for (int j = 0; j < 1000; j++) {
        if (mx[i][j] == 1) {
          cc++;
        }
      }
    }

    return "Number of lights " + Integer.toString(cc);
  }

  @Override
  public String task_2() {

    Pattern pn = Pattern.compile("\\d+");
    Matcher m;
    int[][] mx = new int[1000][1000];
    String[] lines = input.split("\n");
    for (String string : lines) {
      m = pn.matcher(string);
      Integer[] x = new Integer[4];
      m = pn.matcher(string);
      m.find();
      x[0] = Integer.parseInt(m.group());
      m.find();
      x[1] = Integer.parseInt(m.group());
      m.find();
      x[2] = Integer.parseInt(m.group());
      m.find();
      x[3] = Integer.parseInt(m.group());
      if (string.contains("on")) {
        for (int i = x[0]; i <= x[2]; i++) {
          for (int j = x[1]; j <= x[3]; j++) {
            mx[i][j]++;
          }
        }
      } else if (string.contains("off")) {
        for (int i = x[0]; i <= x[2]; i++) {
          for (int j = x[1]; j <= x[3]; j++) {
            if (mx[i][j] != 0) {
              mx[i][j]--;
            }
          }
        }
      } else {
        for (int i = x[0]; i <= x[2]; i++) {
          for (int j = x[1]; j <= x[3]; j++) {
            mx[i][j]++;
            mx[i][j]++;
          }
        }
      }
    }
    int cc = 0;
    for (int i = 0; i < 1000; i++) {
      for (int j = 0; j < 1000; j++) {
        if (mx[i][j] != 0) {
          cc += mx[i][j];
        }
      }
    }

    return "Total brightness " + Integer.toString(cc);
  }
}
