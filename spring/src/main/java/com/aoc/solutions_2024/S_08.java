package com.aoc.solutions_2024;

import java.util.ArrayList;
import java.util.TreeMap;

import com.aoc.lib.*;

public class S_08 extends Solution {

  InputHandler IH;

  public S_08(String input) {
    super(input);
    IH = new InputHandler(input);
  }

  @Override
  public String task_1() {
    Character[][] map = IH.getMatrix();
    TreeMap<Character, Node> nodes = new TreeMap<>();
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] != '.') {
          char c = map[i][j];
          if (!nodes.containsKey(c)) {
            nodes.put(c, new Node());
          }
          nodes.get(c).addPosition(j, i);
        }
      }
    }
    for (var node : nodes.entrySet()) {
      int length = node.getValue().getLength();
      if (length != 1) {
        ArrayList<Integer> xPositions = node.getValue().getX();
        ArrayList<Integer> yPositions = node.getValue().getY();
        for (int i = 0; i < length; i++) {
          for (int j = 0; j < length; j++) {
            if (i == j) {
              j++;
            }
            if (j == length) {
              break;
            }
            int x = xPositions.get(i) - xPositions.get(j);
            int y = yPositions.get(i) - yPositions.get(j);
            x += xPositions.get(i);
            y += yPositions.get(i);
            if (x >= 0 && y >= 0 && y < map.length && x < map[0].length) {
              map[y][x] = '#';
            }
          }
        }
      }
    }
    int counter = 0;
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        if (map[i][j] == '#')
          counter++;
        System.out.print(map[i][j]);
      }
      System.out.println("");
    }
    return String.valueOf(counter);
  }

  @Override
  public String task_2() {
    Character[][] map = IH.getMatrix();
    int counter = 0;
    TreeMap<Character, Node> nodes = new TreeMap<>();
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] != '.') {
          char c = map[i][j];
          if (!nodes.containsKey(c)) {
            nodes.put(c, new Node());
          }
          nodes.get(c).addPosition(j, i);
          counter++;
        }
      }
    }
    for (var node : nodes.entrySet()) {
      int length = node.getValue().getLength();
      if (length != 1) {
        ArrayList<Integer> xPositions = node.getValue().getX();
        ArrayList<Integer> yPositions = node.getValue().getY();
        for (int i = 0; i < length; i++) {
          for (int j = 0; j < length; j++) {
            if (i == j) {
              j++;
            }
            if (j == length) {
              break;
            }
            int delta_x = xPositions.get(i) - xPositions.get(j);
            int delta_y = yPositions.get(i) - yPositions.get(j);
            int x = xPositions.get(i) + delta_x;
            int y = yPositions.get(i) + delta_y;
            while (x >= 0 && y >= 0 && y < map.length && x < map[0].length) {
              if (map[y][x] == '.') {
                map[y][x] = '#';
              }
              x += delta_x;
              y += delta_y;
            }
          }
        }
      }
    }
    System.out.println("____________________________________________");
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        if (map[i][j] == '#')
          counter++;
        System.out.print(map[i][j]);
      }
      System.out.println("");
    }
    return String.valueOf(counter);
  }

  private class Node {
    ArrayList<Integer> x;
    ArrayList<Integer> y;

    public Node() {
      this.x = new ArrayList<>();
      this.y = new ArrayList<>();
    }

    public ArrayList<Integer> getX() {
      return x;
    }

    public ArrayList<Integer> getY() {
      return y;
    }

    public void addPosition(int x, int y) {
      this.x.add(x);
      this.y.add(y);
    }

    public int getLength() {
      return x.size();
    }
  }
}
