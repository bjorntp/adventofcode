package com.aoc.lib;

public abstract class Solution {

  protected String input;
  private ExecutionTimer ET;

  public Solution(String input) {
    this.input = input;
    this.ET = new ExecutionTimer();
  }

  public String run_task_1() {
    ET.start();
    var ret = task_1();
    ET.stop();
    System.out.println("Execution time task 1: " + ET.toString());
    return ret;
  }

  public String run_task_2() {
    ET.start();
    var ret = task_2();
    ET.stop();
    System.out.println("Execution time task 2: " + ET.toString());
    return ret;
  }

  public abstract String task_1();

  public abstract String task_2();
}
