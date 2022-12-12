package src.math.problems;

import src.core.Problem;
import src.core.TestCase;

public class IsPrime extends Problem<Integer, Boolean> {

    public static void main(String[] args) {
        new IsPrime().run();
    }

    @Override
    protected TestCase<Integer, Boolean>[] getTestCases() {
        return new TestCase[]{
                new TestCase(10, false),
                new TestCase(59, true)
        };
    }

    @Override
    protected Boolean solve(Integer testCaseInput) {
        return isPrime(testCaseInput);
    }

    private boolean isPrime(int n) {
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }

        return true;
    }
}
