package week11.assignment_problems;

import java.util.*;

public class HackathonBannerPrinter {

    // Time Complexity: O(length of expanded output)
    // Space Complexity: O(length of expanded output + nesting depth)

    static String expandBanner(String code) {

        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();

        StringBuilder current = new StringBuilder();

        int number = 0;

        for (int i = 0; i < code.length(); i++) {

            char ch = code.charAt(i);

            if (Character.isDigit(ch)) {

                number = number * 10 + (ch - '0');
            }

            else if (ch == '[') {

                countStack.push(number);
                stringStack.push(current);

                current = new StringBuilder();
                number = 0;
            }

            else if (ch == ']') {

                int repeat = countStack.pop();

                StringBuilder previous = stringStack.pop();

                for (int j = 0; j < repeat; j++) {
                    previous.append(current);
                }

                current = previous;
            }

            else {

                current.append(ch);
            }
        }

        return current.toString();
    }

    public static void main(String[] args) {

        String code1 = "3[ab]2[c]";

        System.out.println(expandBanner(code1));

        String code2 = "2[x3[y]]z";

        System.out.println(expandBanner(code2));
    }
}