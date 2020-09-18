package stc.prob1;


import java.util.Arrays;
import java.util.Scanner;

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

            int arr[] = new int[subTest];

            for (int index = 0; index < subTest; index++) {
                arr[index] = input.nextInt();
            }
            long result = sol.count(arr);

            System.out.printf("#%d %d\n", testCase, result);
        }
    }

    public long count(int arr[]) {
        // Trường hợp đặc biệt
        if (arr.length < 5) {

            Arrays.sort(arr);
            if (arr.length > 1) {
                return arr[arr.length - 1] - arr[arr.length - 2];
            } else return arr[0];
        }

        long totalHouseholds = 0;
        for (int i = 0; i < arr.length - 2; i++) {

            // tính toán lấy các toà nhà trong phạm vi xét
            int begin = i - 2;
            if (begin < 0) begin = 0;

            int end = i + 2;
            if (end >= arr.length) end = arr.length - 1;

            // lấy ra các tòa nhà trong khoảng đang xét
            int subArr[] = new int[end - begin + 1];
            int index = 0;
            for (int currIndex = begin; currIndex <= end; currIndex++) {
                subArr[index++] = arr[currIndex];
            }

            // sort
            Arrays.sort(subArr);

            // tìm các hộ thỏa mãn điều kiện đủ không gian mở
            if (arr[i] == subArr[subArr.length - 1] && arr[i] > subArr[subArr.length - 2]) {
                totalHouseholds += subArr[subArr.length - 1] - subArr[subArr.length - 2];
            }
        }
        return totalHouseholds;
    }
}
