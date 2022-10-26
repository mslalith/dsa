package src.core;

public class TestCase<I, O> {
    public I input;
    public O output;

    public TestCase(I input, O output) {
        this.input = input;
        this.output = output;
    }
}
