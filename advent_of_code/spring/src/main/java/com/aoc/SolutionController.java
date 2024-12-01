package com.aoc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aoc.lib.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SolutionController {

  public SolutionController() {
  }

  @PostMapping("/solutions/{year}/{day}")
  public List<String> solve(@PathVariable int year, @PathVariable int day, @RequestBody Map<String, String> json) {
    String message = json.get("message");
    Solution solution;

    try {
      String className = "com.aoc.solutions_" + year + ".S_" + String.format("%02d", day);
      Class<?> clazz = Class.forName(className);

      solution = (Solution) clazz.getDeclaredConstructor(String.class).newInstance(message);

    } catch (Exception e) {

      System.out.println(e);
      return Arrays.asList("This day is not implemented yet", "This day is not implemented yet");
    }
    return Arrays.asList(solution.task_1(), solution.task_2());
  }
}
