package com.aoc.solutions_2015;

import com.aoc.lib.*;

import java.math.BigInteger;
import java.security.*;

public class S_04 extends Solution {

  public S_04(String input) {
    super(input);
  }

  @Override
  public String task_1() {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] messageDigest;
      BigInteger no;
      String hashtext;
      int index = 0;
      while (true) {
        messageDigest = md.digest((input + index).getBytes());
        no = new BigInteger(1, messageDigest);
        hashtext = no.toString(16);
        while (hashtext.length() < 32) {
          hashtext = "0" + hashtext;
          System.out.println(index);
        }
        if (hashtext.startsWith("00000")) {
          return Integer.toString(index);
        }
        index++;
      }
    } catch (Exception e) {
      System.out.println("Something went very, very, very wrong.");
      return "error";
    }
  }

  // Fruktansvärt långsamt och ineffektiv.
  @Override
  public String task_2() {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] messageDigest;
      BigInteger no;
      String hashtext;
      int index = 0;
      while (true) {
        messageDigest = md.digest((input + index).getBytes());
        no = new BigInteger(1, messageDigest);
        hashtext = no.toString(16);
        while (hashtext.length() < 32) {
          hashtext = "0" + hashtext;
          System.out.println(index);
        }
        if (hashtext.startsWith("000000")) {
          return Integer.toString(index);
        }
        index++;
      }
    } catch (Exception e) {
      System.out.println("Something went very, very, very wrong.");
      return "error";
    }
  }
}
