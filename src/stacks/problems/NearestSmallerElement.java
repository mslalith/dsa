package src.stacks.problems;

import src.core.Problem;
import src.core.TestCase;

import java.util.Stack;

public class NearestSmallerElement extends Problem<int[], int[]> {
    public static void main(String[] args) {
        new NearestSmallerElement().run();
    }

    @Override
    protected TestCase<int[], int[]>[] getTestCases() {
        return new TestCase[]{
                new TestCase<>(
                        new int[]{4, 5, 2, 10, 8},
                        new int[]{-1, 4, -1, 2, 2}
                ),
                new TestCase<>(
                        new int[]{3, 2, 1},
                        new int[]{-1, -1, -1}
                ),
                new TestCase<>(
                        new int[]{34, 35, 27, 42, 5, 28, 39, 20, 28},
                        new int[]{-1, 34, -1, 27, -1, 5, 28, 5, 20}
                ),
                new TestCase<>(
                        new int[]{39, 27, 11, 4, 24, 32, 32, 1},
                        new int[]{-1, -1, -1, -1, 4, 24, 24, -1}
                ),
        };
    }

    @Override
    public int[] solve(int[] testInput) {
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
