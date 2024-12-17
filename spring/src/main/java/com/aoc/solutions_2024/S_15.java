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
    ObjectMoverTask1 ob = new ObjectMoverTask1(mapGrid, x, y);
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
    String map = input.split("\n\n")[0];
    String sequence = input.split("\n\n")[1];
    InputHandler ih = new InputHandler(map);
    Character[][] firstMapGrid = ih.getMatrix();
    Character[][] finalGrid = new Character[firstMapGrid.length][firstMapGrid[0].length * 2];
    int x = -1;
    int y = -1;
    for (int i = 0; i < firstMapGrid.length; i++) {
      for (int j = 0; j < firstMapGrid[i].length; j++) {
        switch (firstMapGrid[i][j]) {
          case '.':
            finalGrid[i][2 * j] = '.';
            finalGrid[i][2 * j + 1] = '.';
            break;
          case '#':
            finalGrid[i][2 * j] = '#';
            finalGrid[i][2 * j + 1] = '#';
            break;
          case 'O':
            finalGrid[i][2 * j] = '[';
            finalGrid[i][2 * j + 1] = ']';
            break;
          default:
            finalGrid[i][2 * j] = '@';
            finalGrid[i][2 * j + 1] = '.';
            break;
        }
      }
    }
    System.out.println("--------");
    for (int i = 0; i < finalGrid.length; i++) {
      for (int j = 0; j < finalGrid[i].length; j++) {
        System.out.print(finalGrid[i][j]);
        if (finalGrid[i][j] == '@') {
          x = j;
          y = i;
        }
      }
      System.out.println("");
    }
    ObjectMoverTask2 ob = new ObjectMoverTask2(finalGrid, x, y);
    for (int i = 0; i < sequence.length(); i++) {
      ob.Move(sequence.charAt(i), x, y, true);
      x = ob.getRobotX();
      y = ob.getRobotY();
      System.out.println("Direction: " + sequence.charAt(i));
      ob.printMap();
    }
    int total = 0;
    // for (int i = 0; i < mapGrid.length; i++) {
    // for (int j = 0; j < mapGrid[i].length; j++) {
    // if (mapGrid[i][j] == 'O') {
    // total += i * 100 + j;
    // }
    // }
    // }
    return "";
  }

  public class ObjectMoverTask2 {
    Character[][] map;
    int robotX, robotY;

    public ObjectMoverTask2(Character[][] map, int robotX, int robotY) {
      this.map = map;
      this.robotX = robotX;
      this.robotY = robotY;
    }

    public boolean Move(char direction, int x, int y, boolean robot) {
      boolean moved = true;
      if (direction == '<') {
        if (map[y][x - 1] == '#') {
          return false;
        }
        if (map[y][x - 1] == '[' || map[y][x - 1] == ']') {
          moved = Move(direction, x - 1, y, false);
        }
        if (map[y][x - 1] == '.') {
          map[y][x - 1] = map[y][x];
          map[y][x] = '.';
          if (robot && moved) {
            robotX--;
          }
        }

      } else if (direction == '>') {
        if (map[y][x + 1] == '#') {
          return false;
        }
        if (map[y][x + 1] == '[' || map[y][x + 1] == ']') {
          moved = Move(direction, x + 1, y, false);
        }
        if (map[y][x + 1] == '.') {
          map[y][x + 1] = map[y][x];
          map[y][x] = '.';
          if (robot && moved) {
            robotX++;
          }
        }

      } else if (direction == '^') {
        if (map[y - 1][x] == '#') {
          return false;
        }
        if (map[y - 1][x] == '[') {
          moved = Move(direction, x, y - 1, false);
          if (moved) {
            moved = Move(direction, x + 1, y, false);
          }
        } else if (map[y - 1][x] == ']') {
          moved = Move(direction, x, y - 1, false);
          if (moved) {
            Move(direction, x - 1, y, false);
          }
        }
        if (map[y - 1][x] == '.') {
          map[y - 1][x] = map[y][x];
          map[y][x] = '.';
          if (robot && moved) {
            robotY--;
          }
        }

      } else if (direction == 'v') {
        if (map[y + 1][x] == '#') {
          return false;
        }
        if (map[y + 1][x] == '[') {
          moved = Move(direction, x, y + 1, false);
          if (moved) {
            Move(direction, x + 1, y, false);
          }
        } else if (map[y + 1][x] == ']') {
          moved = Move(direction, x, y + 1, false);
          if (moved) {
            Move(direction, x - 1, y, false);
          }
        }
        if (map[y + 1][x] == '.') {
          map[y + 1][x] = map[y][x];
          map[y][x] = '.';
          if (robot && moved) {
            robotY++;
          }
        }
      }
      return moved;
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

  /**
   * ObjectMover
   */
  public class ObjectMoverTask1 {

    Character[][] map;
    int robotX, robotY;

    public ObjectMoverTask1(Character[][] map, int robotX, int robotY) {
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
