package stc.prob1;

import java.util.*;

/**
 * @author : hoangtq
 * @since : 22:39 13/09/2020, Sun
 **/
public class Sol {
    /**
     *
     * Ý tưởng: tìm số lớn nhất và số lớn thứ 2 trong tất cả các mảng con có 5 phần tử
     * - Nếu phần tử đang xét = số lớn thứ nhất và lớn hơn số thứ 2 thì lấy số lớn nhất trừ đi số lớn thứ 2 là ra số hộ gia đình
     *
     * */
    public List<Integer> buildings = new ArrayList<>();

    public static void main(String[] args) {
        Sol sol = new Sol();
        sol.buildings = Arrays.asList(0, 0, 225, 214, 82, 73, 241, 233, 179, 219, 135, 62, 36, 13, 6, 71, 179, 77, 67, 139, 31, 90, 9, 37, 93, 203, 150, 69, 19, 137, 28, 174, 32, 80, 64, 54, 18, 0, 158, 73, 173, 20, 0, 102, 107, 48, 50, 161, 246, 145, 225, 31, 0, 153, 185, 157, 44, 126, 153, 233, 0, 201, 83, 103, 191, 0, 45, 0, 131, 87, 97, 105, 97, 209, 157, 22, 0, 29, 132, 74, 2, 232, 44, 203, 0, 51, 0, 244, 212, 212, 89, 53, 50, 244, 207, 144, 72, 143, 0, 0);
        sol.run(1);
    }

    public void run(int testCase) {
        System.out.printf("#%d %d\n", testCase, count());
    }

    public int count() {
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

        int totalHouseholds = 0;
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
