package week11.assignment_problems;

import java.util.*;

public class QuizScoringFormula {

    // Time Complexity: O(n)
    // Space Complexity: O(n)

    static int precedence(char op) {

        if (op == '+' || op == '-') {
            return 1;
        }

        if (op == '*' || op == '/') {
            return 2;
        }

        return 0;
    }

    static int calculate(int a, int b, char op) {

        switch (op) {

            case '+':
                return a + b;

            case '-':
                return a - b;

            case '*':
                return a * b;

            case '/':
                return a / b;
        }

        return 0;
    }

    static void applyOperation(
            Stack<Integer> values,
            Stack<Character> operators) {

        int b = values.pop();
        int a = values.pop();

        char op = operators.pop();

        values.push(calculate(a, b, op));
    }

    static int evaluate(String expr) {

        Stack<Integer> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int n = expr.length();

        for (int i = 0; i < n; i++) {

            char ch = expr.charAt(i);

            // Ignore spaces
            if (ch == ' ') {
                continue;
            }

            // Read multi-digit numbers
            if (Character.isDigit(ch)) {

                int number = 0;

                while (i < n &&
                        Character.isDigit(expr.charAt(i))) {

                    number = number * 10 +
                            (expr.charAt(i) - '0');

                    i++;
                }

                i--;

                values.push(number);
            }

            // Opening bracket
            else if (ch == '(') {

                operators.push(ch);
            }

            // Closing bracket
            else if (ch == ')') {

                while (!operators.isEmpty() &&
                        operators.peek() != '(') {

                    applyOperation(values, operators);
                }

                operators.pop();
            }

            // Operators
            else {

                while (!operators.isEmpty() &&
                        operators.peek() != '(' &&
                        precedence(operators.peek()) >= precedence(ch)) {

                    applyOperation(values, operators);
                }

                operators.push(ch);
            }
        }

        // Apply remaining operators
        while (!operators.isEmpty()) {

            applyOperation(values, operators);
        }

        return values.pop();
    }

    public static void main(String[] args) {

        String expr1 = "2 * (3 + 4) - 10 / 5";

        System.out.println(evaluate(expr1));

        String expr2 = "(8 - 2) * (1 + 2) / 4";

        System.out.println(evaluate(expr2));
    }
}