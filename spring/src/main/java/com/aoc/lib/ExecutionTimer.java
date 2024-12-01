package com.aoc.lib;

import java.time.Duration;
import java.time.Instant;

/**
 * ExecutionTime
 */
public class ExecutionTimer {

  private Instant startInstance, endInstance;
  private Duration duration;

  /**
   * Set the starting point to measure from.
   *
   * Resets if there already is values.
   */

  public void start() {
    startInstance = Instant.now();
    if (endInstance != null) {
      endInstance = null;
    }
  }

  /**
   * Set the stopping point to measure to.
   *
   * @throws Error if no starting time is set.
   */

  public void stop() {
    if (startInstance != null) {
      endInstance = Instant.now();
      duration = Duration.between(startInstance, endInstance);
    } else {
      throw new Error("Can't set end value before start value");
    }
  }

  public String toString() {
    if (startInstance != null || endInstance != null) {
      return Long.toString(duration.toMillis());
    } else {
      throw new Error("No start or stop value");
    }
  }
}
