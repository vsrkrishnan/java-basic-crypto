package utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author sviswanathan
 * Jun 07, 2021
 */
public class LinkedHashMapTest
{
    public static void main(String[] args) {
        Map<Container, String> map = new LinkedHashMap<>();
        map.put(new Container("John"), "1");
        map.put(new Container("Jos"), "2");
        map.put(new Container("Broad"), "3");
        map.put(new Container("Cook"), "4");
        map.put(new Container("Smith"), "5");
        map.forEach((k, v) -> System.out.println(v));
    }

    public static class Container {
        private String name;
        public Container(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
        public int hashCode() {
            return 997;
        }
        public boolean equals(Object obj) {
            if (Objects.isNull(obj))
                return false;
            Container that = (Container) obj;
            return this.getName().length() == that.getName().length();
        }
    }
}
