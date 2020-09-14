package stc.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : hoangtq
 * @since : 22:19 14/09/2020, Mon
 **/
public class FuncHelper {

    public static List<Integer> stringToIntegers(String s) {
        List<Integer> result = new ArrayList<>();
        String[] numbers = s.split("\\s++");
        for (String number : numbers) {
            result.add(Integer.parseInt(number));
        }
        return result;
    }
}
