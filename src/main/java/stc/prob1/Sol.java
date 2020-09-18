package stc.prob1;

import java.util.*;

/**
 * @author : hoangtq
 * @since : 22:39 13/09/2020, Sun
 **/
public class Sol {
    /**
     * Ý tưởng: tìm số lớn nhất và số lớn thứ 2 trong tất cả các mảng con có 5 phần tử
     * - Nếu phần tử đang xét = số lớn thứ nhất và lớn hơn số thứ 2 thì lấy số lớn nhất trừ đi số lớn thứ 2 là ra số hộ gia đình
     */
    public static void main(String[] args) {
        Sol sol = new Sol();
        Scanner input = new Scanner(System.in);
        for (int testCase = 1; testCase <= 10; testCase++) {
            int subTest = input.nextInt();

            List<Integer> buildings = new ArrayList<>();

            for (int index = 0; index < subTest; index++) {
                int value = input.nextInt();
                buildings.add(value);
            }

            long result = sol.count(buildings);

            System.out.printf("#%d %d\n", testCase, result);
        }
    }

    public long count(List<Integer> buildings) {
        // Trường hợp đặc biệt
        if (buildings.size() < 5) {
            buildings.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer i1, Integer i2) {
                    return i1 - i2;
                }
            });

            if (buildings.size() > 1) {
                return buildings.get(buildings.size() - 1) - buildings.get(buildings.size() - 2);
            } else return buildings.get(0);
        }

        long totalHouseholds = 0;
        for (int i = 0; i < buildings.size() - 2; i++) {

            // tính toán lấy các toà nhà trong phạm vi xét
            int begin = i - 2;
            if (begin < 0) begin = 0;

            int end = i + 2;
            if (end >= buildings.size()) end = buildings.size() - 1;

            // lấy ra các tòa nhà trong khoảng đang xét
            List<Integer> subList = new ArrayList<>();
            for (int index = begin; index <= end; index++) {
                subList.add(buildings.get(index));
            }
            // sort
            subList.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer i1, Integer i2) {
                    return i1 - i2;
                }
            });

            // tìm các hộ thỏa mãn điều kiện đủ không gian mở
            if (buildings.get(i) == subList.get(subList.size() - 1) && buildings.get(i) > subList.get(subList.size() - 2)) {
                totalHouseholds += subList.get(subList.size() - 1) - subList.get(subList.size() - 2);
            }

        }
        return totalHouseholds;
    }
}
