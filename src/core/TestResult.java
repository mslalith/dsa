package src.core;

public class TestResult {
    public int allTests;
    public int passed;
    public int failed;

    public TestResult(int allTests, int passed, int failed) {
        this.allTests = allTests;
        this.passed = passed;
        this.failed = failed;
    }

    public boolean hasAllTestsPassed() {
        return allTests == passed;
    }
}
