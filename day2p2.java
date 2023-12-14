import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyClass {
  public static void main(String args[]) {
    int sum = 0;
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      String inpStr = in.nextLine();
      int maxRed = 0;
      int maxGreen = 0;
      int maxBlue = 0;
      // Split Game from rest.
      String[] str = inpStr.split(":", 2);
      String gameStr = str[0];
      int gameNumber = Integer.parseInt(gameStr.replaceAll("[^0-9]", ""));
      // Split remaining string.
      String drawStr = str[1];
      for (String cubeStr : drawStr.split(";")) {
        for (String pairStr : cubeStr.split(",")) {
          int number = Integer.parseInt(pairStr.split(" ")[1]);
          String color = pairStr.split(" ")[2];
          if (color.contains("red")) {
            maxRed = Math.max(number, maxRed);
          }
          if (color.contains("blue")) {
            maxBlue = Math.max(number, maxBlue);
          }
          if (color.contains("green")) {
            maxGreen = Math.max(number, maxGreen);
          }
        }
      }
      sum += (maxRed * maxBlue * maxGreen);
    }
    System.out.println(sum);
  }
}
