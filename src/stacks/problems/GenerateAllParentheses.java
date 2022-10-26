package src.stacks.problems;

import src.core.Problem;
import src.core.TestCase;

import java.util.Stack;

public class GenerateAllParentheses extends Problem<String, Integer> {
    public static void main(String[] args) {
        new GenerateAllParentheses().run();
    }

    @Override
    protected TestCase<String, Integer>[] getTestCases() {
        return new TestCase[]{
                new TestCase<>("()[]{}", 1),
                new TestCase<>("([[{}]()])", 1),
                new TestCase<>("){}", 0),
                new TestCase<>("{(}", 0),
        };
    }

    @Override
    public Integer solve(String testInput) {
        return isValid(testInput);
    }

    int isValid(String input) {
        Stack<Character> stack = new Stack<>();
        for (char ch : input.toCharArray()) {
            if (stack.empty() && isClosing(ch)) return 0;

            if (isOpening(ch)) {
                stack.push(ch);
            }
            if (isClosing(ch)) {
                if (isMatching(stack.peek(), ch)) stack.pop();
                else return 0;
            }
        }
        return stack.empty() ? 1 : 0;
    }

    boolean isOpening(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }

    boolean isClosing(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    boolean isMatching(char open, char close) {
        if (open == '(' && close == ')') return true;
        if (open == '[' && close == ']') return true;
        return open == '{' && close == '}';
    }
}
