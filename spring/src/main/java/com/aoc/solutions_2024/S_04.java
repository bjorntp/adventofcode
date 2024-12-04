package com.aoc.solutions_2024;

import com.aoc.lib.*;

public class S_04 extends Solution {

  private InputHandler IH;
  private ExecutionTimer ET = new ExecutionTimer();

  public S_04(String input) {
    super(input);
    IH = new InputHandler(input);
  }

  @Override
  public String task_1() {
    ET.start();
    String[] lines = IH.getLines();
    char[][] grid = new char[lines.length][lines[0].length()];
    StringBuilder sb = new StringBuilder();
    for (int j = 0; j < lines.length; j++) {
      for (int i = 0; i < lines[0].length(); i++) {
        grid[j][i] = lines[j].charAt(i);
      }
    }
    int wordcounter = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 'X') {
          if (findWord(1, "XMAS", grid, i, j, 1, 0))
            wordcounter++;
          if (findWord(1, "XMAS", grid, i, j, -1, 0))
            wordcounter++;
          if (findWord(1, "XMAS", grid, i, j, 0, 1))
            wordcounter++;
          if (findWord(1, "XMAS", grid, i, j, 0, -1))
            wordcounter++;
          if (findWord(1, "XMAS", grid, i, j, -1, -1))
            wordcounter++;
          if (findWord(1, "XMAS", grid, i, j, -1, 1))
            wordcounter++;
          if (findWord(1, "XMAS", grid, i, j, 1, -1))
            wordcounter++;
          if (findWord(1, "XMAS", grid, i, j, 1, 1))
            wordcounter++;
        }
      }
    }
    ET.stop();
    System.out.println(ET);

    return Integer.toString(wordcounter);
  }

  private boolean findWord(int index, String word, char[][] grid, int x, int y, int dirX, int dirY) {
    if (index == word.length())
      return true;
    if (x + dirX == -1 || x + dirX == grid[0].length || y + dirY == -1 || y + dirY == grid.length)
      return false;
    if (grid[x + dirX][y + dirY] == word.charAt(index)) {
      return findWord(index + 1, word, grid, x + dirX, y + dirY, dirX, dirY);
    }
    return false;
  }

  @Override
  public String task_2() {
    ET.start();
    String[] lines = IH.getLines();
    char[][] grid = new char[lines.length][lines[0].length()];
    StringBuilder sb = new StringBuilder();
    for (int j = 0; j < lines.length; j++) {
      for (int i = 0; i < lines[0].length(); i++) {
        grid[j][i] = lines[j].charAt(i);
      }
    }
    int wordcounter = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 'A') {
          if (findX(i, j, grid))
            wordcounter++;
        }
      }
    }

    ET.stop();
    System.out.println(ET);

    return Integer.toString(wordcounter);
  }

  private boolean findX(int x, int y, char[][] grid) {
    if (x == grid.length - 1 || x == 0 || y == grid[0].length - 1 || y == 0)
      return false;

    if (grid[x - 1][y - 1] == 'M' &&
        grid[x - 1][y + 1] == 'S' &&
        grid[x + 1][y - 1] == 'M' &&
        grid[x + 1][y + 1] == 'S')
      return true;
    if (grid[x - 1][y - 1] == 'S' &&
        grid[x - 1][y + 1] == 'M' &&
        grid[x + 1][y - 1] == 'S' &&
        grid[x + 1][y + 1] == 'M')
      return true;
    if (grid[x - 1][y - 1] == 'M' &&
        grid[x - 1][y + 1] == 'M' &&
        grid[x + 1][y - 1] == 'S' &&
        grid[x + 1][y + 1] == 'S')
      return true;
    if (grid[x - 1][y - 1] == 'S' &&
        grid[x - 1][y + 1] == 'S' &&
        grid[x + 1][y - 1] == 'M' &&
        grid[x + 1][y + 1] == 'M')
      return true;
    return false;
  }
}
