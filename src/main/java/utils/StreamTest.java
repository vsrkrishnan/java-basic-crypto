package utils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author sviswanathan
 * Jun 07, 2021
 */
public class StreamTest
{
    public static void main(String args[]) {
        List<String> stringList = Arrays.asList("1","3","4");
        Predicate<String> three=Predicate.isEqual("3");
        Stream<String> filteredStream = stringList.stream().filter(three);
        Consumer<String> c1=System.out::println;
        Consumer<String> c2=(s)->System.out.println(s.length());;
        filteredStream.forEach(c1);
        filteredStream.forEach(c2);
    }
}
