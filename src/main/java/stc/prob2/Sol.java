package stc.prob2;

import java.util.Scanner;

/**
 * @author : hoangtq
 * @since : 08:55 15/09/2020, Tue
 **/
public class Sol {

    public static void main(String[] args) {
        Sol solution = new Sol();
        solution.run();
    }

    public void run() {

        Scanner input = new Scanner(System.in);
        for (int testCase = 1; testCase <= 10; testCase++) {
            String line = input.nextLine();
            String[] values = line.trim().split("\\s++");
            System.out.printf("#%d %s\n", testCase, solved(values[1]));

        }
    }

    public String solved(String number) {
        int begin = 0;
        while (!number.isEmpty()) {
            if (number.length() > begin + 1 && number.charAt(begin) == number.charAt(begin + 1)) {
                number = remove(number, begin, begin + 1);
                if (begin > 0) begin--;
            } else {
                begin++;
            }
            if (begin == number.length()) break;
        }

        return number;
    }

    public String remove(String s, int begin, int end) {
        if (begin > end) return s;
        if (s.length() == 0) return "";
        return s.substring(0, begin) + s.substring(end + 1);
    }
}
