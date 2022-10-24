package stacks.problems;

import src.Problem;
import utils.ListUtilsKt;

import java.util.Stack;

public class EvaluateExpression implements Problem<String[], Integer> {
    public static void main(String[] args) {
        EvaluateExpression problem = new EvaluateExpression();
        for (String[] testInput : problem.getTestInputs()) {
            String inputString = ListUtilsKt.stringFromArray(testInput);
            System.out.println(inputString + ": " + problem.solution(testInput));
        }
    }

    @Override
    public String[][] getTestInputs() {
        String[][] array = new String[4][];
        array[0] = ListUtilsKt.buildArray("2 1 + 3 *"); // 9
        array[1] = ListUtilsKt.buildArray("4 13 5 / +"); // 6
        array[2] = ListUtilsKt.buildArray("5 1 2 + 4 * + 3 -"); // 14
        array[3] = ListUtilsKt.buildArray("5"); // 5
        return array;
    }

    @Override
    public Integer solution(String[] testInput) {
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
