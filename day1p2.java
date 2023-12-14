import java.util.Scanner;

public class MyClass {
  public static void main(String args[]) {
    int sum = 0;
    String[] digits = {
      "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
    };
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      String str = sc.next();
      char firstChar = '$';
      int firstCharPos = Integer.MAX_VALUE;
      char lastChar = '$';
      int lastCharPos = Integer.MIN_VALUE;
      for (int i = 0; i < str.length(); i++) {
        char charAt = str.charAt(i);
        if (charAt >= '0' && charAt <= '9') {
          firstChar = charAt;
          firstCharPos = i;
          break;
        }
      }
      for (int i = str.length() - 1; i >= 0; i--) {
        char charAt = str.charAt(i);
        if (charAt >= '0' && charAt <= '9') {
          lastChar = charAt;
          lastCharPos = i;
          break;
        }
      }
      int firstDigit = 0;
      int firstNrStrPos = Integer.MAX_VALUE;
      int lastDigit = 0;
      int lastNrStrPos = Integer.MIN_VALUE;
      for (int i = 1; i <= 9; i++) {
        int index = str.indexOf(digits[i]);
        if (index < firstNrStrPos && index != -1) {
          firstNrStrPos = index;
          firstDigit = i;
        }
        index = str.lastIndexOf(digits[i]);
        if (index > lastNrStrPos && index != -1) {
          lastNrStrPos = index;
          lastDigit = i;
        }
      }
      String numStr = "";
      if (firstNrStrPos < firstCharPos) {
        numStr += firstDigit;
      } else {
        numStr += firstChar;
      }
      if (lastNrStrPos > lastCharPos) {
        numStr += lastDigit;
      } else {
        numStr += lastChar;
      }
      sum += Integer.parseInt(numStr);
    }
    System.out.println(sum);
    sc.close();
  }
}
