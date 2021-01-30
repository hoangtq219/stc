package training.greedy;

import java.util.Scanner;

public class BuyStock {

    public static void main(String[] args) {
        BuyStock buyStock = new BuyStock();

        Scanner input = new Scanner(System.in);
        int totalTestCase = input.nextInt();
        for (int testCase = 1; testCase <= totalTestCase; testCase++) {
            int numOfDays = input.nextInt();

            Stock[] stocks = new Stock[numOfDays];

            for (int index = 0; index < numOfDays; index++) {
                int price = input.nextInt();
                stocks[index] = new Stock(index, price);
            }

            System.out.printf("#%d %d\n", testCase, buyStock.solve(stocks));

        }
    }

    public long solve(Stock[] stocks) {
        long result = 0;

        // sort O(n*n) :((
        for (int i = 0; i < stocks.length - 1; i++) {
            for (int j = i + 1; j < stocks.length; j++) {
                if (stocks[i].getPrice() > stocks[j].getPrice()) {
                    Stock tmp = stocks[j];
                    stocks[j] = stocks[i];
                    stocks[i] = tmp;
                }
            }
        }

//        printf(stocks, "After sort");
        int currentIndex = stocks.length - 1;
        // tổng các ứng cử vêin còn lại
        int totalCandidate = stocks.length;
        stocks[currentIndex].setMark(true);
        /**
         * Tìm tất cả các stock có index nhỏ hơn index của stock đang xét
         * */
        while (currentIndex > 0 && totalCandidate > 0) {
            long sum = 0;
            int currentMax = stocks[currentIndex].getPrice();
            for (int index = currentIndex - 1; index >= 0; index--) {
                if (!stocks[index].isMark() && stocks[index].getIndex() < stocks[currentIndex].getIndex()) {
                    stocks[index].setMark(true);
                    sum += (currentMax - stocks[index].getPrice());
                    totalCandidate--;
                }
            }

            result += sum;
            currentIndex--;
        }

        return result;
    }

    public void printf(Stock[] stocks, String msg) {
        System.out.printf("\n### %s ###\n", msg);

        for (Stock stock : stocks) {
            System.out.println(stock.toString());
        }
        System.out.println("____________________");
    }

    static class Stock {
        private int index;
        private int price;
        private boolean isMark;

        public Stock() {
        }

        public Stock(int index, int price) {
            this.index = index;
            this.price = price;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public boolean isMark() {
            return isMark;
        }

        public void setMark(boolean mark) {
            isMark = mark;
        }

        @Override
        public String toString() {
            return "Stock{" +
                    "index=" + index +
                    ", price=" + price +
                    ", isMark=" + isMark +
                    '}';
        }
    }

}
