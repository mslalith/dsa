package src.stacks.problems;

import src.core.Problem;
import src.core.TestCase;
import src.utils.ListUtilsKt;

import java.util.Stack;

public class EvaluateExpression extends Problem<String[], Integer> {
    public static void main(String[] args) {
        new EvaluateExpression().run();
    }

    @Override
    protected TestCase<String[], Integer>[] getTestCases() {
        return new TestCase[]{
                new TestCase<>(ListUtilsKt.buildArray("2 1 + 3 *"), 9),
                new TestCase<>(ListUtilsKt.buildArray("4 13 5 / +"), 6),
                new TestCase<>(ListUtilsKt.buildArray("5 1 2 + 4 * + 3 -"), 14),
                new TestCase<>(ListUtilsKt.buildArray("5"), 5),
        };
    }

    @Override
    public Integer solve(String[] testInput) {
        return eval(testInput);
    }

    int eval(String[] inputArray) {
        Stack<String> stack = new Stack<>();

        int result = Integer.parseInt(inputArray[0]);
        for (String input : inputArray) {
            if (isOperator(input)) {
                result = handleOperator(input, stack.pop(), stack.pop());
                stack.push(String.valueOf(result));
            } else {
                stack.push(input);
            }
        }
        return result;
    }

    int handleOperator(String operator, String right, String left) {
        int leftInt = Integer.parseInt(left);
        int rightInt = Integer.parseInt(right);
        switch (operator) {
            case "+":
                return leftInt + rightInt;
            case "-":
                return leftInt - rightInt;
            case "*":
                return leftInt * rightInt;
            case "/":
                return leftInt / rightInt;
            default:
                throw new UnsupportedOperationException(operator + " not supported");
        }
    }

    boolean isOperator(String character) {
        if (character.equals("+")) return true;
        if (character.equals("-")) return true;
        if (character.equals("*")) return true;
        return character.equals("/");
    }
}
