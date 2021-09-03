package utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author sviswanathan
 * Jul 01, 2021
 */
public class JavaStreamsTest
{
    public static void main(String[] args) {
        List<String> vowels = List.of("a", "e", "i", "o", "u");

        // sequential stream - nothing to combine
        StringBuilder result = vowels.stream().collect(StringBuilder::new, (x, y) -> x.append(y), (a, b) -> a.append(",").append(b));
        System.out.println(result.toString());

        // parallel stream - combiner is combining partial results
        StringBuilder result1 = vowels.parallelStream().collect(StringBuilder::new, (x, y) -> x.append(y), (a, b) -> a.append(",").append(b));
        System.out.println(result1.toString());


        // Separate code with no relation to Streams
        /*Map<String, String> map = new HashMap<>();
        String boxId = null;
        String boxIdCast = String.valueOf(boxId);
        map.put(boxId, "Test");
        System.out.println("Value : " + map.get(boxIdCast));
        System.out.println("Value : " + map.get(boxId));*/
    }
}
