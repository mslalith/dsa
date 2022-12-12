package src.stacks.problems;

import src.core.Problem;
import src.core.TestCase;

import java.util.Stack;

public class RedundantBraces extends Problem<String, Integer> {
    public static void main(String[] args) {
        new RedundantBraces().run();
    }

    @Override
    protected TestCase<String, Integer>[] getTestCases() {
        return new TestCase[]{
                new TestCase<>("((a+b))", 1),
                new TestCase<>("(a+(a+b))", 0),
                new TestCase<>("((a*b)+(c+d))", 0),
                new TestCase<>("(a+((b*c)+c))", 0),
        };
    }

    @Override
    public Integer solve(String testInput) {
        return braces(testInput);
    }

    int braces(String input) {
        Stack<Character> stack = new Stack<>();
        for (char ch : input.toCharArray()) {
            if (isOpening(ch) || isOperator(ch)) {
                stack.push(ch);
            } else if (isClosing(ch)) {
                if (isOpening(stack.peek())) return 1;
                while (!isOpening(stack.peek())) {
                    stack.pop();
                }
                stack.pop();
            }
        }
        return 0;
    }

    boolean isOpening(char ch) {
        return ch == '(';
    }

    boolean isClosing(char ch) {
        return ch == ')';
    }

    boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }
}
