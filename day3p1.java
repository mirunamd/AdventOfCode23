package aoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** my class */
public class Aoc {
  private static int findPartNumber(String currStr, String prevStr, String nextStr) {
    int nr = 0;
    int nrIndex = -1;
    boolean symbol = false;
    int localSum = 0;
    for (int j = 0; j < currStr.length(); j++) {
      boolean isDigit = currStr.charAt(j) >= '0' && currStr.charAt(j) <= '9';
      if (isDigit) {
        if (nrIndex == -1) {
          nrIndex = j;
        }
        nr = nr * 10 + (currStr.charAt(j) - '0');
        symbol = symbol ? true : isSymbol(prevStr, j);
        symbol = symbol ? true : isSymbol(nextStr, j);
      }
      if (!isDigit || j == currStr.length() - 1) {
        if (nr != 0 && !symbol) {
          symbol = isSymbol(prevStr, j);
          symbol = symbol ? true : isSymbol(nextStr, j);
          symbol = symbol ? true : isSymbol(currStr, j);
          if (nrIndex > 0) {
            symbol = symbol ? true : isSymbol(prevStr, nrIndex - 1);
            symbol = symbol ? true : isSymbol(nextStr, nrIndex - 1);
            symbol = symbol ? true : isSymbol(currStr, nrIndex - 1);
          }
        }
        if (symbol) {
          localSum += nr;
        }
        nr = 0;
        nrIndex = -1;
        symbol = false;
      }
    }
    return localSum;
  }

  private static boolean isSymbol(String str, int j) {
    return !str.isEmpty() && str.charAt(j) != '.' && (str.charAt(j) < '0' || str.charAt(j) > '9');
  }

  public static void main(String[] args) throws FileNotFoundException {
    Scanner in = new Scanner(new File("input.txt"));
    // input rows -- at least two.
    String prevStr = "";
    String nextStr = "";
    String currStr = in.nextLine();
    int sum = 0;
    while (in.hasNext()) {
      nextStr = in.nextLine();
      sum += findPartNumber(currStr, prevStr, nextStr);
      prevStr = currStr;
      currStr = nextStr;
    }
    if (!prevStr.isEmpty() && !currStr.isEmpty()) {
      sum += findPartNumber(currStr, prevStr, "");
    }
    System.out.println("SUM: " + sum);
    in.close();
  }
}
