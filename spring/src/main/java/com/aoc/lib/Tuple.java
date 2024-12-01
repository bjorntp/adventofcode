package com.aoc.lib;

/**
 * Tuple
 */
public class Tuple<X, Y> {

  private X x;
  private Y y;

  /**
   * @param x Key
   * @param y Value
   */
  public Tuple(X x, Y y) {
    this.x = x;
    this.y = y;
  }

  /**
   * @return x value
   */
  public X getX() {
    return x;
  }

  /**
   * @return y value
   */
  public Y getY() {
    return y;
  }

  /**
   * Change the x value
   * 
   * @param x new value of x
   */
  public void setX(X x) {
    this.x = x;
  }

  /**
   * Change the Y value
   * 
   * @param y new value of y
   */
  public void setY(Y y) {
    this.y = y;
  }

  /**
   * Modified toString()
   * 
   * @return (x, y)
   */
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}
