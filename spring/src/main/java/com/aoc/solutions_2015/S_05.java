package com.aoc.solutions_2015;

import com.aoc.lib.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S_05 extends Solution {

  public S_05(String input) {
    super(input);
  }

  @Override
  public String task_1() {
    int cc = 0;
    String[] lines = input.split("\n");
    Pattern c1 = Pattern.compile("[aeiou].*[aeiou].*[aeiou]");
    Pattern c2 = Pattern.compile("(.)\\1");
    Pattern c3 = Pattern.compile("ab|cd|pq|xy");
    Matcher m1, m2, m3;
    for (String string : lines) {
      m1 = c1.matcher(string);
      m2 = c2.matcher(string);
      m3 = c3.matcher(string);
      if (m1.find() && m2.find() && !m3.find()) {
        cc++;
      }
    }
    return Integer.toString(cc);
  }

  @Override
  public String task_2() {
    int cc = 0;
    String[] lines = input.split("\n");
    Pattern c1 = Pattern.compile("(..).*\\1");
    Pattern c2 = Pattern.compile("(.).\\1");
    Matcher m1, m2;
    for (String string : lines) {
      m1 = c1.matcher(string);
      m2 = c2.matcher(string);
      if (m1.find() && m2.find()) {
        cc++;
      }
    }
    return Integer.toString(cc);
  }
}
