package com.aoc.solutions_2023;

import java.util.ArrayList;
import java.util.HashMap;
import com.aoc.lib.*;

/**
 * S_08
 */
public class S_08 extends Solution {

  private InputHandler inputHandler;

  public S_08(String input) {
    super(input);
    inputHandler = new InputHandler(input);
  }

  @Override
  public String task_1() {
    String[] lines = inputHandler.getLines();
    String sequence = lines[0];
    HashMap<String, Tuple<String, String>> mappings = new HashMap<>();
    for (int i = 2; i < lines.length; i++) {
      String startNode = lines[i].split(" = ")[0];
      Tuple<String, String> endNodes = new Tuple<>("", "");
      String[] end = lines[i].split(" = ")[1].replaceAll("[(),]", "").split(" ");
      endNodes.setX(end[0]);
      endNodes.setY(end[1]);
      mappings.put(startNode, endNodes);
    }
    String currentNode = "AAA";
    for (int i = 0; true; i++) {
      Character directionChar = sequence.charAt(i % sequence.length());
      if (directionChar == 'L') {
        currentNode = mappings.get(currentNode).getX();
      } else {
        currentNode = mappings.get(currentNode).getY();
      }

      if (currentNode.equals("ZZZ")) {
        return Integer.toString(i + 1);
      }
    }
  }

  @Override
  public String task_2() {
    String[] lines = inputHandler.getLines();
    String sequence = lines[0];
    HashMap<String, Tuple<String, String>> mappings = new HashMap<>();
    for (int i = 2; i < lines.length; i++) {
      String startNode = lines[i].split(" = ")[0];
      Tuple<String, String> endNodes = new Tuple<>("", "");
      String[] end = lines[i].split(" = ")[1].replaceAll("[(),]", "").split(" ");
      endNodes.setX(end[0]);
      endNodes.setY(end[1]);
      mappings.put(startNode, endNodes);
    }
    ArrayList<String> startingNodes = new ArrayList<>();
    for (var key : mappings.keySet()) {
      if (key.endsWith("A")) {
        startingNodes.add(key);
      }
    }
    String[] currentNodes = new String[startingNodes.size()];
    for (int i = 0; i < currentNodes.length; i++) {
      currentNodes[i] = startingNodes.get(i);
    }
    Long[] steps = new Long[currentNodes.length];
    for (int i = 0; i < currentNodes.length; i++) {
      String currentNode = currentNodes[i];
      String endNode = "";
      int stepsBetween = 0;
      for (int j = 0; true; j++) {
        Character directionChar = sequence.charAt(j % sequence.length());
        if (directionChar == 'L') {
          currentNode = mappings.get(currentNode).getX();
        } else {
          currentNode = mappings.get(currentNode).getY();
        }
        stepsBetween++;
        if (currentNode.endsWith("Z")) {
          if (endNode.equals("")) {
            endNode = currentNode;
            stepsBetween = 0;
          } else {
            System.out.println(stepsBetween);
            steps[i] = (long) stepsBetween;
            break;
          }
        }
      }
    }
    Long currentLcm = LCM(steps[0], steps[1]);
    for (int i = 2; i < steps.length; i++) {
      currentLcm = LCM(currentLcm, steps[i]);
    }
    return Long.toString(currentLcm);
  }

  /**
   * Calculate Lowest Common Multiplier
   */
  private Long LCM(Long a, Long b) {
    return (a * b) / GCF(a, b);
  }

  /**
   * Calculate Greatest Common Factor
   */
  private Long GCF(Long a, Long b) {
    if (b == 0) {
      return a;
    } else {
      return (GCF(b, a % b));
    }
  }

}
