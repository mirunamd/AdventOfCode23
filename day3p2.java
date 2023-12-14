package aoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/** my class */
public class Aoc {
  // <gear index, adj numbers>
  private static HashMap<String, ArrayList<Integer>> gears = new HashMap<>();

  private static void findPartNumber(String currStr, String prevStr, String nextStr, int rowNr) {
    int nr = 0;
    int nrIndex = -1;
    boolean symbol = false;
    int localProd = 1;
    Set<String> adjGearsIndexes = new HashSet<>();
    for (int j = 0; j < currStr.length(); j++) {
      boolean isDigit = currStr.charAt(j) >= '0' && currStr.charAt(j) <= '9';
      if (isDigit) {
        if (nrIndex == -1) {
          nrIndex = j;
        }
        nr = nr * 10 + (currStr.charAt(j) - '0');
        if (isSymbol(prevStr, j)) {
          adjGearsIndexes.add((rowNr - 1) + "," + j);
        }
        if (isSymbol(nextStr, j)) {
          adjGearsIndexes.add((rowNr + 1) + "," + j);
        }
      }
      if (!isDigit || j == currStr.length() - 1) {
        if (nr != 0) {
          if (isSymbol(prevStr, j)) {
            adjGearsIndexes.add((rowNr - 1) + "," + j);
          }
          if (isSymbol(nextStr, j)) {
            adjGearsIndexes.add((rowNr + 1) + "," + j);
          }
          if (isSymbol(currStr, j)) {
            adjGearsIndexes.add(rowNr + "," + j);
          }
          if (nrIndex > 0) {
            if (isSymbol(prevStr, nrIndex - 1)) {
              adjGearsIndexes.add((rowNr - 1) + "," + (nrIndex - 1));
            }
            if (isSymbol(nextStr, nrIndex - 1)) {
              adjGearsIndexes.add((rowNr + 1) + "," + (nrIndex - 1));
            }
            if (isSymbol(currStr, nrIndex - 1)) {
              adjGearsIndexes.add(rowNr + "," + (nrIndex - 1));
            }
          }
        }
        if (!adjGearsIndexes.isEmpty()) {
          for (String gearIndexes : adjGearsIndexes) {
            if (!gears.containsKey(gearIndexes)) {
              gears.put(gearIndexes, new ArrayList<>());
            }
            ArrayList<Integer> adjNumbers = gears.get(gearIndexes);
            adjNumbers.add(nr);
          }
          adjGearsIndexes.clear();
        }
        nr = 0;
        nrIndex = -1;
      }
    }
  }

  private static boolean isSymbol(String str, int j) {
    return !str.isEmpty() && str.charAt(j) != '.' && (str.charAt(j) < '0' || str.charAt(j) > '9');
  }

  public static void main(String[] args) throws FileNotFoundException {
    Scanner in = new Scanner(new File(DirPath.DIR_PATH + "input.txt"));
    // input rows -- at least two.
    String prevStr = "";
    String nextStr = "";
    String currStr = in.nextLine();
    int prod = 1;
    int rowNr = 0;
    int result = 0;
    while (in.hasNext()) {
      nextStr = in.nextLine();
      findPartNumber(currStr, prevStr, nextStr, rowNr);
      prevStr = currStr;
      currStr = nextStr;
      rowNr++;
    }
    if (!prevStr.isEmpty() && !currStr.isEmpty()) {
      findPartNumber(currStr, prevStr, "", rowNr);
    }
    for (String gearIndexes : gears.keySet()) {
      ArrayList<Integer> adjNumbers = gears.get(gearIndexes);
      if (adjNumbers.size() == 2) {
        prod = adjNumbers.get(0) * adjNumbers.get(1);
        result += prod;
        prod = 1;
      }
    }
    System.out.println("Result: " + result);
    in.close();
  }
}
