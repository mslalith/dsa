package src.core;

import src.linkedlist.problems.RemoveDuplicatesFromSortedList;
import src.linkedlist.problems.RemoveNthNodeFromListEnd;
import src.stacks.problems.*;

public class RunAllProblems {
    public static void main(String[] args) {
        System.out.println("======= Running Stack problems =======");
        run(new EvaluateExpression());
        run(new GenerateAllParentheses());
        run(new NearestSmallerElement());
        run(new RedundantBraces());
        System.out.println();

        System.out.println("======= Running Linked List problems =======");
        run(new RemoveDuplicatesFromSortedList());
        run(new RemoveNthNodeFromListEnd());
    }

    static void run(Problem<?, ?> problem) {
        TestResult testResult = problem.runSilent();
        StringBuilder sb = new StringBuilder();

        sb.append("Running ").append(problem.getClass().getSimpleName());
        sb.append(": (").append(testResult.allTests).append(") test").append(testResult.allTests > 1 ? "s" : "");
        sb.append("\n");

        String passedText = testResult.hasAllTestsPassed() ? "All" : String.valueOf(testResult.passed);
        sb.append("✅ ").append(passedText).append(" Passed");
        sb.append("\n");

        if (testResult.failed > 0) {
            sb.append("❌ ").append(testResult.failed).append(" Failed");
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
