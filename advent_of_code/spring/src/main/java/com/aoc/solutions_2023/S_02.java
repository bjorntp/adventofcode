package com.aoc.solutions_2023;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.aoc.lib.*;

public class S_02 extends Solution {

  public S_02(String input) {
    super(input);
  }

  @Override
  public String task_1() {
    String[] lines = input.split("\n");
    int index = 1, counter = 0;
    int MAX_RED = 12;
    int MAX_GREEN = 13;
    int MAX_BLUE = 14;
    Pattern red = Pattern.compile("\\d+ red");
    Pattern green = Pattern.compile("\\d+ green");
    Pattern blue = Pattern.compile("\\d+ blue");
    Matcher mred, mgreen, mblue;

    for (String line : lines) {
      mred = red.matcher(line);
      mgreen = green.matcher(line);
      mblue = blue.matcher(line);
      ArrayList<Integer> ared = new ArrayList<Integer>();
      ArrayList<Integer> agreen = new ArrayList<Integer>();
      ArrayList<Integer> ablue = new ArrayList<Integer>();

      while (mred.find()) {
        ared.add(Integer.parseInt(mred.group().split(" ")[0]));
      }

      while (mgreen.find()) {
        agreen.add(Integer.parseInt(mgreen.group().split(" ")[0]));
      }

      while (mblue.find()) {
        ablue.add(Integer.parseInt(mblue.group().split(" ")[0]));
      }

      Collections.sort(ared);
      Collections.reverse(ared);
      Collections.sort(agreen);
      Collections.reverse(agreen);
      Collections.sort(ablue);
      Collections.reverse(ablue);
      if (ared.get(0) <= MAX_RED && agreen.get(0) <= MAX_GREEN && ablue.get(0) <= MAX_BLUE) {
        counter += index;
      }
      index++;
    }
    return Integer.toString(counter);
  }

  @Override
  public String task_2() {
    String[] lines = input.split("\n");
    int counter = 0;
    Pattern red = Pattern.compile("\\d+ red");
    Pattern green = Pattern.compile("\\d+ green");
    Pattern blue = Pattern.compile("\\d+ blue");
    Matcher mred, mgreen, mblue;

    for (String line : lines) {
      mred = red.matcher(line);
      mgreen = green.matcher(line);
      mblue = blue.matcher(line);
      ArrayList<Integer> ared = new ArrayList<Integer>();
      ArrayList<Integer> agreen = new ArrayList<Integer>();
      ArrayList<Integer> ablue = new ArrayList<Integer>();

      while (mred.find()) {
        ared.add(Integer.parseInt(mred.group().split(" ")[0]));
      }

      while (mgreen.find()) {
        agreen.add(Integer.parseInt(mgreen.group().split(" ")[0]));
      }

      while (mblue.find()) {
        ablue.add(Integer.parseInt(mblue.group().split(" ")[0]));
      }

      Collections.sort(ared);
      Collections.reverse(ared);
      Collections.sort(agreen);
      Collections.reverse(agreen);
      Collections.sort(ablue);
      Collections.reverse(ablue);

      counter += ared.get(0) * agreen.get(0) * ablue.get(0);
    }
    return Integer.toString(counter);
  }

}
