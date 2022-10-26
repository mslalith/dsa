package src.core;

import src.utils.ListUtilsKt;

import java.util.Arrays;

public abstract class Problem<I, O> {
    protected abstract TestCase<I, O>[] getTestCases();

    protected abstract O solve(I testCaseInput);

    public void run() {
        for (TestCase<I, O> testCase : getTestCases()) {
            String inputString = stringFromType(testCase.input);
            O output = solve(testCase.input);
            String outputString = stringFromType(output);
            System.out.println("Input: " + inputString);
            System.out.println("Output: " + outputString);

            boolean isTestPassed = isTestPassed(testCase.output, output);
            System.out.println(isTestPassed ? "✅ Passed" : "❌ Failed");
            System.out.println();
        }
    }

    String stringFromType(Object input) {
        if (input instanceof String[]) return ListUtilsKt.stringFromArray((String[]) input);
        if (input instanceof int[]) return ListUtilsKt.stringFromArray((int[]) input);
        return input.toString();
    }

    boolean isTestPassed(O actual, O expected) {
        if (actual instanceof int[]) return Arrays.equals((int[]) actual, (int[]) expected);
        return actual.equals(expected);
    }
}
