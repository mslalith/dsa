package stacks.problems;

import src.Problem;

import java.util.Stack;

public class RedundantBraces implements Problem<String, Integer> {
    public static void main(String[] args) {
        RedundantBraces problem = new RedundantBraces();
        for (String testInput : problem.getTestInputs()) {
            System.out.println(testInput + ": " + problem.solution(testInput));
        }
    }

    @Override
    public String[] getTestInputs() {
        return new String[]{
                "((a+b))", // 1
                "(a+(a+b))", // 0
                "((a*b)+(c+d))", // 0
                "(a+((b*c)+c))" // 0
        };
    }

    @Override
    public Integer solution(String testInput) {
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
