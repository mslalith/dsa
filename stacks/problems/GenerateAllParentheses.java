package stacks.problems;

import src.Problem;

import java.util.Stack;

public class GenerateAllParentheses implements Problem<String, Integer> {
    public static void main(String[] args) {
        GenerateAllParentheses problem = new GenerateAllParentheses();
        for (String testInput : problem.getTestInputs()) {
            System.out.println(testInput + ": " + problem.solution(testInput));
        }
    }

    @Override
    public String[] getTestInputs() {
        return new String[]{
                "()[]{}", // 1
                "([[{}]()])", // 1
                "){}", // 0
                "{(}" // 0
        };
    }

    @Override
    public Integer solution(String testInput) {
        return isValid(testInput);
    }

    static int isValid(String input) {
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

    static boolean isOpening(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }

    static boolean isClosing(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    static boolean isMatching(char open, char close) {
        if (open == '(' && close == ')') return true;
        if (open == '[' && close == ']') return true;
        return open == '{' && close == '}';
    }
}
