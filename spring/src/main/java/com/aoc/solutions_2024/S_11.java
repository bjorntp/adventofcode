package com.aoc.solutions_2024;

import java.util.HashMap;
import java.util.LinkedList;

import com.aoc.lib.*;

public class S_11 extends Solution {

  private ExecutionTimer ET;
  private HashMap<String, Long> savedNumbers;

  public S_11(String input) {
    super(input);
    ET = new ExecutionTimer();
  }

  @Override
  public String task_1() {
    ET.start();
    savedNumbers = new HashMap<>();
    String[] stringInput = input.split(" ");
    LinkedList<Long> numbers = new LinkedList<>();
    for (int i = 0; i < stringInput.length; i++) {
      numbers.add(Long.parseLong(stringInput[i]));
    }
    long sum = 0;
    for (Long number : numbers) {
      sum += countStones(number, 6);
    }

    ET.stop();
    System.out.println(ET.toString());
    return String.valueOf(sum);

  }

  @Override
  public String task_2() {
    ET.start();
    String[] stringInput = input.split(" ");
    LinkedList<Long> numbers = new LinkedList<>();
    savedNumbers = new HashMap<>();
    for (int i = 0; i < stringInput.length; i++) {
      numbers.add(Long.parseLong(stringInput[i]));
    }
    long sum = 0;
    for (Long number : numbers) {
      sum += countStones(number, 75);
    }
    ET.stop();
    System.out.println(ET.toString());
    return String.valueOf(sum);
  }

  private long countStones(long number, int level) {
    if (level == 0) {
      return 1l;
    }
    if (savedNumbers.containsKey(number + "l" + level)) {
      return savedNumbers.get(number + "l" + level);
    }
    if (number == 0) {
      savedNumbers.put(number + "l" + level, countStones(1l, level - 1));
      return savedNumbers.get(number + "l" + level);
    }
    long digitCounter = (long) (Math.log10(number) + 1);
    if (digitCounter % 2 != 0) {
      savedNumbers.put(number + "l" + level, countStones(number * 2024, level - 1));
      return savedNumbers.get(number + "l" + level);
    }
    double tempNumb = number / Math.pow(10, digitCounter / 2);
    long nmbl = (long) (tempNumb);
    long nmbr = (long) ((tempNumb - nmbl) * Math.pow(10, digitCounter / 2) + 0.5);
    if (nmbl == nmbr) {
      savedNumbers.put(number + "l" + level, 2 * countStones(nmbl, level - 1));
      return savedNumbers.get(number + "l" + level);
    }
    savedNumbers.put(number + "l" + level, countStones(nmbl, level - 1) + countStones(nmbr, level - 1));
    return savedNumbers.get(number + "l" + level);
  }
}
