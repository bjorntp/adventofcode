package com.aoc.solutions_2024;

import java.util.ArrayList;

import com.aoc.lib.*;

public class S_07 extends Solution {

  InputHandler IH;

  public S_07(String input) {
    super(input);
    IH = new InputHandler(input);
  }

  @Override
  public String task_1() {
    String[] lines = IH.getLines();
    long total = 0;
    for (var line : lines) {
      long result = Long.parseLong(line.split(":")[0]);
      String[] variablesString = line.split(": ")[1].split(" ");
      ArrayList<Long> vars = new ArrayList<>();
      for (int i = variablesString.length - 1; i >= 0; i--) {
        vars.add(Long.parseLong(variablesString[i]));
      }
      if (addmul_test(result, vars, 0, vars.size() - 1)) {
        total += result;
      }
    }
    return String.valueOf(total);
  }

  @Override
  public String task_2() {
    String[] lines = IH.getLines();
    long total = 0;
    for (var line : lines) {
      long result = Long.parseLong(line.split(":")[0]);
      String[] variablesString = line.split(": ")[1].split(" ");
      ArrayList<Long> vars = new ArrayList<>();
      for (int i = variablesString.length - 1; i >= 0; i--) {
        vars.add(Long.parseLong(variablesString[i]));
      }
      if (addmuland_test(result, vars, 0, vars.size() - 1)) {
        total += result;
      }
    }
    return String.valueOf(total);
  }

  private boolean addmul_test(long result, ArrayList<Long> variables, long currentNumber, int index) {

    if (currentNumber == 0) {
      currentNumber = variables.get(index);
      index--;
    }
    if (currentNumber > result) {
      return false;
    }
    if (index == -1) {
      if (currentNumber == result) {
        return true;
      } else {
        return false;
      }
    }
    long val = variables.get(index);
    boolean booleanAdd = addmul_test(result, variables, currentNumber + val, index - 1);
    if (booleanAdd) {
      return true;
    }
    boolean booleanMul = addmul_test(result, variables, currentNumber * val, index - 1);
    if (booleanMul) {
      return true;
    }
    return false;
  }

  private boolean addmuland_test(long result, ArrayList<Long> variables, long currentNumber, int index) {

    if (currentNumber == 0) {
      currentNumber = variables.get(index);
      index--;
    }
    if (currentNumber > result) {
      return false;
    }
    if (index == -1) {
      if (currentNumber == result) {
        return true;
      } else {
        return false;
      }
    }
    long val = variables.get(index);
    int nmbDigits = (int) Math.log10(val) + 1;
    long and = (long) (currentNumber * Math.pow(10, nmbDigits) + val);
    boolean booleanAdd = addmuland_test(result, variables, currentNumber + val, index - 1);
    if (booleanAdd) {
      return true;
    }
    boolean booleanMul = addmuland_test(result, variables, currentNumber * val, index - 1);
    if (booleanMul) {
      return true;
    }
    boolean booleanAnd = addmuland_test(result, variables, and, index - 1);
    if (booleanAnd) {
      return true;
    }
    return false;
  }
}
