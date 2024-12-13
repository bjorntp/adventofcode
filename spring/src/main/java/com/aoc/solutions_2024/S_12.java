package com.aoc.solutions_2024;

import com.aoc.lib.*;

public class S_12 extends Solution {

  private InputHandler IH;
  private int area, perimeter;
  private Character[][] map;
  private int corners;

  public S_12(String input) {
    super(input);
    IH = new InputHandler(input);
  }

  @Override
  public String task_1() {
    map = IH.getMatrix();
    int total = 0;
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] != '1') {
          area = 0;
          perimeter = 0;
          findAdjacent(map[i][j], j, i);
          total += area * perimeter;
          for (int k = 0; k < map.length; k++) {
            for (int l = 0; l < map[k].length; l++) {
              if (map[k][l] == '0') {
                map[k][l] = '1';
              }
            }
          }
        }
      }
    }
    return String.valueOf(total);
  }

  @Override
  public String task_2() {
    map = IH.getMatrix();
    int total = 0;
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] != '1') {
          area = 0;
          corners = 0;
          findAdjacentSecond(map[i][j], j, i);
          total += area * corners;
          for (int k = 0; k < map.length; k++) {
            for (int l = 0; l < map[k].length; l++) {
              if (map[k][l] == '0') {
                map[k][l] = '1';
              }
            }
          }
        }
      }
    }
    return String.valueOf(total);
  }

  private void findAdjacent(char letter, int x, int y) {
    area++;
    map[y][x] = '0';
    boolean left = x != 0 && map[y][x - 1] == letter;
    if (left) {
      findAdjacent(letter, x - 1, y);
    } else if (x == 0) {
      perimeter++;
    } else if (map[y][x - 1] != '0') {
      perimeter++;
    }
    boolean up = y != 0 && map[y - 1][x] == letter;
    if (up) {
      findAdjacent(letter, x, y - 1);
    } else if (y == 0) {
      perimeter++;
    } else if (map[y - 1][x] != '0') {
      perimeter++;
    }
    boolean right = x != map[y].length - 1 && map[y][x + 1] == letter;
    if (right) {
      findAdjacent(letter, x + 1, y);
    } else if (x == map[y].length - 1) {
      perimeter++;
    } else if (map[y][x + 1] != '0') {
      perimeter++;
    }
    boolean down = y != map.length - 1 && map[y + 1][x] == letter;
    if (down) {
      findAdjacent(letter, x, y + 1);
    } else if (y == map.length - 1) {
      perimeter++;
    } else if (map[y + 1][x] != '0') {
      perimeter++;
    }
  }

  private void findAdjacentSecond(char letter, int x, int y) {
    area++;
    map[y][x] = '0';
    // True if same character
    boolean left = x != 0 && (map[y][x - 1] == letter || map[y][x - 1] == '0');
    boolean up = y != 0 && (map[y - 1][x] == letter || map[y - 1][x] == '0');
    boolean right = x != map[y].length - 1 && (map[y][x + 1] == letter || map[y][x + 1] == '0');
    boolean down = y != map.length - 1 && (map[y + 1][x] == letter || map[y + 1][x] == '0');

    // True if empty
    boolean nw = x == 0 || y == 0 || (map[y - 1][x - 1] != letter && map[y - 1][x - 1] != '0');
    boolean sw = x == 0 || y == map.length - 1 || (map[y + 1][x - 1] != letter && map[y + 1][x - 1] != '0');
    boolean se = x == map[y].length - 1 || y == map.length - 1
        || (map[y + 1][x + 1] != letter && map[y + 1][x + 1] != '0');
    boolean ne = x == map[y].length - 1 || y == 0 || (map[y - 1][x + 1] != letter && map[y - 1][x + 1] != '0');

    if (!left && !up) {
      corners++;
    } else if ((left && up) && nw) {
      corners++;
    }
    if (!left && !down) {
      corners++;
    } else if ((left && down) && sw) {
      corners++;
    }
    if (!right && !up) {
      corners++;
    } else if ((right && up) && ne) {
      corners++;
    }
    if (!right && !down) {
      corners++;
    } else if ((right && down) && se) {
      corners++;
    }
    left = x != 0 && map[y][x - 1] == letter;
    if (left) {
      findAdjacentSecond(letter, x - 1, y);
    } else if (x == 0) {
      perimeter++;
    } else if (map[y][x - 1] != '0') {
      perimeter++;
    }
    up = y != 0 && map[y - 1][x] == letter;
    if (up) {
      findAdjacentSecond(letter, x, y - 1);
    } else if (y == 0) {
      perimeter++;
    } else if (map[y - 1][x] != '0') {
      perimeter++;
    }
    right = x != map[y].length - 1 && map[y][x + 1] == letter;
    if (right) {
      findAdjacentSecond(letter, x + 1, y);
    } else if (x == map[y].length - 1) {
      perimeter++;
    } else if (map[y][x + 1] != '0') {
      perimeter++;
    }
    down = y != map.length - 1 && map[y + 1][x] == letter;
    if (down) {
      findAdjacentSecond(letter, x, y + 1);
    } else if (y == map.length - 1) {
      perimeter++;
    } else if (map[y + 1][x] != '0') {
      perimeter++;
    }
  }
}
