package com.aoc.solutions_2024;

import java.util.List;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aoc.lib.*;

public class S_14 extends Solution {

  public S_14(String input) {
    super(input);
  }

  @Override
  public String task_1() {
    Pattern digit = Pattern.compile("-?\\d+");
    Matcher match = digit.matcher(input);
    List<Robot> robots = new LinkedList<>();
    while (match.find()) {
      int xStart = Integer.parseInt(match.group());
      match.find();
      int yStart = Integer.parseInt(match.group());
      match.find();
      int xVelocity = Integer.parseInt(match.group());
      match.find();
      int yVelocity = Integer.parseInt(match.group());
      robots.add(new Robot(xStart, yStart, xVelocity, yVelocity));
    }
    for (int i = 0; i < 100; i++) {
      robots.forEach(x -> x.move(101, 103));
    }
    int NW = 0;
    int SW = 0;
    int NE = 0;
    int SE = 0;
    for (Robot robot : robots) {
      switch (robot.quadrant(101, 103)) {
        case "WN":
          NW++;
          break;
        case "EN":
          NE++;
          break;
        case "WS":
          SW++;
          break;
        case "ES":
          SE++;
          break;
        default:
          break;
      }
    }
    return "Safety factor: " + (NW * SW * NE * SE);
  }

  @Override
  public String task_2() {
    Pattern digit = Pattern.compile("-?\\d+");
    Matcher match = digit.matcher(input);
    List<Robot> robots = new LinkedList<>();
    while (match.find()) {
      int xStart = Integer.parseInt(match.group());
      match.find();
      int yStart = Integer.parseInt(match.group());
      match.find();
      int xVelocity = Integer.parseInt(match.group());
      match.find();
      int yVelocity = Integer.parseInt(match.group());
      robots.add(new Robot(xStart, yStart, xVelocity, yVelocity));
    }
    Character[][] map = new Character[103][101];
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        map[i][j] = '.';
      }
    }
    boolean searching = true;
    StringBuilder sb;
    int counter = 0;
    while (searching) {
      counter++;
      for (Robot robot : robots) {
        robot.move(101, 103);
        map[robot.getyPos()][robot.getxPos()] = '#';
      }
      sb = new StringBuilder();
      for (int i = 0; i < map.length; i++) {
        for (int j = 0; j < map[i].length; j++) {
          sb.append(map[i][j]);
        }
      }
      if (sb.toString().contains("###############")) {
        searching = false;
      }
      for (int i = 0; i < map.length; i++) {
        for (int j = 0; j < map[i].length; j++) {
          map[i][j] = '.';
        }
      }
      System.out.print(".");
    }
    return "Iterations: " + counter;

  }

  private class Robot {
    private int xPos, yPos, xVelocity, yVelocity;

    public Robot(int xPos, int yPos, int xVelocity, int yVelocity) {
      this.xPos = xPos;
      this.yPos = yPos;
      this.xVelocity = xVelocity;
      this.yVelocity = yVelocity;
    }

    public int getxPos() {
      return xPos;
    }

    public int getyPos() {
      return yPos;
    }

    public void move(int sizeX, int sizeY) {
      xPos += xVelocity;
      yPos += yVelocity;
      if (xPos >= sizeX) {
        xPos -= sizeX;
      } else if (xPos < 0) {
        xPos += sizeX;
      }
      if (yPos >= sizeY) {
        yPos -= sizeY;
      } else if (yPos < 0) {
        yPos += sizeY;
      }
    }

    public String quadrant(int sizeX, int sizeY) {
      StringBuilder sb = new StringBuilder();
      if (xPos < (sizeX - 1) / 2) {
        sb.append('W');
      } else if (xPos > (sizeX - 1) / 2) {
        sb.append('E');
      } else {
        sb.append('M');
      }
      if (yPos < (sizeY - 1) / 2) {
        sb.append('N');
      } else if (yPos > (sizeY - 1) / 2) {
        sb.append('S');
      } else {
        sb.append('M');
      }
      return sb.toString();
    }
  }
}
