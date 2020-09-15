package stc.prob2;

/**
 * @author : hoangtq
 * @since : 08:55 15/09/2020, Tue
 **/
public class Sol {

    public static void main(String[] args) {
        Sol solution = new Sol();
        solution.run(1, "1238099084");
        solution.run(2, "4100112380990844");
    }

    public void run(int testCase, String number) {
        System.out.printf("#%d %s\n", testCase, solved(number));
    }

    public String solved(String number) {
        int begin = 0;
        while (!number.isEmpty()) {
            if (number.length() > begin + 1 && number.charAt(begin) == number.charAt(begin + 1)) {
                number = remove(number, begin, begin + 1);
                begin--;
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
