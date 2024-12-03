package com.aoc.solutions_2024;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aoc.lib.*;

public class S_03 extends Solution {

  private static final Pattern MULPATTERN = Pattern.compile("mul\\(\\d+,\\d+\\)");
  private static final Pattern DOPATTERN = Pattern.compile("do\\(\\)");
  private static final Pattern DONTPATTERN = Pattern.compile("don't\\(\\)");

  public S_03(String input) {
    super(input);
  }

  @Override
  public String task_1() {
    Matcher mulMatcher;
    long total = 0;

    mulMatcher = MULPATTERN.matcher(input);
    while (mulMatcher.find()) {
      String[] s = mulMatcher.group().replaceAll("mul\\(", "").replaceAll("\\)", "").split(",");
      total += Long.parseLong(s[0]) * Long.parseLong(s[1]);
    }
    return Long.toString(total);
  }

  @Override
  public String task_2() {
    Matcher doMatcher = DOPATTERN.matcher(input);
    Matcher dontMatcher = DONTPATTERN.matcher(input);
    Matcher mulMatcher = MULPATTERN.matcher(input);
    ArrayList<Tuple<Integer, Integer>> ranges = new ArrayList<>();
    long total = 0;

    dontMatcher.find();
    ranges.add(new Tuple<Integer, Integer>(0, dontMatcher.start()));
    while (doMatcher.find(ranges.getLast().getY())) {
      if (dontMatcher.find(doMatcher.start())) {
        ranges.add(new Tuple<Integer, Integer>(doMatcher.start(),
            dontMatcher.start()));
      } else {
        ranges.add(new Tuple<Integer, Integer>(doMatcher.start(), input.length()));
      }
    }

    while (mulMatcher.find()) {
      for (var range : ranges) {
        if (mulMatcher.start() >= range.getX() && mulMatcher.start() <= range.getY()) {
          String[] s = mulMatcher.group().replaceAll("mul\\(", "").replaceAll("\\)", "").split(",");
          total += Long.parseLong(s[0]) * Long.parseLong(s[1]);
          break;
        }
      }
    }
    return Long.toString(total);
  }
}
