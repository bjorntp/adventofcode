package com.aoc.solutions_2024;

import com.aoc.lib.*;

public class S_15 extends Solution {

  public S_15(String input) {
    super(input);
  }

  @Override
  public String task_1() {
    String map = input.split("\n\n")[0];
    String sequence = input.split("\n\n")[1];
    InputHandler ih = new InputHandler(map);
    Character[][] mapGrid = ih.getMatrix();
    int x = -1;
    int y = -1;
    for (int i = 0; i < mapGrid.length; i++) {
      for (int j = 0; j < mapGrid[i].length; j++) {
        if (mapGrid[i][j] == '@') {
          x = j;
          y = i;
          i = mapGrid.length;
          break;
        }
      }
    }
    ObjectMover ob = new ObjectMover(mapGrid, x, y);
    for (int i = 0; i < sequence.length(); i++) {
      ob.Move(sequence.charAt(i), x, y, true);
      x = ob.getRobotX();
      y = ob.getRobotY();
    }
    ob.printMap();
    int total = 0;
    for (int i = 0; i < mapGrid.length; i++) {
      for (int j = 0; j < mapGrid[i].length; j++) {
        if (mapGrid[i][j] == 'O') {
          total += i * 100 + j;
        }
      }
    }

    return "" + total;
  }

  @Override
  public String task_2() {
    return "";
  }

  /**
   * ObjectMover
   */
  public class ObjectMover {

    Character[][] map;
    int robotX, robotY;

    public ObjectMover(Character[][] map, int robotX, int robotY) {
      this.map = map;
      this.robotX = robotX;
      this.robotY = robotY;
    }

    public void Move(char direction, int x, int y, boolean robot) {
      if (direction == '<') {
        if (map[y][x - 1] == '#') {
          return;
        }
        if (map[y][x - 1] == 'O') {
          Move(direction, x - 1, y, false);
        }
        if (map[y][x - 1] == '.') {
          map[y][x - 1] = map[y][x];
          map[y][x] = '.';
          if (robot) {
            robotX--;
          }
        }

      } else if (direction == '>') {
        if (map[y][x + 1] == '#') {
          return;
        }
        if (map[y][x + 1] == 'O') {
          Move(direction, x + 1, y, false);
        }
        if (map[y][x + 1] == '.') {
          map[y][x + 1] = map[y][x];
          map[y][x] = '.';
          if (robot) {
            robotX++;
          }
        }

      } else if (direction == '^') {
        if (map[y - 1][x] == '#') {
          return;
        }
        if (map[y - 1][x] == 'O') {
          Move(direction, x, y - 1, false);
        }
        if (map[y - 1][x] == '.') {
          map[y - 1][x] = map[y][x];
          map[y][x] = '.';
          if (robot) {
            robotY--;
          }
        }

      } else if (direction == 'v') {
        if (map[y + 1][x] == '#') {
          return;
        }
        if (map[y + 1][x] == 'O') {
          Move(direction, x, y + 1, false);
        }
        if (map[y + 1][x] == '.') {
          map[y + 1][x] = map[y][x];
          map[y][x] = '.';
          if (robot) {
            robotY++;
          }
        }
      }
    }

    public Character[][] getMap() {
      return map;
    }

    public int getRobotX() {
      return robotX;
    }

    public int getRobotY() {
      return robotY;
    }

    public void printMap() {
      for (int i = 0; i < map.length; i++) {
        for (int j = 0; j < map[i].length; j++) {
          System.out.print(map[i][j]);
        }
        System.out.println("");
      }
      System.out.println("");
      System.out.println("-------");
      System.out.println("");
    }
  }
}
