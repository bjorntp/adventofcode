package com.aoc.solutions_2024;

import java.util.ArrayList;
import com.aoc.lib.*;

public class S_10 extends Solution {

  private InputHandler IH;

  public S_10(String input) {
    super(input);
    IH = new InputHandler(input);
  }

  @Override
  public String task_1() {
    int acc = trailhead(true);
    return String.valueOf(acc);
  }

  @Override
  public String task_2() {
    int acc = trailhead(false);
    return String.valueOf(acc);
  }

  private int trailhead(boolean uniqueTrails) {
    Character[][] charMap = IH.getMatrix();
    int[][] intMap = new int[charMap.length][charMap[0].length];
    ArrayList<Position> startingPositions = new ArrayList<>();
    for (int i = 0; i < charMap.length; i++) {
      for (int j = 0; j < charMap[i].length; j++) {
        intMap[i][j] = (charMap[i][j] - '0');
        if (intMap[i][j] == 0) {
          startingPositions.add(new Position(j, i));
        }
      }
    }
    int acc = 0;
    for (Position position : startingPositions) {
      int[][] copy = new int[intMap.length][];
      for (int i = 0; i < intMap.length; i++) {
        copy[i] = intMap[i].clone();
      }
      int trails = trailheadFinder(position.getX(), position.getY(), 0, copy, uniqueTrails);
      acc += trails;
    }
    return acc;
  }

  private int trailheadFinder(int x, int y, int height, int[][] map, boolean uniqueTrails) {
    if (height == 9) {
      if (uniqueTrails) {
        map[y][x] = -1;
      }
      return 1;
    }
    int counter = 0;
    if (x != 0 && map[y][x - 1] == height + 1) {
      counter += trailheadFinder(x - 1, y, height + 1, map, uniqueTrails);
    }

    if (y != 0 && map[y - 1][x] == height + 1) {
      counter += trailheadFinder(x, y - 1, height + 1, map, uniqueTrails);
    }

    if (x != map[y].length - 1 && map[y][x + 1] == height + 1) {
      counter += trailheadFinder(x + 1, y, height + 1, map, uniqueTrails);
    }

    if (y != map.length - 1 && map[y + 1][x] == height + 1) {
      counter += trailheadFinder(x, y + 1, height + 1, map, uniqueTrails);
    }
    return counter;
  }

  /**
   * Position
   */
  public class Position {
    int x, y;

    public Position(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }
  }
}
