package src.math.problems;

import src.core.Problem;
import src.core.TestCase;

public class SumOfDigits extends Problem<Integer, Integer> {

    public static void main(String[] args) {
        new SumOfDigits().run();
    }

    @Override
    protected TestCase<Integer, Integer>[] getTestCases() {
        return new TestCase[]{
                new TestCase(123, 6),
                new TestCase<>(89723478, 48)
        };
    }

    @Override
    protected Integer solve(Integer testCaseInput) {
        return reverseDigits(testCaseInput);
    }

    private int reverseDigits(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}
