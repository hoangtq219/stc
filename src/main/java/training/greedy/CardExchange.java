package training.greedy;

import java.util.Scanner;

/**
 * @author : hoangtq
 * @since : 22:13 02/10/2020, Fri
 **/
public class CardExchange {
    public static String max = "";
    public static String previous = "";
    public static int remaining = 0;
    public static int totalSwap = 0;

    public static void main(String[] args) {
        CardExchange cardExchange = new CardExchange();
        Scanner input = new Scanner(System.in);
        int totalTest = Integer.parseInt(input.nextLine());
        for (int testCase = 1; testCase <= totalTest; testCase++) {

            String line = input.nextLine();
            String[] numbStr = line.split("\\s++");
            String cardInfo = numbStr[0];
            int totalExchange = Integer.parseInt(numbStr[1]);
            // reset
            max = "";
            previous = "";
            remaining = totalExchange;
            totalSwap = totalExchange;

            cardExchange.exchange(cardInfo, totalExchange, 0);

            /**
             * Sau khi tìm được số lớn nhất, nếu vẫn còn dư số lượt tráo đổi
             * dựa vào số lượt dư còn lại để xem xét có cần tráo đổi hay không
             * dư == 1 => tráo về số lớn thứ 2 => trả về số lớn thứ 2
             * dư %2 == 0 => đổi tối ưu sẽ ra số lớn nhất =>trả về luôn số lớn nhất
             * */
            if (CardExchange.remaining % 2 == 0) {
                System.out.printf("Case #%d\n%s\n", testCase, max);
            } else {
                if (!CardExchange.previous.isEmpty()) {
                    System.out.printf("Case #%d\n%s\n", testCase, previous);
                } else {
                    System.out.printf("Case #%d\n%s\n", testCase, max);
                }
            }

        }
    }

    public String swap(String str, int i, int j) {
        if (i == j) return str;
        char ith = str.charAt(i);
        char jth = str.charAt(j);

        String left = str.substring(0, i);
        String middle = str.substring(i + 1, j);
        String right = str.substring(j + 1);
        return left + jth + middle + ith + right;
    }

    public void exchange(String cardInfo, int remainingSwap, int currentIndex) {

        if (remainingSwap == 0 || currentIndex >= cardInfo.length()) {
            return;
        }

        int n = cardInfo.length();

        char maxChar = cardInfo.charAt(currentIndex);

        for (int id = currentIndex + 1; id < n; id++) {
            if (maxChar < cardInfo.charAt(id)) {
                maxChar = cardInfo.charAt(id);
            }
        }

        if (maxChar != cardInfo.charAt(currentIndex)) {
            remainingSwap--;
        }

        for (int id = currentIndex; id < n; id++) {
            if (cardInfo.charAt(id) == maxChar) {
                cardInfo = swap(cardInfo, currentIndex, id);

                if (cardInfo.compareTo(max) > 0) {
                    previous = max;
                    max = cardInfo;
                    if (remaining > (totalSwap - remainingSwap)) {
                        remaining = totalSwap - remainingSwap;
                    }
                }

                exchange(cardInfo, remainingSwap, currentIndex + 1);
                cardInfo = swap(cardInfo, currentIndex, id);
            }
        }
    }
}