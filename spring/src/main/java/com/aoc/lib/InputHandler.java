package com.aoc.lib;

/**
 * InputHandler
 */
public class InputHandler {

  private String input;

  public InputHandler(String input) {
    this.input = input;
  }

  /**
   * @return Array of each line stripped of trailing spaces
   */
  public String[] getLines() {
    String[] returnString = input.split("\n");
    for (int i = 0; i < returnString.length; i++) {
      returnString[i] = returnString[i].strip();
    }
    return input.split("\n");
  }

  public Character[][] getMatrix() {
    String[] lines = getLines();
    Character[][] matrix = new Character[lines.length][];
    for (int i = 0; i < matrix.length; i++) {
      matrix[i] = new Character[lines[i].length()];
      for (int j = 0; j < lines[i].length(); j++) {
        matrix[i][j] = lines[i].charAt(j);
        System.out.print(matrix[i][j]);
      }
      System.out.println("");
    }

    return matrix;
  }

  /**
   * @return A string of the original input
   */
  public String raw() {
    return input;
  }
}
