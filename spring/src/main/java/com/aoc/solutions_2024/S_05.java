package com.aoc.solutions_2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.aoc.lib.*;

public class S_05 extends Solution {

  private ExecutionTimer ET = new ExecutionTimer();

  public S_05(String input) {
    super(input);
  }

  @Override
  public String task_1() {
    var rules = getRules(input.split("\n\n")[0].split("\n"));
    String[] s_section = input.split("\n\n")[1].split("\n");
    boolean safe;
    int total = 0;
    for (String row : s_section) {
      safe = true;
      String[] nmbs = row.split(",");
      InnerLoop: for (int i = nmbs.length - 1; i > 0; i--) {
        var arrayToCheck = Arrays.asList(Arrays.copyOfRange(nmbs, 0, i));
        if (rules.containsKey(nmbs[i])) {
          for (var comp : rules.get(nmbs[i])) {
            if (arrayToCheck.contains(comp)) {
              safe = false;
              System.out.println(row);
              break InnerLoop;
            }
          }
        }
      }
      if (safe) {
        total += Integer.parseInt(nmbs[nmbs.length / 2]);
      }
    }
    return String.valueOf(total);
  }

  @Override
  public String task_2() {
    var rules = getRules(input.split("\n\n")[0].split("\n"));
    String[] s_section = input.split("\n\n")[1].split("\n");
    ArrayList<LinkedList<String>> badRows = new ArrayList<LinkedList<String>>();
    boolean safe;
    for (String row : s_section) {
      safe = true;
      String[] nmbs = row.split(",");
      InnerLoop: for (int i = nmbs.length - 1; i > 0; i--) {
        var arrayToCheck = Arrays.asList(Arrays.copyOfRange(nmbs, 0, i));
        if (rules.containsKey(nmbs[i])) {
          for (var comp : rules.get(nmbs[i])) {
            if (arrayToCheck.contains(comp)) {
              safe = false;
              System.out.println(row);
              break InnerLoop;
            }
          }
        }
      }
      if (!safe) {
        badRows.add(new LinkedList<String>(Arrays.asList(nmbs)));
      }
    }
    int total = 0;
    ArrayList<List<String>> goodRows = new ArrayList<>();
    for (var row : badRows) {
      var badRow = row;
      for (int i = badRow.size() - 1; i > 0; i--) {
        var arrayToCheck = badRow.subList(0, i);
        if (rules.containsKey(badRow.get(i))) {
          for (var comp : rules.get(badRow.get(i))) {
            if (arrayToCheck.contains(comp)) {
              var temp = badRow.remove(i);
              badRow.add(0, temp);
              i = badRow.size();
              break;
            }
          }
        }
      }
      total += Integer.parseInt(badRow.get(badRow.size() / 2));
      goodRows.add(badRow);
    }
    return "" + total;
  }

  private HashMap<String, ArrayList<String>> getRules(String[] f_section) {
    HashMap<String, ArrayList<String>> rules = new HashMap<>();
    for (String row : f_section) {
      String[] temp = row.split("[|]");
      if (!rules.containsKey(temp[0])) {
        rules.put(temp[0], new ArrayList<>());
      }
      rules.get(temp[0]).add(temp[1]);
    }
    return rules;
  }
}
