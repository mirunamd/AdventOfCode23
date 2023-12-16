import java.util.*;
import java.lang.Math;

public class MyClass {
    public static void main(String args[]) {
        int points = 0;
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String inpStr = in.nextLine();
            int winningNr = 0;
            // Split Card from rest.
            String[] str = inpStr.split(":", 2);
            // Split remaining string.
            String[] nrStr = str[1].split("\\|");
            String winningStr = nrStr[0];
            String elfStr = nrStr[1];
            Set<String> winningSet = new HashSet<>();
            for (String nr: winningStr.split("\s")) {
                if(nr.length() > 0){
                    winningSet.add(nr);
                }
            }
            for (String nr: elfStr.split("\s")) {
                if (winningSet.contains(nr)) {
                    winningNr++;
                }
            }
            if (winningNr > 0) {
                points += Math.pow(2, winningNr - 1);
            }
        }
        System.out.println(points);
    }
}
