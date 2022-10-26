package src.core;

public class RunningProblem extends Problem<Integer, String> {

    public static void main(String[] args) {
        new RunningProblem().run();
    }

    @Override
    protected TestCase<Integer, String>[] getTestCases() {
        return new TestCase[0];
    }

    @Override
    public String solve(Integer testInput) {
        return null;
    }
}
