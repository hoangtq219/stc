package stc.prob1;

import stc.utils.FileHelper;
import stc.utils.FuncHelper;
import org.junit.Test;

import java.util.List;


/**
 * @author : hoangtq
 * @since : 22:40 13/09/2020, Sun
 **/
public class TestSolution {

    @Test
    public void run() {
        int[] arr = new int[]{0, 0, 225, 214, 82, 73, 241, 233, 179, 219, 135, 62, 36, 13, 6, 71, 179, 77, 67, 139, 31, 90, 9, 37, 93, 203, 150, 69, 19, 137, 28, 174, 32, 80, 64, 54, 18, 0, 158, 73, 173, 20, 0, 102, 107, 48, 50, 161, 246, 145, 225, 31, 0, 153, 185, 157, 44, 126, 153, 233, 0, 201, 83, 103, 191, 0, 45, 0, 131, 87, 97, 105, 97, 209, 157, 22, 0, 29, 132, 74, 2, 232, 44, 203, 0, 51, 0, 244, 212, 212, 89, 53, 50, 244, 207, 144, 72, 143, 0, 0};
        Sol sol = new Sol();

        for (int i = 0; i < arr.length; i++) {
            sol.buildings.add(arr[i]);
        }

        sol.run(1);
    }

    @Test
    public void runFile() {
        Sol sol = new Sol();

        List<String> testCases = FileHelper.readLines("inputs/problem1.txt");
        for (int index = 0; index < testCases.size(); index += 2) {

            int testcase = index / 2 + 1;
            if (index % 2 == 1) {
                testcase += 1;
            }

            int totalBuilding = Integer.parseInt(testCases.get(index));
            List<Integer> buildings = FuncHelper.stringToIntegers(testCases.get(index + 1));

            if (totalBuilding != buildings.size()) {
                System.out.printf("Test %d thiếu: \n", testcase);
                System.out.printf("Total buildings =  %d, length = %d\n", totalBuilding, buildings.size());
                continue;
            }

            sol.buildings = buildings;

            sol.run(testcase);
        }

    }
}
