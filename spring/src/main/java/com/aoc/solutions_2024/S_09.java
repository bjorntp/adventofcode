package com.aoc.solutions_2024;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aoc.lib.*;

public class S_09 extends Solution {
  private ExecutionTimer ET;
  private InputHandler IH;

  public S_09(String input) {
    super(input);
    ET = new ExecutionTimer();
    IH = new InputHandler(input);
  }

  @Override
  public String task_1() {
    ET.start();
    Pattern digitPattern = Pattern.compile("\\d");
    Matcher digitMatcher = digitPattern.matcher(input);
    long index = -1;
    long id = 0;
    ArrayList<Long> files = new ArrayList<>();
    while (digitMatcher.find()) {
      index++;
      if (index % 2 == 0) {
        for (int i = 0; i < Integer.parseInt(digitMatcher.group()); i++) {
          files.add(id);
        }
        id++;
      } else {
        for (int i = 0; i < Integer.parseInt(digitMatcher.group()); i++) {
          files.add(-1l);
        }
      }
    }
    for (int i = files.size() - 1; i > files.size() / 2; i--) {
      if (files.get(i) != -1) {
        long t = files.get(i);
        files.set(i, -1l);
        for (int j = 0; j < files.size(); j++) {
          if (files.get(j) == -1) {
            files.set(j, t);
            break;
          }
        }
      }
    }
    long total = 0;
    for (int i = 0; i < files.size(); i++) {
      long localid = files.get(i);
      if (localid == -1) {
        break;
      }
      total += i * localid;
    }
    ET.stop();
    System.out.println("Task 1: " + ET);
    return String.valueOf(total);
  }

  @Override
  public String task_2() {
    ET.start();
    Pattern digitPattern = Pattern.compile("\\d");
    Matcher digitMatcher = digitPattern.matcher(input);
    long index = -1;
    long id = 0;
    ArrayList<digitString> files = new ArrayList<>();
    while (digitMatcher.find()) {
      index++;
      if (index % 2 == 0) {
        files.add(new digitString(id, Long.parseLong(digitMatcher.group())));
        id++;
      } else {
        files.add(new digitString(-1l, Long.parseLong(digitMatcher.group())));
      }
    }
    for (int i = files.size() - 1; i > 0; i--) {
      long rightDigit = files.get(i).getDigit();
      long rightLength = files.get(i).getLength();
      if (rightDigit != -1) {
        for (int j = 0; j < files.size(); j++) {
          if (files.get(j).getDigit() == -1 && files.get(j).getLength() >= rightLength) {
            if (j > i)
              break;
            digitString temp = files.remove(i);
            files.add(j, temp);
            long newLength = files.get(j + 1).getLength() - rightLength;
            files.get(j + 1).setLength(newLength);
            files.add(i, new digitString(-1, temp.getLength()));
            break;
          }
        }
      }
    }
    long total = 0;
    int multiplier = 0;
    for (int i = 0; i < files.size(); i++) {
      long localid = files.get(i).getDigit();
      if (localid == -1) {
        multiplier += files.get(i).getLength();
      } else {
        for (int j = 0; j < files.get(i).getLength(); j++) {
          total += multiplier * localid;
          multiplier++;
        }
      }
    }
    ET.stop();
    System.out.println("Task 2: " + ET);
    return String.valueOf(total);
  }

  /**
   * digitString
   */
  public class digitString {
    long digit;
    long length;

    public digitString(long digit, long length) {
      this.digit = digit;
      this.length = length;
    }

    public long getDigit() {
      return digit;
    }

    public long getLength() {
      return length;
    }

    public void setDigit(long digit) {
      this.digit = digit;
    }

    public void setLength(long length) {
      this.length = length;
    }
  }
}
