package com.aoc.solutions_2023;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import com.aoc.lib.*;

public class S_05 extends Solution {

  public S_05(String input) {
    super(input);
  }

  public String task_1() {
    Instant starts = Instant.now();
    String[] sections = input.split("\\n\\n");
    sections[0] = sections[0].split(": ")[1];
    for (int i = 1; i < sections.length; i++) {
      sections[i] = sections[i].split(":\\n")[1];
    }

    String[] seeds = sections[0].split(" ");

    MappingTask1[] maps = new MappingTask1[7];
    for (int i = 0; i < maps.length; i++) {
      maps[i] = new MappingTask1(sections[i + 1]);
    }

    double min = Double.MAX_VALUE;

    for (String seed : seeds) {
      double currentPosition = Double.parseDouble(seed);
      for (MappingTask1 mapping : maps) {
        currentPosition = mapping.newPos(currentPosition);
      }
      if (min > currentPosition) {
        min = currentPosition;
      }
    }

    Instant ends = Instant.now();
    System.out.println("Execution time for task 1: " + Duration.between(starts, ends));

    DecimalFormat df = new DecimalFormat("#.##");
    return String.valueOf(df.format(min));
  }

  private class MappingTask1 {

    RangeTask1[] r;

    public MappingTask1(String values) {
      String[] lines = values.split("\\n");
      r = new RangeTask1[lines.length];
      int index = 0;
      for (String line : lines) {
        String[] val = line.split(" ");
        r[index] = new RangeTask1(Double.parseDouble(val[0]), Double.parseDouble(val[1]), Double.parseDouble(val[2]));
        index++;
      }
    }

    public double newPos(double n) {
      double temp = -1;
      for (RangeTask1 range : r) {
        temp = range.within(n);
        if (temp != -1) {
          return temp;
        }
      }
      return n;
    }
  }

  private class RangeTask1 {

    private double outStart, inStart, inEnd;

    public RangeTask1(double outStart, double inStart, double m) {
      this.outStart = outStart;
      this.inStart = inStart;
      this.inEnd = inStart + m;
    }

    public double within(double n) {
      if (inStart <= n && n < inEnd) {
        return n - (inStart - outStart);
      } else {
        return -1;
      }
    }
  }

  public String task_2() {

    // Input formation section[0] is seeds rest are mappings
    String[] sections = input.split("\\n\\n");
    sections[0] = sections[0].split(": ")[1];
    for (int i = 1; i < sections.length; i++) {
      sections[i] = sections[i].split(":\\n")[1];
    }
    Instant starts = Instant.now();

    String[] seeds = sections[0].split(" ");

    ArrayList<Long> seedRanges = new ArrayList<>();

    for (int i = 0; i < seeds.length; i += 2) {
      seedRanges.add(Long.parseLong(seeds[i]));
      seedRanges.add(seedRanges.getLast() + Long.parseLong(seeds[i + 1]) - 1);
    }

    MappingT2[] maps = new MappingT2[7];
    for (int i = 0; i < maps.length; i++) {
      maps[i] = new MappingT2(sections[i + 1]);
    }
    Long min = Long.MAX_VALUE;

    for (int i = 0; i < seedRanges.size(); i += 2) {
      ArrayList<Long> seedToSoil = maps[0].getOutRanges(seedRanges.get(i), seedRanges.get(i + 1));

      ArrayList<Long> SoilToFertilizer = new ArrayList<>();
      for (int j = 0; j < seedToSoil.size(); j += 2) {
        SoilToFertilizer.addAll(maps[1].getOutRanges(seedToSoil.get(j), seedToSoil.get(j + 1)));
      }

      ArrayList<Long> FertilizerToWater = new ArrayList<>();
      for (int j = 0; j < SoilToFertilizer.size(); j += 2) {
        FertilizerToWater.addAll(maps[2].getOutRanges(SoilToFertilizer.get(j), SoilToFertilizer.get(j + 1)));
      }

      ArrayList<Long> WaterToLight = new ArrayList<>();
      for (int j = 0; j < FertilizerToWater.size(); j += 2) {
        WaterToLight.addAll(maps[3].getOutRanges(FertilizerToWater.get(j), FertilizerToWater.get(j + 1)));
      }

      ArrayList<Long> LightToTemparature = new ArrayList<>();
      for (int j = 0; j < WaterToLight.size(); j += 2) {
        LightToTemparature.addAll(maps[4].getOutRanges(WaterToLight.get(j), WaterToLight.get(j + 1)));
      }

      ArrayList<Long> TemperatureToHumidity = new ArrayList<>();
      for (int j = 0; j < LightToTemparature.size(); j += 2) {
        TemperatureToHumidity.addAll(maps[5].getOutRanges(LightToTemparature.get(j), LightToTemparature.get(j + 1)));
      }

      ArrayList<Long> HumidityToLocation = new ArrayList<>();
      for (int j = 0; j < TemperatureToHumidity.size(); j += 2) {
        HumidityToLocation.addAll(maps[6].getOutRanges(TemperatureToHumidity.get(j), TemperatureToHumidity.get(j + 1)));
      }

      Collections.sort(HumidityToLocation);
      if (min > HumidityToLocation.getFirst()) {
        min = HumidityToLocation.getFirst();
      }
    }

    Instant ends = Instant.now();
    System.out.println("Execution time for task 2: " + Duration.between(starts, ends));
    DecimalFormat df = new DecimalFormat("#.##");
    return String.valueOf(df.format(min));

  }

