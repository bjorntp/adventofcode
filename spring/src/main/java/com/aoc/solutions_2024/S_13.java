package com.aoc.solutions_2024;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aoc.lib.*;

public class S_13 extends Solution {

  public S_13(String input) {
    super(input);
  }

  @Override
  public String task_1() {
    return calc(false);
  }

  @Override
  public String task_2() {
    return calc(true);
  }

  private String calc(boolean task2) {
    Pattern numberPattern = Pattern.compile("\\d+");
    Matcher matchNumber = numberPattern.matcher(input);
    double total = 0;

    while (matchNumber.find()) {
      double am = Double.parseDouble(matchNumber.group());
      matchNumber.find();
      double cm = Double.parseDouble(matchNumber.group());
      matchNumber.find();
      double bm = Double.parseDouble(matchNumber.group());
      matchNumber.find();
      double dm = Double.parseDouble(matchNumber.group());
      matchNumber.find();
      double y1 = Double.parseDouble(matchNumber.group());
      matchNumber.find();
      double y2 = Double.parseDouble(matchNumber.group());
      if (task2) {
        y1 += 10000000000000.0;
        y2 += 10000000000000.0;
      }
      double detA = am * dm - bm * cm;
      double ami = dm / detA;
      double bmi = -bm / detA;
      double cmi = -cm / detA;
      double dmi = am / detA;
      double A = ami * y1 + bmi * y2;
      double B = cmi * y1 + dmi * y2;

      if ((A % 1 < 0.001 || A % 1 > 0.99)
          && (B % 1 < 0.001 || B % 1 > 0.99)) {
        total += A * 3 + B;
      }
    }
    return String.format("%.0f", total);
  }
}
