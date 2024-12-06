package com.aoc.solutions_2024;

import com.aoc.lib.*;

public class S_06 extends Solution {

  private ExecutionTimer ET = new ExecutionTimer();
  private InputHandler IH;

  public S_06(String input) {
    super(input);
    IH = new InputHandler(input);
  }

  @Override
  public String task_1() {
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
    return String.valueOf(nmbVisited);
  }

  @Override
  public String task_2() {
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
    return String.valueOf(nmbVisited);
  }

  private class Position {
    private int x;
    private int y;
    private String direction;
    private Character[][] map;

    public Position(Character[][] map) {
      this.map = map;
      findStart();
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

    private void findStart() {
      for (int i = 0; i < map.length; i++) {
        for (int j = 0; j < map[i].length; j++) {
          switch (map[j][i]) {
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
      if (nextY >= map.length || nextX >= map[0].length || nextY < 0 || nextX < 0) {
        x = Integer.MAX_VALUE;
        y = Integer.MAX_VALUE;
        return;
      }
      if (map[nextY][nextX] != '#') {
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
