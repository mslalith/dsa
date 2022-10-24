package src.stacks.problems;

import src.Problem;
import src.utils.ListUtilsKt;

import java.util.Stack;

public class NearestSmallerElement implements Problem<int[], int[]> {
    public static void main(String[] args) {
        NearestSmallerElement problem = new NearestSmallerElement();
        for (int[] testInput : problem.getTestInputs()) {
            String inputString = ListUtilsKt.stringFromArray(testInput);
            String outputString = ListUtilsKt.stringFromArray(problem.solution(testInput));
            System.out.println("Input : " + inputString);
            System.out.println("Output: " + outputString);
            System.out.println();
        }
    }


    @Override
    public int[][] getTestInputs() {
        return new int[][]{
                new int[]{4, 5, 2, 10, 8}, // [-1, 4, -1, 2, 2]
                new int[]{3, 2, 1}, // [-1, -1, -1]
                new int[]{34, 35, 27, 42, 5, 28, 39, 20, 28}, // -1 34 -1 27 -1 5 28 5 20
                new int[]{39, 27, 11, 4, 24, 32, 32, 1} // -1 -1 -1 -1 4 24 24 -1
        };
    }

    @Override
    public int[] solution(int[] testInput) {
        return prevSmaller(testInput);
    }

    int[] prevSmaller(int[] array) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[array.length];
        stack.push(array[0]);
        result[0] = -1;

        for (int i = 1; i < array.length; i++) {
            while (!stack.empty() && stack.peek() >= array[i]) stack.pop();

            if (stack.empty()) result[i] = -1;
            else result[i] = stack.peek();

            stack.push(array[i]);
        }

        return result;
    }
}