  private class MappingT2 {

    private ArrayList<MapRangeT2> mappingRanges;

    public MappingT2(String values) {
      mappingRanges = new ArrayList<>();
      String[] lines = values.split("\\n");
      for (String line : lines) {
        String[] val = line.split(" ");
        mappingRanges.add(new MapRangeT2(Long.parseLong(val[0]), Long.parseLong(val[1]), Long.parseLong(val[2])));
      }
      mappingRanges.sort((a, b) -> Long.compare(a.getStartInRange(), b.getStartInRange()));
    }

    /**
     * @param1 Starting point of range
     * @param2 End point of range
     *
     * @return An arraylist with an event amount of elements where each pair
     *         represent a range the would be filled by the input.
     */
    public ArrayList<Long> getOutRanges(Long start, Long end) {

      ArrayList<Long> ranges = new ArrayList<>();
      if (start < mappingRanges.getFirst().getStartInRange()) {
        ranges.add(start);
        if (end < mappingRanges.getFirst().getStartInRange()) {
          ranges.add(end);
          return ranges;
        }
        ranges.add(mappingRanges.getFirst().getStartInRange() - 1);
        start = mappingRanges.getFirst().getStartInRange();
      }

      for (int i = 0; i < mappingRanges.size(); i++) {
        if (start < mappingRanges.get(i).getStartInRange()) {
          ranges.add(start);
          if (end < mappingRanges.get(i).getStartInRange()) {
            ranges.add(end);
            return ranges;
          }
          ranges.add(mappingRanges.get(i).getStartInRange() - 1);
          start = mappingRanges.get(i).getStartInRange();
        }
        if (start >= mappingRanges.get(i).getStartInRange() && start < mappingRanges.get(i).getEndInRange()) {
          ranges.add(mappingRanges.get(i).getNewPosition(start));
          if (end < mappingRanges.get(i).getEndInRange()) {
            ranges.add(mappingRanges.get(i).getNewPosition(end));
            return ranges;
          } else {
            ranges.add(mappingRanges.get(i).getNewPosition(mappingRanges.get(i).getEndInRange() - 1));
            start = mappingRanges.get(i).getEndInRange();
          }
        }

      }
      if (end > mappingRanges.getLast().getEndInRange()) {
        ranges.add(start);
        ranges.add(end);
      }

      return ranges;
    }
  }

  private class MapRangeT2 {

    private Long startInRange, range, startOutRange;

    public MapRangeT2(Long startOutRange, Long startInRange, Long range) {
      this.startInRange = startInRange;
      this.range = range;
      this.startOutRange = startOutRange;
    }

    /**
     * @return The new position of a element after
     */

    public Long getNewPosition(Long p) {
      return p - (startInRange - startOutRange);
    }

    /**
     * @return The index of the start of the Range/
     */
    public Long getStartInRange() {
      return startInRange;
    }

    /**
     * @return Last index of the in-range
     */

    public Long getEndInRange() {
      return startInRange + range;
    }
  }
}
