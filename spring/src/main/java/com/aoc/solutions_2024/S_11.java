package com.aoc.solutions_2024;

import com.aoc.lib.*;

public class S_11 extends Solution {

  private ExecutionTimer ET;

  public S_11(String input) {
    super(input);
    ET = new ExecutionTimer();
  }

  @Override
  public String task_1() {
    ET.start();
    ET.stop();
    System.out.println(ET.toString());
    return "!";
  }

  @Override
  public String task_2() {
    ET.start();
    ET.stop();
    System.out.println(ET.toString());
    return "!";
  }
}
