package com.aoc.solutions_2015;

import com.aoc.lib.*;

import java.util.HashSet;

public class S_03 extends Solution {

  public S_03(String input) {
    super(input);
  }

  @Override
  public String task_1() {
    int pos_x = 0, pos_y = 0;
    HashSet<String> positions = new HashSet<>();
    positions.add(pos_x + "," + pos_y);
    for (int i = 0; i < input.length(); i++) {
      switch (input.charAt(i)) {
        case '^':
          pos_y++;
          break;
        case 'v':
          pos_y--;
          break;
        case '>':
          pos_x++;
          break;
        case '<':
          pos_x--;
        default:
          break;
      }
      positions.add(pos_x + "," + pos_y);
    }

    return Integer.toString(positions.size());
  }

  @Override
  public String task_2() {
    HashSet<String> positions = new HashSet<>();
    positions.add(0 + "," + 0);

    int pos_x = 0, pos_y = 0;
    for (int i = 0; i < input.length(); i = i + 2) {
      switch (input.charAt(i)) {
        case '^':
          pos_y++;
          break;
        case 'v':
          pos_y--;
          break;
        case '>':
          pos_x++;
          break;
        case '<':
          pos_x--;
        default:
          break;
      }
      positions.add(pos_x + "," + pos_y);
    }

    int pos_xx = 0, pos_yy = 0;
    for (int i = 1; i < input.length(); i = i + 2) {
      switch (input.charAt(i)) {
        case '^':
          pos_yy++;
          break;
        case 'v':
          pos_yy--;
          break;
        case '>':
          pos_xx++;
          break;
        case '<':
          pos_xx--;
        default:
          break;
      }
      positions.add(pos_xx + "," + pos_yy);
    }
    return Integer.toString(positions.size());
  }

}
