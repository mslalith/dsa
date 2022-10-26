package src.core;

import src.utils.ListUtilsKt;

public abstract class Problem<I, O> {
    protected abstract TestCase<I, O>[] getTestCases();

    protected abstract O solve(I testCaseInput);

    public void run() {
        for (TestCase<I, O> testCase : getTestCases()) {
            String inputString = stringFromInput(testCase.input);
            O output = solve(testCase.input);
            System.out.println("Input: " + inputString);
            System.out.println("Output: " + output);

            boolean isTestPassed = testCase.output.equals(output);
            System.out.println(isTestPassed ? "✅ Passed" : "❌ Failed");
            System.out.println();
        }
    }

    String stringFromInput(I input) {
        if (input instanceof String[]) return ListUtilsKt.stringFromArray((String[]) input);
        return "";
    }
}
