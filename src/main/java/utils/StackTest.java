package utils;

import java.util.Stack;

/**
 * @author sviswanathan
 * Jun 07, 2021
 */
public class StackTest
{
    public static void print(Stack<String> s) {
        if (!s.empty()) {
            String element = s.pop();
            print(s);
            System.out.println(element);
        }
    }
    public static void main(String args[]) {
        String[] elements = new String[] {"A", "B", "C", "D"};
        Stack<String> stack = new Stack<>();
        for(String tmpString : elements){
            stack.push(tmpString);
        }
        print(stack);
    }
}
