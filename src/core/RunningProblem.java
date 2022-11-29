package src.core;

public class RunningProblem extends Problem<String, Integer> {

    public static void main(String[] args) {
        new RunningProblem().run();
    }

    @Override
    protected TestCase<String, Integer>[] getTestCases() {
        return new TestCase[]{};
    }

    @Override
    protected Integer solve(String testCaseInput) {
        return null;
    }
}
