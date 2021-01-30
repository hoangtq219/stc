package stc.training.backtrack;

import org.junit.Test;
import training.greedy.CardExchange;

/**
 * @author : hoangtq
 * @since : 11:05 03/10/2020, Sat
 **/
public class TestCardExchange {

    @Test
    public void exchange() {
        CardExchange cardExchange = new CardExchange();

        String test =
                "50\n" +
                        "772 8\n" +
                        "441296 9\n" +
                        "5525 2\n" +
                        "114 2\n" +
                        "6575 3\n" +
                        "0473 7\n" +
                        "7321 10\n" +
                        "1138 4\n" +
                        "53179 9\n" +
                        "975054 4\n" +
                        "0128 9\n" +
                        "50665 7\n" +
                        "937 2\n" +
                        "55960 5\n" +
                        "564 9\n" +
                        "50308 8\n" +
                        "57308 6\n" +
                        "215 4\n" +
                        "377088 3\n" +
                        "340 1\n" +
                        "1136 6\n" +
                        "37 2\n" +
                        "58 8\n" +
                        "1375 8\n" +
                        "655673 10\n" +
                        "29 4\n" +
                        "153 3\n" +
                        "583185 8\n" +
                        "812 7\n" +
                        "361 9\n" +
                        "394 5\n" +
                        "60153 1\n" +
                        "65433 4\n" +
                        "267825 10\n" +
                        "969 9\n" +
                        "027147 10\n" +
                        "08343 8\n" +
                        "57568 1\n" +
                        "805683 8\n" +
                        "83 8\n" +
                        "97 9\n" +
                        "7734 5\n" +
                        "5005 7\n" +
                        "216926 7\n" +
                        "3560 7\n" +
                        "50 1\n" +
                        "679 9\n" +
                        "16813 3\n" +
                        "1415 8\n" +
                        "751 10\n";

        String[] lines = test.split("\\n");
        int totalTest = Integer.parseInt(lines[0]);

        for (int testCase = 1; testCase <= totalTest; testCase++) {

            String[] numbers = lines[testCase].split("\\s++");

            // reset
            CardExchange.max = "";
            CardExchange.previous = "";

            String cardInfo = numbers[0];
            int totalExchange = Integer.parseInt(numbers[1]);
            CardExchange.totalSwap = totalExchange;
            CardExchange.remaining = totalExchange;
            cardExchange.exchange(cardInfo, totalExchange, 0);

            if (CardExchange.remaining % 2 == 0) {
                System.out.printf("Case #%d\n%s\n", testCase, CardExchange.max);
            } else {
                if (!CardExchange.previous.isEmpty()) {
                    System.out.printf("Case #%d\n%s\n", testCase, CardExchange.previous);
                } else {
                    System.out.printf("Case #%d\n%s\n", testCase, CardExchange.max);
                }
            }
        }
    }
}