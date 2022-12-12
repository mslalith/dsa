package src.math.problems;

import src.core.Problem;
import src.core.TestCase;

public class ReverseDigits extends Problem<Integer, Integer> {

    public static void main(String[] args) {
        new ReverseDigits().run();
    }

    @Override
    protected TestCase<Integer, Integer>[] getTestCases() {
        return new TestCase[]{
                new TestCase(123, 321),
                new TestCase<>(89723478, 87432798)
        };
    }

    @Override
    protected Integer solve(Integer testCaseInput) {
        return reverseDigits(testCaseInput);
    }

    private int reverseDigits(int n) {
        int rev = 0;
        while (n > 0) {
            rev = (rev * 10) + (n % 10);
            n /= 10;
        }
        return rev;
    }
}
