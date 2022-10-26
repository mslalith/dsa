package src.core;

import src.linkedlist.ListNode;
import src.utils.LinkedListUtilsKt;
import src.utils.ListUtilsKt;

import java.util.Arrays;

public abstract class Problem<I, O> {
    protected abstract TestCase<I, O>[] getTestCases();

    protected abstract O solve(I testCaseInput);

    public TestResult runSilent() {
        int allTests = getTestCases().length;
        int passed = 0;
        int failed = 0;
        for (TestCase<I, O> testCase : getTestCases()) {
            if (runSingle(testCase, true)) passed++;
            else failed++;
        }
        return new TestResult(allTests, passed, failed);
    }

    public void run() {
        for (TestCase<I, O> testCase : getTestCases()) {
            runSingle(testCase, false);
        }
    }

    private boolean runSingle(TestCase<I, O> testCase, boolean silent) {
        String inputString = stringFromType(testCase.input);
        O output = solve(testCase.input);
        String outputString = stringFromType(output);
        if (!silent) System.out.println("Input: " + inputString);
        if (!silent) System.out.println("Output: " + outputString);

        boolean isTestPassed = isTestPassed(testCase.output, output);
        if (!silent) System.out.println(isTestPassed ? "✅ Passed" : "❌ Failed");
        if (!silent) System.out.println();
        return isTestPassed;
    }

    protected String stringFromType(Object input) {
        if (input == null) return "null";
        if (input instanceof String[]) return ListUtilsKt.stringFromArray((String[]) input);
        if (input instanceof int[]) return ListUtilsKt.stringFromArray((int[]) input);
        if (input instanceof ListNode<?>) return LinkedListUtilsKt.stringFromListNode((ListNode<?>) input);
        return input.toString();
    }

    protected <T> boolean isTestPassed(T actual, T expected) {
        if (actual == null && expected == null) return true;
        if (actual == null || expected == null) return false;
        if (actual instanceof int[]) return Arrays.equals((int[]) actual, (int[]) expected);
        if (actual instanceof ListNode<?>)
            return LinkedListUtilsKt.areListNodesEqual((ListNode) actual, (ListNode) expected);
        return actual.equals(expected);
    }
}
