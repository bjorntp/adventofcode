package com.aoc.solutions_2024;

import java.util.ArrayList;

import com.aoc.lib.*;

public class S_06 extends Solution {

  private InputHandler IH;
  private ExecutionTimer ET;

  public S_06(String input) {
    super(input);
    IH = new InputHandler(input);
    ET = new ExecutionTimer();
  }

  @Override
  public String task_1() {
    ET.start();
    Character[][] map = IH.getMatrix();
    Character[][] visited = new Character[map.length][map[0].length];
    int nmbVisited = 1;
    Position p = new Position(map);
    visited[p.getY()][p.getX()] = 'X';
    while (true) {
      p.moveOneStep();
      if (p.getX() >= map[0].length && p.getY() >= map.length) {
        break;
      }
      if (visited[p.getY()][p.getX()] == null) {
        nmbVisited++;
      }
      visited[p.getY()][p.getX()] = 'X';
    }
    ET.stop();
    System.out.println("Execution time task 1: " + ET);
    return String.valueOf(nmbVisited);
  }

  @Override
  public String task_2() {
    ET.start();
    Character[][] map = IH.getMatrix();
    ArrayList<P> positions = new ArrayList<>();
    int repeats = 0;
    Position p = new Position(map);
    Character[][] visited = new Character[map.length][map[0].length];
    while (true) {
      p.moveOneStep();
      if (p.getX() >= map[0].length && p.getY() >= map.length) {
        break;
      }
      if (visited[p.getY()][p.getX()] == null) {
        positions.add(new P(p.getX(), p.getY()));
      }
      visited[p.getY()][p.getX()] = 'X';
    }
    for (int i = 0; i < positions.size(); i++) {
      Character[][] tempMap = IH.getMatrix();
      Position tempPosition = new Position(tempMap);
      tempPosition.addObstacle(positions.get(i).getX(), positions.get(i).getY());
      boolean x = true;
      int j = 0;
      while (x) {
        tempPosition.moveOneStep();
        if (tempPosition.getX() >= map[0].length && tempPosition.getY() >= map.length) {
          x = false;
          break;
        }
        if (j == 10000) {
          x = false;
          repeats++;
          break;
        }
        j++;
      }
    }

    ET.stop();
    System.out.println("Execution time task 2: " + ET);
    return String.valueOf(repeats);
  }

  private class P {
    private int x, y;

    public P(int x, int y) {
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

  private class Position {
    private int x;
    private int y;
    private String direction;
    private Character[][] mapInPosition;

    public Position(Character[][] map) {
      this.mapInPosition = map;
      findStart();
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

    public String getDirection() {
      if (direction == "UP") {
        return "U";
      }
      if (direction == "DOWN") {
        return "D";
      }
      if (direction == "LEFT") {
        return "L";
      }
      return "R";
    }

    public void addObstacle(int xx, int yy) {
      mapInPosition[yy][xx] = 'O';
    }

    private void findStart() {
      for (int i = 0; i < mapInPosition.length; i++) {
        for (int j = 0; j < mapInPosition[i].length; j++) {
          switch (mapInPosition[j][i]) {
            case '<':
              x = i;
              y = j;
              direction = "LEFT";
              return;
            case '^':
              x = i;
              y = j;
              direction = "UP";
              return;
            case '>':
              x = i;
              y = j;
              direction = "RIGHT";
              return;
            case 'v':
              x = i;
              y = j;
              direction = "DOWN";
              return;
            default:
              break;
          }
        }
      }
    }

    private void moveOneStep() {
      int nextX;
      int nextY;
      switch (direction) {
        case "LEFT":
          nextX = x - 1;
          nextY = y;
          break;
        case "UP":
          nextX = x;
          nextY = y - 1;
          break;
        case "RIGHT":
          nextX = x + 1;
          nextY = y;
          break;
        default:
          nextX = x;
          nextY = y + 1;
          break;
      }
      if (nextY >= mapInPosition.length || nextX >= mapInPosition[0].length || nextY < 0 || nextX < 0) {
        x = Integer.MAX_VALUE;
        y = Integer.MAX_VALUE;
        return;
      }
      if (mapInPosition[nextY][nextX] != '#' && mapInPosition[nextY][nextX] != 'O') {
        x = nextX;
        y = nextY;
      } else {
        switch (direction) {
          case "LEFT":
            direction = "UP";
            break;
          case "UP":
            direction = "RIGHT";
            break;
          case "RIGHT":
            direction = "DOWN";
            break;
          default:
            direction = "LEFT";
            break;
        }
        moveOneStep();
      }
    }
  }
}
